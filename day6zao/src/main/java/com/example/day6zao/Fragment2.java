package com.example.day6zao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Fragment2 extends Fragment {


    private ImageView iv;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragmenttwo, container, false);
        initView(inflate);
        return inflate;


    }

    private void initView(View inflate) {
        iv = inflate.findViewById(R.id.iv);
        btn=inflate.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                int image = activity.getImage();
                iv.setImageResource(image);
            }
        });

//        Bundle arguments = getArguments();
//        int image = arguments.getInt("image");
//        iv.setImageResource(image);


    }


}
