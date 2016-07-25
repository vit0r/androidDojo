package com.ciandt.dojoandroid.marvelsapp.interfaces;

import com.ciandt.dojoandroid.marvelsapp.models.character.Character;
import com.ciandt.dojoandroid.marvelsapp.models.comic.Comic;
import com.ciandt.dojoandroid.marvelsapp.models.schemadata.SchemaData;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by vnaraujo on 21/07/2016.
 */
public interface MarvelServiceBase {

    @GET("characters")
    Observable<SchemaData<Character>> getCharacters(@QueryMap Map<String, String> params);

    @GET("comics")
    Observable<SchemaData<Comic>> getComics(@QueryMap Map<String, String> params);
}