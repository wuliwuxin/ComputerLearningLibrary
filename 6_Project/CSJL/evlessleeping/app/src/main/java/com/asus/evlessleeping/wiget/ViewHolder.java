package com.asus.evlessleeping.wiget;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder {


    private ViewHolder() {
    }

    public static <T extends View> T getView(View mView, int id) {

        SparseArray<View> mViews = (SparseArray<View>) mView.getTag();

        if (mViews == null) {

            mViews = new SparseArray<View>();
            mView.setTag(mViews);
        }

        View v = mViews.get(id);

        if (v == null) {

            v = mView.findViewById(id);

            mViews.put(id, v);
        }

        return (T) v;

    }
}
