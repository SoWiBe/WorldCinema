package com.example.batumicinema.ui.collections;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.batumicinema.R;
import com.example.batumicinema.adapters.CollectionsAdapter;

import java.util.ArrayList;

public class CollectionsFragment extends Fragment {

    private ImageView imageView;
    private CollectionsAdapter collectionsAdapter;
    private ArrayList<Collection> collections = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
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
        recyclerView = view.findViewById(R.id.recyclerCollections);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CreateCollection.class));
            }
        });
        collections.add(new Collection(R.drawable.heart, "Избранное"));
        collections.add(new Collection(R.drawable.time, "Когда-нибудь"));
        collections.add(new Collection(R.drawable.plane, "В поездку"));

        collectionsAdapter = new CollectionsAdapter(getContext(), collections);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(collectionsAdapter);
        return view;
    }
}