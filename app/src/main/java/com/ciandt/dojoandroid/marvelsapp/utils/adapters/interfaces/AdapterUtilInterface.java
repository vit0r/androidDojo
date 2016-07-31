package com.ciandt.dojoandroid.marvelsapp.utils.adapters.interfaces;

/**
 * Created by vnaraujo on 25/07/2016.
 */
public interface AdapterUtilInterface {
    /*Example
      public void notifyAdapter(){
        if (yourAdapter == null)
            yourAdapter = new YourAdapter(R.layout.yourlayout, getContext(), getFragmentManager(), getMList());
        else
            youAdapter.notifyDataSetChanged();

        setAdapter(yourAdapter);
     }
    */
    void notifyAdapter();
}
