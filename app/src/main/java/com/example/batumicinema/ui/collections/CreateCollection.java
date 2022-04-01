package com.example.batumicinema.ui.collections;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.batumicinema.ui.MainMenu.MainActivity;
import com.example.batumicinema.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CreateCollection extends AppCompatActivity {

    //кнопка выбора иконки
    private MaterialButton btnChooseIcon;

    //для получения выбранной иконки
    private Bundle bundle;

    //выбранная иконка
    private ImageView iconChoose;

    //поле ввода названия
    private TextInputEditText editTitle;

    //кнопка "Сохранить" коллекцию
    private Button btnSaveCollection;

    //получение данных из памяти
    private SharedPreferences sharedCollections;
    //изменение данных
    private SharedPreferences.Editor editor;

    private

    private Set<String> set = new HashSet<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);

        //инициализация всех полей
        btnChooseIcon = findViewById(R.id.btnChooseIcon);
        iconChoose = findViewById(R.id.iconChoose);
        btnSaveCollection = findViewById(R.id.btnSaveCollection);
        editTitle = findViewById(R.id.text_text_title_collection);


        //получение данных, если они есть
        bundle = getIntent().getExtras();
        if(bundle != null) {
            //установка полученной картинки для коллекции
            iconChoose.setImageResource(bundle.getInt("icon"));
        }

        //два клика - сохранение коллекции и выбор иконки
        btnSaveCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set.add(editTitle.getText().toString());
                editor.putStringSet("titles", set);
                startActivity(new Intent(CreateCollection.this, MainActivity.class));
            }
        });
        btnChooseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateCollection.this, ChooseIconActivity.class));
            }
        });


    }

    //возврат на предыдущую страницу
    public void goBack(View view) {
        finish();
    }


}