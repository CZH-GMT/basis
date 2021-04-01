package com.example.shujuku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mame;
    private EditText age;
    private EditText sex;
    private Button add;
    private Button delete;
    private Button undate;
    private Button query;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mame = (EditText) findViewById(R.id.mame);
        age = (EditText) findViewById(R.id.age);
        sex = (EditText) findViewById(R.id.sex);
        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);
        undate = (Button) findViewById(R.id.undate);
        query = (Button) findViewById(R.id.query);
        tv = (TextView) findViewById(R.id.tv);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        undate.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mameString = mame.getText().toString().trim();
        String ageString = age.getText().toString().trim();
        String sexString = sex.getText().toString().trim();
        switch (v.getId()) {

            case R.id.add:

                break;
            case R.id.delete:

                break;
            case R.id.undate:

                break;
            case R.id.query:

                break;
        }
    }


}
