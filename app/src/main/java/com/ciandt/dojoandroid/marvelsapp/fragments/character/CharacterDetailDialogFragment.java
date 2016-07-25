package com.ciandt.dojoandroid.marvelsapp.fragments.character;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ciandt.dojoandroid.marvelsapp.R;

/**
 * Created by Vitor on 14/07/2016.
 */
public class CharacterDetailDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_character_detail, container, true);
        if (getArguments() != null) {
            TextView textView = (TextView) view.findViewById(R.id.dialog_text_description);
            textView.setText(getArguments().get("description").toString());
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        return view;
    }
}