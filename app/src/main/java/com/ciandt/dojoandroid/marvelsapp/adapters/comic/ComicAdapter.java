package com.ciandt.dojoandroid.marvelsapp.adapters.comic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.dojoandroid.marvelsapp.R;
import com.ciandt.dojoandroid.marvelsapp.models.comic.Comic;
import com.ciandt.dojoandroid.marvelsapp.utils.adapters.implementations.AdapterUtil;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by vnaraujo on 25/07/2016.
 */
public class ComicAdapter extends AdapterUtil<Comic> {
    public ComicAdapter(Integer layoutId, Context context, FragmentManager fragmentManager, ArrayList<Comic> mList) {
        super(layoutId, context, fragmentManager, mList);
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        super.getView(position, null, viewGroup);
        View layout = getLayout();
        final Comic comic = getmList().get(position);
        ImageView imageView = ((ImageView) layout.findViewById(R.id.thumbnail));
        TextView textViewTitle = ((TextView) layout.findViewById(R.id.title));
        TextView textViewModified = ((TextView) layout.findViewById(R.id.modified));
        String thumbnail = String.format("%s.%s", comic.getThumbnail().getPath(), comic.getThumbnail().getExtension());
        Picasso.with(getContext()).load(thumbnail).into(imageView);
        textViewTitle.setText(comic.getTitle());
        textViewTitle.setTextColor(Color.DKGRAY);
        textViewModified.setText(new SimpleDateFormat("dd/MM/yyyy").format(comic.getModified()));
        layout.setBackgroundColor((position % 2 == 0) ? Color.TRANSPARENT : Color.LTGRAY);
        return layout;
    }
}
