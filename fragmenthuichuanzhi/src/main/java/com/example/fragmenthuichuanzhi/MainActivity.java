package com.example.fragmenthuichuanzhi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et;
    private Button btn;
    private FrameLayout fl;
    private MyFragment myFragment;
    private FragmentManager supportFragmentManager;

    public EditText getEt() {
        return et;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        et = (EditText) findViewById(R.id.et);
        btn = (Button) findViewById(R.id.btn);
        fl = (FrameLayout) findViewById(R.id.fl);

        btn.setOnClickListener(this);
        myFragment = new MyFragment();
        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .add(R.id.fl, myFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:

                String string = myFragment.getEtf().getText().toString();
                et.setText(string);
                break;


        }
    }


}
