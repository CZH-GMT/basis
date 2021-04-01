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
public class BlankFragment extends Fragment {

    private ImageView iv;

    public BlankFragment() {
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
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewById, View.ALPHA, 0, 1);
        objectAnimator.setDuration(10000).start();


    }
}
