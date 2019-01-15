package com.ren.kai.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ren.kai.R;
import com.ren.kai.entity.Voice;
import com.ren.kai.modle.InspectionModle;
import com.ren.kai.presenter.InspectionPresenter;
import com.ren.kai.ui.contract.InspectionContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/7.
 */

public class InspectionFragment extends BaseFragment<InspectionPresenter, InspectionModle> implements InspectionContract.View {

    @BindView(R.id.lv)
    ListView listView;

    private List<Voice> beans;
    private MyAdapter adapter;

    @Override
    public int layoutResId() {
        return R.layout.inspection_fragment;
    }

    @Override
    public void onBind(Bundle savedInstanceState) {
        beans=new ArrayList<>();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return beans.size();
        }

        @Override
        public Object getItem(int position) {
            return beans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(parent.getContext());
            textView.setTextColor(Color.RED);
            textView.setText(beans.get(position).getStrLength());
            return textView;
        }
    }
}
