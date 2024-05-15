package com.example.watttracker;

import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class profileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public profileFragment() {
        // Required empty public constructor
    }

    public void openGithubProfile(View view) {
        // Open GitHub profile URL
        String url = "https://github.com/your_username";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void openInstagramProfile(View view) {
        // Open Instagram profile URL
        String url = "https://www.instagram.com/wandegreat";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find image views
        ImageView imageViewGithub = view.findViewById(R.id.imageViewGithub);
        ImageView imageViewInstagram = view.findViewById(R.id.imageViewInstagram);
        ImageView imageView = view.findViewById(R.id.imageView3);

        // Set click listeners
        imageViewGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGithubProfile(v);
            }
        });

        imageViewInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagramProfile(v);
            }
        });

        // Set color filter
        imageView.setColorFilter(Color.parseColor("#f6f6f6"), PorterDuff.Mode.SRC_IN);

        return view;
    }
}