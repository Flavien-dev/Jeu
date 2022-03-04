package com.chapfla.jeu;

// importer les librairies
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // créer les objets de l'activité
    private TextView TV_main;
    private Button BT_add_player;
    private EditText ET_first_player;
    private EditText ET_second_player;
    private Button BT_jouer;
    private Toolbar toolbar;
    private LinearLayout LL_about;
    private LinearLayout LL_settings;

    /**
     * crée et initialise les objets au démarrage de l'application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ajoute un menu de navigation
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // initialise les objets avec les éléments de l'activité
        TV_main = findViewById(R.id.text_main);
        BT_add_player = findViewById(R.id.button_add_player);
        ET_first_player = findViewById(R.id.main_edit_first_player);
        ET_second_player = findViewById(R.id.main_edit_second_player);
        BT_jouer = findViewById(R.id.button_playToGame);
        LL_about = findViewById(R.id.main_about_popup);
        LL_settings = findViewById(R.id.main_settings_popup);

        // rend invisible certains éléments
        ET_first_player.setVisibility(View.INVISIBLE);
        ET_second_player.setVisibility(View.INVISIBLE);
        LL_about.setVisibility(View.INVISIBLE);
        LL_settings.setVisibility(View.INVISIBLE);
    }

    /**
     * exécute le programme après le démarrage
     */
    @Override
    protected void onStart() {
        super.onStart();

        /**
         * exécute le code du bouton pour ajouter un utilisateur
         */
        BT_add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // permet au premier utilisateur de s'ajouter
                ET_first_player.setVisibility(View.VISIBLE);
                ET_first_player.requestFocus();
                // si le joueur 1 est déjà saisi, on peut saisir le joueur 2
                if (!ET_first_player.getText().toString().equals("")) {
                    // permet au deuxième utilisateur de s'ajouter
                    ET_second_player.setVisibility(View.VISIBLE);
                    ET_second_player.requestFocus();
                }
            }
        });

        BT_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // si 2 joueurs sont saisis, l'activité GameActivity se lance
                if (vérifierChampsSaisie()) {
                    Intent GameActivity = new Intent(MainActivity.this,GameActivity.class);
                    // l'activité MainActivity se termine
                    finish();
                    // l'activité GameActivity se lance
                    startActivity(GameActivity);
                }
            }
        });
    }

    public boolean vérifierChampsSaisie() {
        // si les deux joueurs sont inscrits, le jeu se lance
        if (!ET_first_player.getText().toString().equals("") && !ET_second_player.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * crée un popup menu
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * exécute une action par rapport à ce qu'on a sélectionné dans le menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.about:
                // permet de voir les informations sur l'application
                LL_about.setVisibility(View.VISIBLE);
                break;
            case R.id.settings:
                // ouvre les paramètres
                LL_settings.setVisibility(View.VISIBLE);
                break;
            default:
                // rend tout invisible
                LL_about.setVisibility(View.INVISIBLE);
                LL_settings.setVisibility(View.INVISIBLE);
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}