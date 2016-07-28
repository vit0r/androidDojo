package com.ciandt.dojoandroid.marvelsapp.fragments.comic;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ciandt.dojoandroid.marvelsapp.R;
import com.ciandt.dojoandroid.marvelsapp.adapters.comic.ComicAdapter;
import com.ciandt.dojoandroid.marvelsapp.models.comic.Comic;
import com.ciandt.dojoandroid.marvelsapp.models.schemadata.SchemaData;
import com.ciandt.dojoandroid.marvelsapp.utils.adapters.interfaces.AdapterUtilInterface;
import com.ciandt.dojoandroid.marvelsapp.utils.commons.Common;
import com.ciandt.dojoandroid.marvelsapp.utils.fragments.implementations.ListFragmentUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vnaraujo on 20/07/2016.
 */
public class ComicListFragment extends ListFragmentUtil<Comic> implements AdapterUtilInterface<Comic> {

    private final String KEY_CHARACTER_ID = "CHARACTER_ID";
    private final String KEY_LIST_NAME = "COMIC_LIST";
    private Integer characterId;
    private ComicAdapter comicAdapter;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(KEY_LIST_NAME, getMList());
        savedInstanceState.putInt(KEY_CHARACTER_ID, characterId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<Comic> mList = savedInstanceState.getParcelableArrayList(KEY_LIST_NAME);
            setMList(mList);
            characterId = savedInstanceState.getInt(KEY_CHARACTER_ID);
            notifyAdapter();
        } else {
            characterId = getArguments().getInt(KEY_CHARACTER_ID);
        }
    }

    public void notifyAdapter() {
        if (comicAdapter == null) {
            comicAdapter =
                    new ComicAdapter(R.layout.character_comic_list, getActivity(), getFragmentManager(), getMList());
        } else {
            comicAdapter.notifyDataSetChanged();
        }
        setAdapter(comicAdapter);
    }

    @Override
    public void requestAPI() {
        super.requestAPI();
        Map<String, String> additionalParams = new HashMap<String, String>();
        additionalParams.put("characters",characterId.toString());
        getMMarvelServiceBase().getComics(Common.getParams(getResources(), getMList().size(),additionalParams))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .timeout(30, TimeUnit.SECONDS)
                .retry(3)
                .subscribe(new Observer<SchemaData<Comic>>() {
                    @Override
                    public void onCompleted() {
                        detachSplashScreen();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                        Log.e("marvelServiceBase", e.toString());
                    }

                    @Override
                    public void onNext(SchemaData schemaData) {
                        addItens(schemaData.getData());
                        notifyAdapter();
                    }
                });
    }
}