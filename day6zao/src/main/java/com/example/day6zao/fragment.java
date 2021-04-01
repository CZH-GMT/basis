package com.example.day6zao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment extends Fragment {

    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment, container, false);
        tv = inflate.findViewById(R.id.tv);
        return inflate;
    }
    private int connt=0;
    @Override
    public void onResume() {
        connt++;
        tv.setText(connt+"");
        super.onResume();
    }
}
