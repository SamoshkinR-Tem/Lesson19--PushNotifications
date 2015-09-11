package com.l19_pushnotification;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by R-Tem on 09.09.2015.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private static final int TYPE_ITEM_LIGHT = 0;
    private static final int TYPE_ITEM_DARK = 1;

    private List mData;

    public RvAdapter(List _Data) {
        mData = _Data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup _viewGroup, int viewType) {
        View itemView = LayoutInflater.from(_viewGroup.getContext()).inflate(R.layout.rec_view_item, _viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder _ViewHolder, int i) {
        _ViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public int getItemViewColor(int position) {
        return position % 2 == 0 ? TYPE_ITEM_LIGHT : TYPE_ITEM_DARK;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View _view) {
            super(_view);
            mView = _view;
        }

        public void onBind() {


            final int type = getItemViewColor(getPosition());
            switch (type) {
                case TYPE_ITEM_LIGHT:
                    itemView.setBackgroundColor(Color.YELLOW);
                    break;

                case TYPE_ITEM_DARK:
                    itemView.setBackgroundColor(Color.DKGRAY);
                    break;
            }
        }
    }
}
