package com.ciandt.dojoandroid.marvelsapp.adapters.character;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.dojoandroid.marvelsapp.R;
import com.ciandt.dojoandroid.marvelsapp.fragments.character.CharacterDetailDialogFragment;
import com.ciandt.dojoandroid.marvelsapp.fragments.comic.ComicListFragment;
import com.ciandt.dojoandroid.marvelsapp.models.character.Character;
import com.ciandt.dojoandroid.marvelsapp.utils.adapters.implementations.AdapterUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vnaraujo on 25/07/2016.
 */
public class CharacterAdapter extends AdapterUtil<Character>{
    public CharacterAdapter(Integer layoutId, Context context, FragmentManager fragmentManager, ArrayList<Character> mList) {
        super(layoutId, context, fragmentManager, mList);
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        super.getView(position, null, viewGroup);
        View layout = getLayout();
        final Character character = getmList().get(position);
        ImageView imageView = ((ImageView) layout.findViewById(R.id.thumbnail));
        TextView textViewName = ((TextView) layout.findViewById(R.id.name));
        String thumbnail = String.format("%s.%s", character.getThumbnail().getPath(), character.getThumbnail().getExtension());
        Picasso.with(getContext()).load(thumbnail).into(imageView);
        textViewName.setText(character.getName());
        textViewName.setTextColor(Color.DKGRAY);
        Button btDescription = (Button) layout.findViewById(R.id.show_description);
        Button btComics = (Button) layout.findViewById(R.id.show_comic_character);
        if (!character.getDescription().isEmpty()) {
            btDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("description", character.getDescription());
                    CharacterDetailDialogFragment characterDetailDialogFragment = new CharacterDetailDialogFragment();
                    characterDetailDialogFragment.setArguments(bundle);
                    characterDetailDialogFragment.show(getFragmentManager(), getContext().getResources().getString(R.string.dialog_character_detail));
                }
            });
        } else {
            btDescription.setEnabled(false);
        }
        if (character.getComics().getReturned() > 0) {
            btComics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("CHARACTER_ID", character.getId());
                    ComicListFragment comicListFragment = new ComicListFragment();
                    comicListFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    String comicTag = getContext().getResources().getString(R.string.character_comics_list_fragment);
                    fragmentTransaction.add(R.id.fragment_container, comicListFragment, comicTag);
                    String characterTag = getContext().getResources().getString(R.string.character_list_fragment);
                    Fragment fragment = getFragmentManager().findFragmentByTag(characterTag);
                    fragmentTransaction.detach(fragment).addToBackStack(characterTag);
                    fragmentTransaction.commit();
                }
            });
        } else {
            btComics.setEnabled(false);
        }
        layout.setBackgroundColor((position % 2 == 0) ? Color.TRANSPARENT : Color.LTGRAY);
        return layout;
    }
}
