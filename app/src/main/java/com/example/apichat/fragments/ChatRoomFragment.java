package com.example.apichat.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apichat.MainActivity;
import com.example.apichat.R;
import com.example.apichat.adapters.MessageAdapter;
import com.example.apichat.data.network.ApiServices;
import com.example.apichat.data.network.RetrofitBuilder;
import com.example.apichat.data.pojo.ScreenTwo;
import com.example.apichat.databinding.FragmentChatRoomBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRoomFragment extends Fragment {

    private ApiServices apiServices;
    private Call<ScreenTwo> call;

    FragmentChatRoomBinding fragmentChatRoomBinding;
    private MessageAdapter message_adapter;

    public ChatRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentChatRoomBinding = FragmentChatRoomBinding.inflate(inflater, container, false);
        return fragmentChatRoomBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentChatRoomBinding.messageRecyclerView.setHasFixedSize(true);
        view.findViewById(R.id.message_arrow).setOnClickListener(v -> MainActivity.navControllerMain.popBackStack());

        message_adapter = new MessageAdapter(getActivity());
        apiServices = RetrofitBuilder.getClient().create(ApiServices.class);
        call = apiServices.getData2();
        call.enqueue(new Callback<ScreenTwo>() {
            @Override
            public void onResponse(@NonNull Call<ScreenTwo> call, @NonNull Response<ScreenTwo> response) {
                //init data
                assert response.body() != null;
                Picasso.get().load(Uri.parse(response.body().getPic())).into(fragmentChatRoomBinding.messageIv);
                fragmentChatRoomBinding.messageTvName.setText(response.body().getName());

                message_adapter.setPic(response.body().getPic());
                message_adapter.setMyMessages(response.body().getMessages());
                fragmentChatRoomBinding.messageRecyclerView.setAdapter(message_adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ScreenTwo> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}