package com.example.apichat.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apichat.R;
import com.example.apichat.data.pojo.Information;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private Context context;
    private List<Information> infos=new ArrayList<>();

    public FavoriteAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_favorite,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Information d=infos.get(position);
        if(!d.getPic().isEmpty())
            Picasso.get().load(Uri.parse(d.getPic())).into(holder.iv_pic);
        holder.tv_name.setText(d.getName());
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_pic;
        TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic=itemView.findViewById(R.id.custom_favorite_iv);
            tv_name=itemView.findViewById(R.id.custom_favorite_tv_name);
        }
    }

    public void setInfos(List<Information> infos) {
        this.infos = infos;
        this.notifyDataSetChanged();
    }
}

