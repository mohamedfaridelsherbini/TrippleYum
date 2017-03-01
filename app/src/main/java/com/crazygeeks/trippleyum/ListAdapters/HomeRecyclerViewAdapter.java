package com.crazygeeks.trippleyum.ListAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crazygeeks.trippleyum.Fragments.HomeFragment.OnListFragmentInteractionListener;
import com.crazygeeks.trippleyum.Models.MainListModel;
import com.crazygeeks.trippleyum.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MainListModel} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private final List<MainListModel> mValues;
    private final OnListFragmentInteractionListener mListener;
    int favState = 0;
    Context mContext;
    public HomeRecyclerViewAdapter(Context context , List<MainListModel> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        favState = 0;
        holder.mIVfavourite.setImageResource(R.drawable.star_not_clicked);
        holder.mItem = mValues.get(position);
        holder.mUserName.setText(mValues.get(position).userName);
        holder.mPostContent.setText(mValues.get(position).postContent);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.push_up_list);
        holder.mainItemLayout.setAnimation(animation);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        favState = mValues.get(position).favState;
        if(favState == 0) {
            holder.mIVfavourite.setImageResource(R.drawable.star);
        }else{
            holder.mIVfavourite.setImageResource(R.drawable.star_not_clicked);
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mUserName;
        public final TextView mPostContent;
        public final ImageView mIVfavourite;
        public final ImageView mIVshare;
        public final LinearLayout mainItemLayout;
        public MainListModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mUserName = (TextView) view.findViewById(R.id.txtUserName);
            mPostContent = (TextView) view.findViewById(R.id.txtPost);
            mIVfavourite = (ImageView) view.findViewById(R.id.ivFavorite);
            mIVshare = (ImageView) view.findViewById(R.id.ivFavorite);
            mainItemLayout = (LinearLayout) view.findViewById(R.id.mainListItemLayout);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPostContent.getText() + "'";
        }
    }
}
