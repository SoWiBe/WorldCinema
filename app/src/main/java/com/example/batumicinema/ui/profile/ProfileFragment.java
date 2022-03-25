package com.example.batumicinema.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.batumicinema.Authorization.AuthorizationActivity;
import com.example.batumicinema.R;
import com.example.batumicinema.network.ProfileHandler;
import com.example.batumicinema.network.models.ProfileResponse;
import com.example.batumicinema.network.service.IApiService;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private static final String TAG = "ProfileFragment";
    private IApiService service = ProfileHandler.getInstance().getService();
    private String token;
    private TextView txtName;
    private TextView txtEmail;
    private ImageView imgProfile;
    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_profile, container, false);
        txtName = v.findViewById(R.id.txt_user_name);
        txtEmail = v.findViewById(R.id.txt_user_email);
        imgProfile = v.findViewById(R.id.img_user_photo);

        MaterialButton materialButton = v.findViewById(R.id.btnSignOut);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().remove("token").commit();
                startAuthorizationActivity();
            }
        });
        getUserInfo();
        return v;
    }

    private void startAuthorizationActivity(){
        startActivity(new Intent(getContext(), AuthorizationActivity.class));
    }

    private void getUserInfo(){
        AsyncTask.execute(() -> {
            service.getData(token).enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                    txtEmail.setText(response.body().get(0).getEmail());
                    txtName.setText(response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName());
                    Picasso.with(getContext()).load("http://cinema.areas.su/up/images/" + response.body().get(0).getAvatar()).into(imgProfile);
                }

                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}