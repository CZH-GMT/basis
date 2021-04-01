package com.example.fragmenthuichuanzhi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class MyFragment extends Fragment {
    private EditText etf;
    private Button btnf;

    public EditText getEtf() {


        return etf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment, container, false);

        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        etf=inflate.findViewById(R.id.etf);
        btnf=inflate.findViewById(R.id.btnf);
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                String string = activity.getEt().getText().toString();
                etf.setText(string);

            }
        });


    }
}
