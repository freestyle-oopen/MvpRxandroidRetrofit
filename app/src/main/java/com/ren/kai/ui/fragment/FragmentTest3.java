package com.ren.kai.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ren.kai.R;

/**
 * Created by Administrator on 2017/6/7.
 */

public class FragmentTest3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.test3, null);
        return inflate;
    }
}
