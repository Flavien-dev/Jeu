package com.chapfla.jeu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView TV_main;
    private Button BT_add_player;
    private EditText ET_first_player;
    private EditText ET_second_player;
    private Button BT_jouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV_main = findViewById(R.id.text_main);
        BT_add_player = findViewById(R.id.button_add_player);
        ET_first_player = findViewById(R.id.main_edit_first_player);
        ET_second_player = findViewById(R.id.main_edit_second_player);
        BT_jouer = findViewById(R.id.button_playToGame);

        ET_first_player.setVisibility(View.INVISIBLE);
        ET_second_player.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        BT_add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ET_first_player.setVisibility(View.VISIBLE);
                ET_first_player.requestFocus();
                if (!ET_first_player.getText().toString().equals("")) {
                    ET_second_player.setVisibility(View.VISIBLE);
                    ET_second_player.requestFocus();
                }
            }
        });

        BT_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vérifierChampsSaisie()) {
                    Intent GameActivity = new Intent(MainActivity.this,GameActivity.class);
                    finish();
                    startActivity(GameActivity);
                }
            }
        });
    }

    public boolean vérifierChampsSaisie() {
        if (!ET_first_player.getText().toString().equals("") && !ET_second_player.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}