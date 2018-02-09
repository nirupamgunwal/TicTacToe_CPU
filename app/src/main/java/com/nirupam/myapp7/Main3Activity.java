package com.nirupam.myapp7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                devmessage();
            }
        });
    }



    public void openActivity(){
        Intent intent1 = new Intent(this , MainActivity.class);

        startActivity(intent1);
    }

    public void openActivity2(){
        Intent intent = new Intent(this , Main2Activity.class);

        startActivity(intent);
    }

    public void devmessage(){
        Toast.makeText(this, "Feature not available yet" , Toast.LENGTH_SHORT).show();
    }
}
