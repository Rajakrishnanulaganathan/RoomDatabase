package com.imdb.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.imdb.roomdatabase.databinding.ItemViewBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<User> mUserArrayList;

    public UserListAdapter(Context applicationContext, ArrayList<User> users) {
        mContext=applicationContext;
        mUserArrayList=users;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemViewBinding itemViewBinding= DataBindingUtil.inflate(layoutInflater,R.layout.item_view,parent,false);
        return new UserListViewHolder(itemViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, final int position) {
        final User user=mUserArrayList.get(position) ;
        Log.d("username",user.mUserName);
        holder.itemViewBinding.setUser(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 User user1=mUserArrayList.get(position);
                 Utils.mPhone=user1.mUserPhone;
                 Intent intent=new Intent(mContext,DetailsActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserArrayList.size();
    }

    public void setList(List<User> users) {
        mUserArrayList=users;
        notifyDataSetChanged();


    }

    class UserListViewHolder extends RecyclerView.ViewHolder {
        ItemViewBinding itemViewBinding;
        public UserListViewHolder(@NonNull ItemViewBinding itemView) {
            super(itemView.getRoot());
            itemViewBinding= DataBindingUtil.bind(itemView.getRoot());

        }
    }
}
