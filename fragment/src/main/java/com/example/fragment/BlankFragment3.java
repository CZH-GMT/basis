package com.example.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment {

    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        ImageView viewById = inflate.findViewById(R.id.iv);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewById, View.SCALE_Y, 0, 2);
        objectAnimator.setDuration(10000).start();


    }
}
