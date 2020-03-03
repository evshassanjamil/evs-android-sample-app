package com.evs.android.mysampleapp.week8.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.android.mysampleapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Item> dataList = new ArrayList<>();
    private OnListItemClickListener mListener;
    @LayoutRes
    private final int layoutResIdContent = R.layout.list_item;
    private static final int VIEW_TYPE_ITEM1 = 1;
    //private static final int VIEW_TYPE_ITEM2 = 2;

    public MyRecyclerViewAdapter(Context context, ArrayList<Item> listData) {
        this.mContext = context;
        if (listData == null)
            dataList.clear();
        else
            dataList = (ArrayList<Item>) listData.clone();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*if (viewType == VIEW_TYPE_ITEM1) {
            return new ItemContentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(layoutResIdContent, parent, false));
        }*/

        return new ItemContentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(layoutResIdContent, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemContentViewHolder) {
            setViewsToItemContent(getItem(position), (ItemContentViewHolder) holder);
            ((ItemContentViewHolder) holder).bind(position, getItem(position), mListener);
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        /*return (dataList.size() < position && position % 3 == 0)
         ? VIEW_TYPE_ITEM2
         : VIEW_TYPE_ITEM1;*/
        return VIEW_TYPE_ITEM1;
    }

    private Item getItem(int position) {
        return dataList.get(position);
    }

    public long getItemId(int position) {
        return dataList.get(position).getId();
    }

    public void setOnListItemClickListener(OnListItemClickListener listener) {
        this.mListener = listener;
    }

    private class ItemContentViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivImage;
        final TextView tvName;
        final TextView tvCategory;
        final TextView tvPrice;

        private ItemContentViewHolder(View view) {
            super(view);
            ivImage = view.findViewById(R.id.ivImage);
            tvName = view.findViewById(R.id.tvName);
            tvCategory = view.findViewById(R.id.tvCategory);
            tvPrice = view.findViewById(R.id.tvPrice);
        }

        void bind(final int position, final Item item, final OnListItemClickListener listener) {
            if (item == null)
                return;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position, item);
                }
            });
        }
    }

    private void setViewsToItemContent(final Item item, ItemContentViewHolder viewHolder) {
        if (item == null)
            return;

        loadImage(viewHolder, item.getImageUrl());
        viewHolder.tvName.setText(item.getName());
        viewHolder.tvCategory.setText(item.getCategory());
        viewHolder.tvPrice.setText(String.valueOf(item.getPrice()));
    }

    private void loadImage(ItemContentViewHolder viewHolder, String url) {
        final Picasso picasso = Picasso.with(mContext);
        picasso.setIndicatorsEnabled(false);
        picasso.load(url)
                .fit()
                .centerCrop()
                .placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_filter_hdr))
                .error(ContextCompat.getDrawable(mContext, R.drawable.ic_broken_image))
                .transform(new CropCircleTransformation())
                .into(viewHolder.ivImage);

    }
}
