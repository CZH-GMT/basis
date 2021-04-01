package com.example.a6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Four_Fragment extends Fragment {

    private WebView wb;

    public Four_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_four_, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        wb = inflate.findViewById(R.id.wb);
        wb.loadUrl("https://www.baidu.com/?tn=40020637_6_oem_dg");
        wb.setWebViewClient(new WebViewClient());
    }
}
