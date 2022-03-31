package com.example.wearbatumicinema;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SignInBlank extends Fragment {


    public SignInBlank() {

    }

    public static SignInBlank newInstance(String param1, String param2) {
        SignInBlank fragment = new SignInBlank();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_blank, container, false);
        return view;
    }
}