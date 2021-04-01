//package com.example.sousuo2;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MainActivity extends AppCompatActivity {
//
//    private EditText et;
//    private RecyclerView recyclerView;
//    private ImageView iv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initView();
//
//}
//
//
//
//    private void initView() {
//        et = (EditText) findViewById(R.id.et);
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        iv = (ImageView) findViewById(R.id.iv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        MyAdapter myAdapter = new MyAdapter(this);
//        recyclerView.setAdapter(myAdapter);
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new
//
//        }
//        });
//
//
//
//    }
//
//
//}
