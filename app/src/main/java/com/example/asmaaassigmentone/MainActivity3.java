package com.example.asmaaassigmentone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ListView listView = findViewById(R.id.list);

        List<String> list = new ArrayList<>();
        list.add("Who is Pythagoras?");
        list.add("What is the Pythagorean theorem?");
        list.add("Steps to calculate the Pythagorean theorem");
        list.add("Pythagoras calculator");
        list.add("A short quiz of Pythagorean theorem");
        list.add("Quit APP");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        openInformation();
                        break;
                    case 1:

                        openTheoremPage();
                        break;

                    case 2:

                        openSteps();
                        break;
                    case 3:
                        openCalculatorPage();
                        break;
                    case 4:
                        openQuiz();
                        break;
                    case 5:
                        // Close the app
                        finish();
                        break;
                }
            }
        });
    }


    private void openCalculatorPage() {
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);
    }
    private void openTheoremPage() {
        Intent intent = new Intent(MainActivity3.this, Theorem.class);
        startActivity(intent);
    }
    private void openSteps() {
        Intent intent = new Intent(MainActivity3.this, Steps.class);
        startActivity(intent);
    }

    private void openQuiz() {
        Intent intent = new Intent(MainActivity3.this, Quiz.class);
        startActivity(intent);
    }

    private void openInformation() {
        Intent intent = new Intent(MainActivity3.this, Information.class);
        startActivity(intent);
    }
}
