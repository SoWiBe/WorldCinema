package com.example.batumicinema.ui.collections;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.batumicinema.R;

public class CollectionsFragment extends Fragment {

    private ImageView imageView;
    public CollectionsFragment() {

    }

    public static CollectionsFragment newInstance(String param1, String param2) {
        CollectionsFragment fragment = new CollectionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        imageView = view.findViewById(R.id.image_add_collection);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CreateCollection.class));
            }
        });
        return view;
    }
}