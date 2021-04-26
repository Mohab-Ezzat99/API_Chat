package com.example.apichat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apichat.MainActivity;
import com.example.apichat.R;
import com.example.apichat.adapters.FavoriteAdapter;
import com.example.apichat.adapters.RecentAdapter;
import com.example.apichat.data.network.ApiServices;
import com.example.apichat.data.network.RetrofitBuilder;
import com.example.apichat.data.pojo.ScreenOne;
import com.example.apichat.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ApiServices apiServices;
    private Call<ScreenOne> call;

    private FragmentHomeBinding fragmentHomeBinding;
    private FavoriteAdapter favorite_adapter;
    private RecentAdapter recent_adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        fragmentHomeBinding= FragmentHomeBinding.inflate(inflater, container, false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.home_iv_arrow).setOnClickListener(v -> requireActivity().finish());
        fragmentHomeBinding.homeNav.setSelectedItemId(R.id.mainMenu_home);

        apiServices = RetrofitBuilder.getClient().create(ApiServices.class);
        call = apiServices.getData();
        call.enqueue(new Callback<ScreenOne>() {
            @Override
            public void onResponse(@NonNull Call<ScreenOne> call, @NonNull Response<ScreenOne> response) {
                favorite_adapter = new FavoriteAdapter(getActivity());
                recent_adapter = new RecentAdapter(getActivity(), name -> {
                    if (name.equals("Alan Byrd"))
                        MainActivity.navControllerMain.navigate(R.id.action_homeFragment_to_chatRoomFragment);
                });

                //init favorite
                assert response.body() != null;
                favorite_adapter.setInfos(response.body().getFavorites());
                fragmentHomeBinding.homeRvFavorite.setAdapter(favorite_adapter);

                //init recent
                recent_adapter.setInfos(response.body().getRecent());
                fragmentHomeBinding.homeRvRecent.setAdapter(recent_adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ScreenOne> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}