package com.example.apichat.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apichat.R;
import com.example.apichat.data.pojo.Information;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {
    private Context context;
    private List<Information> infos=new ArrayList<>();
    OnClickListener listener;

    public RecentAdapter(Context context, OnClickListener listener) {
        this.context=context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chat_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Information d=infos.get(position);
        if(!d.getPic().isEmpty())
            Picasso.with(context).load(Uri.parse(d.getPic())).into(holder.iv_pic);
        holder.tv_name.setText(d.getName());
        holder.tv_message.setText(d.getMessage());
        holder.tv_time.setText(d.getTime());
        if(d.getNew()!=0) {
            holder.tv_new.setVisibility(View.VISIBLE);
            holder.tv_new.setText(String.valueOf(d.getNew()));
        }
        if(position%2==0) {
            holder.constraint.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_next_chat));
        }
        holder.name=d.getName();
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_pic;
        TextView tv_name,tv_message,tv_time,tv_new;
        String name="";
        ConstraintLayout constraint;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic=itemView.findViewById(R.id.customChat_iv);
            tv_name=itemView.findViewById(R.id.customChat_tv_name);
            tv_message=itemView.findViewById(R.id.customChat_tv_message);
            tv_time=itemView.findViewById(R.id.customChat_tv_time);
            tv_new=itemView.findViewById(R.id.customChat_tv_new);
            constraint=itemView.findViewById(R.id.customChat_constraintLayout);

            itemView.setOnClickListener(v -> listener.onItemClick(name));
        }
    }

    public void setInfos(List<Information> infos) {
        this.infos = infos;
        this.notifyDataSetChanged();
    }

    public interface OnClickListener
    {
        void onItemClick(String name);
    }
}

