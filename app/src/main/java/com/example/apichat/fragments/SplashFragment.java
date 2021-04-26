package com.example.apichat.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.apichat.MainActivity;
import com.example.apichat.R;
import java.util.Objects;

public class SplashFragment extends Fragment {

    private Animation anim_left;
    private ImageView iv;
    private TextView tv;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();

        anim_left = AnimationUtils.loadAnimation(getContext(), R.anim.anim_left);
        iv=view.findViewById(R.id.splash_iv_light);
        tv=view.findViewById(R.id.splash_tv_muse);
        iv.setAnimation(anim_left);
        tv.setAnimation(anim_left);

        new Handler().postDelayed(() -> MainActivity.navControllerMain.navigate(R.id.action_splashFragment_to_homeFragment), 1000);

    }
}