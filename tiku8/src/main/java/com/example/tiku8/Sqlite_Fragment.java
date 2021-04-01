package com.example.tiku8;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sqlite_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter2 myAdapter;
    private Sqlite sqlite;

    public Sqlite_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_1, container, false);
        sqlite = new Sqlite(getContext());
        initView(inflate);
        initData();

        return inflate;

    }

    private void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter2(getContext());
        recyclerView.setAdapter(myAdapter);
        registerForContextMenu(recyclerView);

    }

    private void initData() {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 21; i++) {

            students.add(new Student(R.drawable.ic_launcher_foreground, "代金龙", "14"));
        }
        myAdapter.additem(students);


    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                final int position = myAdapter.position;
                myAdapter.deleteitem(position);

                break;
            case 1:

                final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.window, null);
                Button btn = inflate.findViewById(R.id.btn1);
                Button btn1 = inflate.findViewById(R.id.btn2);
                final PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAtLocation(inflate, Gravity.CENTER,0,0);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int position1 = myAdapter.position;
                        EditText et = inflate.findViewById(R.id.et);
                        String string = et.getText().toString();
                        sqlite.update(myAdapter.list.get(position1).getName(),string);
                        myAdapter.list.get(position1).setName(string);
                        myAdapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                    }
                });



                break;
        }
        return super.onContextItemSelected(item);
    }
}
