package com.example.asmaaassigmentone;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Quiz extends AppCompatActivity {
    private Spinner spinner;
    private Button submitButton;
    private RadioButton trueRadio, falseRadio;
    private EditText editText;
    private RadioButton optionA, optionB, optionC, optionD;
    private CheckBox rememberCheckbox;
    public static final String SPINNER_ANSWER = "SPINNER_ANSWER";
    public static final String TRUE_FALSE_ANSWER = "TRUE_FALSE_ANSWER";
    public static final String EDIT_TEXT_ANSWER = "EDIT_TEXT_ANSWER";
    public static final String MULTIPLE_CHOICE_ANSWER = "MULTIPLE_CHOICE_ANSWER";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner = findViewById(R.id.spinner2);
        submitButton = findViewById(R.id.submit_button);
        trueRadio = findViewById(R.id.truee);
        falseRadio = findViewById(R.id.falsee);
        editText = findViewById(R.id.editTextQ1);
        optionA = findViewById(R.id.OptionA);
        optionB = findViewById(R.id.OptionB);
        optionC = findViewById(R.id.OptionC);
        optionD = findViewById(R.id.OptionD);
        rememberCheckbox = findViewById(R.id.Checkbox);

        String[] options = {"a^2 + b^2 = c^2", "a + b = c", "a^2 - b^2 = c^2", "a × b = c"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        spinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedSpinner = spinner.getSelectedItem().toString();
                boolean trueSelected = trueRadio.isChecked();
                String editTextAnswer = editText.getText().toString().trim();
                boolean optionASelected = optionA.isChecked();
                boolean optionBSelected = optionB.isChecked();
                boolean optionCSelected = optionC.isChecked();
                boolean optionDSelected = optionD.isChecked();

                if (!optionASelected && !optionBSelected && !optionCSelected && !optionDSelected) {
                    Toast.makeText(Quiz.this, "Please fill in all the answers.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedSpinner.isEmpty() || editTextAnswer.isEmpty()) {
                    Toast.makeText(Quiz.this, "Please fill in all the answers.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String errorMessage = checkAnswers(selectedSpinner, trueSelected, editTextAnswer, optionCSelected);
                Toast.makeText(Quiz.this, errorMessage, Toast.LENGTH_SHORT).show();

                if (rememberCheckbox.isChecked()) {
                    btnSaveAnswers();
                }
            }
        });
        setSharedPrefs();
        checkPrefs();
    }

    private void setSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    public void btnSaveAnswers() {
        String selectedSpinner = spinner.getSelectedItem().toString();
        boolean trueSelected = trueRadio.isChecked();
        String editTextAnswer = editText.getText().toString().trim();
        boolean optionASelected = optionA.isChecked();
        boolean optionBSelected = optionB.isChecked();
        boolean optionCSelected = optionC.isChecked();
        boolean optionDSelected = optionD.isChecked();

        boolean multipleChoiceAnswer = optionASelected || optionBSelected || optionCSelected || optionDSelected;
        if (rememberCheckbox.isChecked()) {
            if (!flag) {
                editor.putString(SPINNER_ANSWER, selectedSpinner);
                editor.putBoolean(TRUE_FALSE_ANSWER, trueSelected);
                editor.putString(EDIT_TEXT_ANSWER, editTextAnswer);
                editor.putBoolean(MULTIPLE_CHOICE_ANSWER, multipleChoiceAnswer);
                editor.putBoolean(FLAG, true);
                editor.apply();
            }
        }
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);
        if (flag) {
            String spinnerAnswer = prefs.getString(SPINNER_ANSWER, "");
            boolean trueFalseAnswer = prefs.getBoolean(TRUE_FALSE_ANSWER, false);
            String editTextAnswer = prefs.getString(EDIT_TEXT_ANSWER, "");
            boolean multipleChoiceAnswer = prefs.getBoolean(MULTIPLE_CHOICE_ANSWER, false);

            spinner.setSelection(((ArrayAdapter<String>) spinner.getAdapter()).getPosition(spinnerAnswer));
            trueRadio.setChecked(trueFalseAnswer);
            falseRadio.setChecked(!trueFalseAnswer);
            editText.setText(editTextAnswer);
            if (multipleChoiceAnswer) {
                optionC.setChecked(true);
            }
        }
    }

    private String checkAnswers(String spinnerAnswer, boolean trueFalseAnswer, String editTextAnswer, boolean multipleChoiceAnswer) {
        if (!spinnerAnswer.equals("a^2 + b^2 = c^2")) {
            return "Spinner answer is incorrect.";
        }
        if (!trueFalseAnswer) {
            return "True/False answer is incorrect.";
        }
        if (!editTextAnswer.equalsIgnoreCase("Hypotenuse")) {
            return "Fill-in-the-blank answer is incorrect.";
        }
        if (!multipleChoiceAnswer) {
            return "Multiple choice answer is incorrect.";
        }
        return "All answers are correct!";
    }

}
