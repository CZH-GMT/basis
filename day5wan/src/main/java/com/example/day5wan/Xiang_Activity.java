package com.example.day5wan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class Xiang_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView xiv;
    private EditText xtitle;
    private EditText xdesc;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_);
        initView();
    }

    private void initView() {
        xiv = (ImageView) findViewById(R.id.xiv);
        xtitle = (EditText) findViewById(R.id.xtitle);
        xdesc = (EditText) findViewById(R.id.xdesc);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
        Intent intent = getIntent();
        Bean.DataBean info = (Bean.DataBean) intent.getSerializableExtra("info");
        Glide.with(this).load(info.getImagePath()).into(xiv);
        xtitle.setText(info.getTitle());
        xdesc.setText(info.getDesc());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Intent intent = new Intent();
                String string = xtitle.getText().toString();
                String string1 = xdesc.getText().toString();
                intent.putExtra("b",string);
                intent.putExtra("m",string1);
                setResult(200,intent);
                finish();
                break;
        }
    }


}
