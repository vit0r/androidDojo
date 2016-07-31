package com.ciandt.dojoandroid.marvelsapp.utils.adapters.implementations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by vnaraujo on 08/07/2016.
 */
abstract public class AdapterUtil<T> extends BaseAdapter {

    private Context context;
    private ArrayList<T> mList;
    private LayoutInflater inflater;
    private FragmentManager fragmentManager;
    private Integer layoutId;
    private View layout;

    public AdapterUtil(final Integer layoutId, final Context context, final FragmentManager fragmentManager, ArrayList<T> mList) {
        this.layoutId = layoutId;
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mList = mList;
        this.fragmentManager = fragmentManager;
    }

    public View getLayout() {
        return layout;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<T> getMList() {
        return mList;
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        layout = inflater.inflate(layoutId, null, true);
        return layout;
    }
}
