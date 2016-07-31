package com.ciandt.dojoandroid.marvelsapp.utils.fragments.implementations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.ciandt.dojoandroid.marvelsapp.R;
import com.ciandt.dojoandroid.marvelsapp.interfaces.MarvelServiceBase;
import com.ciandt.dojoandroid.marvelsapp.models.schemadata.Data;
import com.ciandt.dojoandroid.marvelsapp.services.MarvelService;

import java.util.ArrayList;

abstract public class ListFragmentUtil<T> extends ListFragment implements AbsListView.OnScrollListener {

    private ArrayList<T> mList;
    private Integer mTotal;
    private MarvelServiceBase mMarvelServiceBase;
    private ListAdapter mListAdapter;
    private final String KEY_MTOTAL = "KEY_MTOTAL";

    public ArrayList<T> getMList() {
        return mList;
    }

    public void setMList(ArrayList<T> mList) {
        this.mList = mList;
    }

    public MarvelServiceBase getMMarvelServiceBase() {
        return mMarvelServiceBase;
    }

    public void setAdapter(ListAdapter mAdapter) {
        mListAdapter = mAdapter;
        if (getListAdapter() == null)
            setListAdapter(mListAdapter);
    }

    public Integer getMTotal() {
        return mTotal;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_MTOTAL, mTotal);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mList = new ArrayList<T>();
        } else {
            mTotal = savedInstanceState.getInt(KEY_MTOTAL);
        }
        mMarvelServiceBase = MarvelService.getService(getResources().getString(R.string.base_url));
    }

    @Override
    public void onActivityCreated(Bundle saveInstantState) {
        super.onActivityCreated(saveInstantState);
        getListView().setOnScrollListener(this);
        if (mList.size() == 0)
            requestAPI();
    }

    @Override
    public void setListShown(boolean shown) {
        super.setListShown(shown);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    protected void requestAPI() {
        setListShown(false);
    }

    public void detachSplashScreen() {
        ListAdapter listAdapter = mListAdapter;
        FragmentManager fragmentManager = getFragmentManager();
        if (listAdapter != null && !listAdapter.isEmpty()) {
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(getResources().getString(R.string.fragment_splash));
            if (fragmentByTag != null)
                fragmentManager.beginTransaction().detach(fragmentByTag).commit();
        }
        setListShown(true);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        Integer position = mList.size() - 1;
        Boolean isNotEnd = mList.size() < mTotal;
        Boolean scrollInEnd = absListView.getLastVisiblePosition() == position && i == 1;
        if (isNotEnd && scrollInEnd)
            requestAPI();
        else if (!isNotEnd && scrollInEnd)
            Toast.makeText(getActivity(), String.format("%d de %d", mList.size(), mTotal), Toast.LENGTH_LONG).show();
    }

    public void addItens(Data data) {
        mTotal = data.getTotal();
        if (getMTotal() > 0)
            mList.addAll(data.getResults());
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
    }
}
