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
 * Created by Vitor on 10/07/2016.
 */
public class CharacterDialogFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, true);
        view.setBackgroundColor(Color.TRANSPARENT);
        if (getArguments() != null) {
            TextView textView = (TextView) view.findViewById(R.id.dialog_text_description);
            textView.setText(getArguments().get("description").toString());
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
