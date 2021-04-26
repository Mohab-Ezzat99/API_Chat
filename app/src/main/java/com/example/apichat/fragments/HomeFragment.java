package com.example.apichat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apichat.MainActivity;
import com.example.apichat.R;
import com.example.apichat.adapters.FavoriteAdapter;
import com.example.apichat.adapters.RecentAdapter;
import com.example.apichat.data.network.ApiServices;
import com.example.apichat.data.network.RetrofitBuilder;
import com.example.apichat.data.pojo.ScreenOne;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ApiServices apiServices;
    private Call<ScreenOne> call;

    private RecyclerView rv_favorite, rv_recent;
    private BottomNavigationView bottomNavigationView;
    private FavoriteAdapter favorite_adapter;
    private RecentAdapter recent_adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init views
        bottomNavigationView = view.findViewById(R.id.home_nav);
        bottomNavigationView.setSelectedItemId(R.id.mainMenu_home);
        rv_favorite = view.findViewById(R.id.home_rv_favorite);
        rv_recent = view.findViewById(R.id.home_rv_recent);
        rv_favorite.setHasFixedSize(true);
        rv_recent.setHasFixedSize(true);

        view.findViewById(R.id.home_iv_arrow).setOnClickListener(v -> requireActivity().finish());

        apiServices = RetrofitBuilder.getClient().create(ApiServices.class);
        call = apiServices.getData();
        call.enqueue(new Callback<ScreenOne>() {
            @Override
            public void onResponse(Call<ScreenOne> call, Response<ScreenOne> response) {
                favorite_adapter = new FavoriteAdapter(getActivity());
                recent_adapter = new RecentAdapter(getActivity(), new RecentAdapter.OnClickListener() {
                    @Override
                    public void onItemClick(String name) {
                        if (name.equals("Alan Byrd"))
                            MainActivity.navControllerMain.navigate(R.id.action_homeFragment_to_chatRoomFragment);
                    }
                });

                //init favorite
                assert response.body() != null;
                favorite_adapter.setInfos(response.body().getFavorites());
                rv_favorite.setAdapter(favorite_adapter);

                //init recent
                recent_adapter.setInfos(response.body().getRecent());
                rv_recent.setAdapter(recent_adapter);
            }

            @Override
            public void onFailure(Call<ScreenOne> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}