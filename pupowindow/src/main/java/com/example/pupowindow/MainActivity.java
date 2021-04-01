package com.example.pupowindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);


                final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAsDropDown(tv, 100, -150);
                //popupWindow.setOutsideTouchable(true);


                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha=0.3f;
                window.setAttributes(attributes);
                popupWindow.setOnDismissListener(
                        new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Window window = getWindow();
                        WindowManager.LayoutParams attributes = window.getAttributes();
                        attributes.alpha=1f;
                        window.setAttributes(attributes);
                    }
                });


                final View btn = inflate.findViewById(R.id.btn);
                View btn1 = inflate.findViewById(R.id.btn1);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


            }
        });

    }
}
