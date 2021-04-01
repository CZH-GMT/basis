package com.example.frangmentchuanzhi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {

    private View view;
    private ViewHolder viewHolder;

    public ViewHolder getViewHolder() {
        return viewHolder;
    }

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_blank2, container, false);
        initView();
        return view;

    }

    private void initView() {
        viewHolder = new ViewHolder(view);
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                BlankFragment blankFragment = activity.getBlankFragment();
                EditText et1 = blankFragment.getViewHolder().et1;
                String string = et1.getText().toString();
                viewHolder.et1.setText(string);
            }
        });

    }

    public static
    class ViewHolder {
        public View rootView;
        public EditText et1;
        public Button btn;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.et1 = (EditText) rootView.findViewById(R.id.et1);
            this.btn = (Button) rootView.findViewById(R.id.btn);
        }

    }
}
