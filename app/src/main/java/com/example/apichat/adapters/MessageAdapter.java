package com.example.apichat.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apichat.R;
import com.example.apichat.data.pojo.MyMassage;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private Context context;
    private List<MyMassage> myMessages=new ArrayList<>();
    private String pic=null;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_message_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyMassage m=myMessages.get(position);
        if(m.getSender()==0) {
            holder.tv_move.setVisibility(View.VISIBLE);
            holder.group.setVisibility(View.GONE);
            holder.tv_move.setText(m.getMessage());
        }
        if(m.getSender()==1) {
            holder.group.setVisibility(View.VISIBLE);
            holder.tv_move.setVisibility(View.GONE);
            holder.tv_white.setText(m.getMessage());
            if(!pic.isEmpty())
                Picasso.with(context).load(Uri.parse(pic)).into(holder.iv);
        }
    }

    @Override
    public int getItemCount() {
        return myMessages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_move,tv_white;
        Group group;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_move=itemView.findViewById(R.id.message_tv_blue);
            tv_white=itemView.findViewById(R.id.message_tv_white);
            group=itemView.findViewById(R.id.group_white_message);
            iv=itemView.findViewById(R.id.message_iv);
        }
    }

    public void setMyMessages(List<MyMassage> myMessages) {
        this.myMessages = myMessages;
        this.notifyDataSetChanged();
    }

    public void setPic(String pic) {
        this.pic = pic;
        this.notifyDataSetChanged();
    }
}
