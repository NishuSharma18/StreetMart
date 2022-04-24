package com.example.streetmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Objects;

public class secondActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    String s1[],s2[];
    int images[] ={R.drawable.honey,R.drawable.brush,R.drawable.tomato,R.drawable.oximeter,R.drawable.apple,R.drawable.tshirt,R.drawable.shoes,R.drawable.bp};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerView);
        Objects.requireNonNull(getSupportActionBar()).hide();
        s1 = getResources().getStringArray(R.array.items);
    }
}