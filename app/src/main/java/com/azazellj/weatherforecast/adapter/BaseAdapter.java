package com.azazellj.weatherforecast.adapter;

/**
 * Created by azazellj on 27.05.16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.azazellj.weatherforecast.adapter.holder.BaseHolder;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class BaseAdapter<E, VH extends BaseHolder<E>> extends RecyclerView.Adapter<VH> {
    public List<E> mItems = Lists.newArrayList();

    public void setItems(List<E> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void addItems(List<E> items) {
        this.mItems.addAll(items);
        notifyItemRangeInserted(getItemCount() - items.size(), items.size());
    }

    public E getItem(int position) {
        return this.mItems.get(position);
    }

    public void deleteAll() {
        this.mItems.clear();
    }

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(final VH holder, final int position);

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }
}
