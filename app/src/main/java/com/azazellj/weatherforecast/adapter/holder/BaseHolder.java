package com.azazellj.weatherforecast.adapter.holder;

/**
 * Created by azazellj on 27.05.16.
 */

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class BaseHolder<E> extends RecyclerView.ViewHolder {
    public BaseHolder(View view) {
        super(view);

        RecyclerView.LayoutParams mParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(mParams);
    }

    public View getView() {
        return this.itemView;
    }

    public ViewDataBinding getBinding() {
        return DataBindingUtil.getBinding(getView());
    }
}