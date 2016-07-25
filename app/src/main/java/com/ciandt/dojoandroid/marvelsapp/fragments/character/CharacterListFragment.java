package com.ciandt.dojoandroid.marvelsapp.fragments.character;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ciandt.dojoandroid.marvelsapp.R;
import com.ciandt.dojoandroid.marvelsapp.adapters.character.CharacterAdapter;
import com.ciandt.dojoandroid.marvelsapp.models.character.Character;
import com.ciandt.dojoandroid.marvelsapp.models.schemadata.SchemaData;
import com.ciandt.dojoandroid.marvelsapp.utils.adapters.interfaces.AdapterUtilInterface;
import com.ciandt.dojoandroid.marvelsapp.utils.fragments.implementations.ListFragmentUtil;
import com.ciandt.dojoandroid.marvelsapp.utils.commons.Common;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CharacterListFragment extends ListFragmentUtil<Character> implements AdapterUtilInterface<Character> {
    private CharacterAdapter characterAdapter;
    private final String KEY_LIST_NAME = "CHARACTER_LIST";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(KEY_LIST_NAME, getmList());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<Character> mList = savedInstanceState.getParcelableArrayList(KEY_LIST_NAME);
            setmList(mList);
        }
    }

    public void notifyAdapter() {
        if (characterAdapter == null) {
            characterAdapter =
                    new CharacterAdapter(R.layout.fragment_character_list, getContext(), getFragmentManager(), getmList());
        } else {
            characterAdapter.notifyDataSetChanged();
        }
        setAdapter(characterAdapter);
    }

    @Override
    public void requestAPI() {
        super.requestAPI();
        getmMarvelServiceBase().getCharacters(Common.getParams(getResources(), getmList().size(),null))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .timeout(30, TimeUnit.SECONDS)
                .retry(3)
                .subscribe(new Observer<SchemaData<Character>>() {
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
