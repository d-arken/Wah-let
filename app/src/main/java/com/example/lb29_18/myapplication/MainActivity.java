package com.example.lb29_18.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Button bt1;
    private Button bt2;
    private Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState !=null)
            return;
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3= (Button) findViewById(R.id.bt3);

         fragment1= new Fragment1();
         fragment2= new Fragment2();
         fragment3= new Fragment3();



        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager= getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();

                transaction.replace(R.id.container,fragment1);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager= getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();

                transaction.replace(R.id.container,fragment2);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager= getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();

                transaction.replace(R.id.container,fragment3);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


    }
}
