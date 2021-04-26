package com.example.apichat.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apichat.MainActivity;
import com.example.apichat.R;
import com.example.apichat.adapters.MessageAdapter;
import com.example.apichat.data.network.ApiServices;
import com.example.apichat.data.network.RetrofitBuilder;
import com.example.apichat.data.pojo.ScreenTwo;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRoomFragment extends Fragment {

    private ApiServices apiServices;
    private Call<ScreenTwo> call;

    private RecyclerView recyclerView;
    private MessageAdapter message_adapter;
    private TextView tv_name;
    private ImageView iv_pic;

    public ChatRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init views
        tv_name = view.findViewById(R.id.message_tv_name);
        iv_pic = view.findViewById(R.id.message_iv);
        recyclerView = view.findViewById(R.id.message_recyclerView);
        recyclerView.setHasFixedSize(true);

        view.findViewById(R.id.message_arrow).setOnClickListener(v -> MainActivity.navControllerMain.popBackStack());

        message_adapter = new MessageAdapter(getActivity());
        apiServices = RetrofitBuilder.getClient().create(ApiServices.class);
        call = apiServices.getData2();
        call.enqueue(new Callback<ScreenTwo>() {
            @Override
            public void onResponse(Call<ScreenTwo> call, Response<ScreenTwo> response) {
                //init data
                Picasso.with(getActivity()).load(Uri.parse(response.body().getPic())).into(iv_pic);
                tv_name.setText(response.body().getName());

                message_adapter.setPic(response.body().getPic());
                message_adapter.setMyMessages(response.body().getMessages());
                recyclerView.setAdapter(message_adapter);
            }

            @Override
            public void onFailure(Call<ScreenTwo> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}