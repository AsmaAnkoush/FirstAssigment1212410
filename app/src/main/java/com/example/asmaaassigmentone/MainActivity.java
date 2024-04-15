package com.example.asmaaassigmentone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextA, editTextB, editTextC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);

        editTextA.setShowSoftInputOnFocus(false);
        editTextB.setShowSoftInputOnFocus(false);

    }


    public void onNumberButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        EditText focusedEditText = getFocusedEditText();
        if (focusedEditText != null) {
            String currentText = focusedEditText.getText().toString();
            focusedEditText.setText(currentText + buttonText);
        } else {
            Toast.makeText(MainActivity.this, "Please select a text field first", Toast.LENGTH_SHORT).show();
        }
    }



    private void calculateResult() {
        String lengthAString = editTextA.getText().toString();
        String lengthBString = editTextB.getText().toString();

        if (lengthAString.isEmpty() || lengthBString.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter lengths for sides a and b", Toast.LENGTH_SHORT).show();
            return;
        }
        double lengthA = Double.parseDouble(lengthAString);
        double lengthB = Double.parseDouble(lengthBString);
        double lengthC = Math.sqrt(Math.pow(lengthA, 2) + Math.pow(lengthB, 2));
        editTextC.setText(String.valueOf(lengthC));
    }
    public void onCalculateButtonClick(View view) {
        calculateResult();
    }
    public void onClearButtonClick(View view) {
        clearInputs();
    }

    public void onDeleteButtonClick(View view) {
        deleteLastDigit();
    }


    private void clearInputs() {

        editTextA.getText().clear();
        editTextB.getText().clear();
        editTextC.getText().clear();
    }

    private void deleteLastDigit() {
        EditText focusedEditText = getFocusedEditText();
        if (focusedEditText != null) {
            String currentText = focusedEditText.getText().toString();
            if (!currentText.isEmpty()) {
                focusedEditText.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else {
            Toast.makeText(MainActivity.this, "Please select a text field first", Toast.LENGTH_SHORT).show();
        }
    }

    private EditText getFocusedEditText() {
        EditText[] editTexts = {editTextA, editTextB, editTextC};
        for (EditText editText : editTexts) {
            if (editText.hasFocus()) {
                return editText;
            }
        }
        return null;
    }


}
