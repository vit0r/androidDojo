package com.ciandt.dojoandroid.marvelsapp.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.ciandt.dojoandroid.marvelsapp.R;
import com.ciandt.dojoandroid.marvelsapp.fragments.character.CharacterListFragment;
import com.ciandt.dojoandroid.marvelsapp.fragments.splash.SplashFragment;

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            setFragments();
    }

    protected void setFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setArguments(getIntent().getExtras());
        fragmentTransaction.add(R.id.fragment_container, splashFragment, getResources().getString(R.string.fragment_splash)).disallowAddToBackStack();

        CharacterListFragment characterListFragment = new CharacterListFragment();
        characterListFragment.setArguments(getIntent().getExtras());
        fragmentTransaction.add(R.id.fragment_container, characterListFragment, getResources().getString(R.string.character_list_fragment));
        fragmentTransaction.commit();
    }
}
