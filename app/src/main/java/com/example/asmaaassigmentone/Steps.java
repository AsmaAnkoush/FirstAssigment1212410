package com.example.asmaaassigmentone;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Steps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_steps);
        ListView listView = findViewById(R.id.list);

        List<String> steps = new ArrayList<>();

        steps.add("1-Understand the Theorem: The Pythagorean theorem describes a fundamental relationship in geometry.");
        steps.add("2-Identify the Sides: In any right-angled triangle, there are three sides: the hypotenuse and the two legs.");
        steps.add("3-Measure the Lengths: Use a ruler or any measuring tool to determine the lengths of the two legs of the right triangle.");
        steps.add("4-Square the Lengths: Calculate the square of each length by multiplying it by itself.");
        steps.add("5-Add the Squares: After squaring the lengths of the two legs, add the results together.");
        steps.add("6-Calculate the Square of the Hypotenuse: Measure the length of the hypotenuse and then square this length.");
        steps.add("7-Compare the Results: Compare the sum of the squares of the lengths of the legs with the square of the length of the hypotenuse.");
        steps.add("8-Apply the Theorem: Once you have verified that the triangle satisfies the Pythagorean theorem, you can use this principle to solve various geometric problems involving right-angled triangles.");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, steps);
        listView.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}