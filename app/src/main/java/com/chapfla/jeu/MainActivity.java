package com.chapfla.jeu;

// importer les librairies
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chapfla.jeu.Models.Question;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // créer les objets de l'activité
    private TextView TV_main;
    private Button BT_add_player;
    private EditText ET_first_player;
    private EditText ET_second_player;
    private TextInputEditText ET_delai_question;
    private TextInputEditText ET_nouvelle_question;
    private RadioButton RB_nouvelle_reponse;
    private Button BT_jouer;
    private Toolbar toolbar;
    private ConstraintLayout StartLayout;
    private LinearLayout LL_about;
    private LinearLayout LL_settings;
    private SwitchMaterial SW_theme;
    private Button BT_valider;

    int delai_entre_question = 0;
    Question nouvelleQuestion;
    String question;
    int réponse;

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
        StartLayout = findViewById(R.id.StartLayout);
        LL_about = findViewById(R.id.main_about_popup);
        LL_settings = findViewById(R.id.main_settings_popup);
        SW_theme = findViewById(R.id.switch_réponse);
        BT_valider = findViewById(R.id.button_validate);
        ET_delai_question = findViewById(R.id.edittext_menu_vitesse_question);
        ET_nouvelle_question = findViewById(R.id.edittext_menu_nouvelle_question);
        RB_nouvelle_reponse = findViewById(R.id.radio_checked_element);

        // rend invisible certains éléments
        ET_first_player.setVisibility(View.INVISIBLE);
        ET_second_player.setVisibility(View.INVISIBLE);
        LL_about.setVisibility(View.INVISIBLE);
        LL_settings.setVisibility(View.INVISIBLE);

        SW_theme.setChecked(true);
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

        /**
         * exécute le code du bouton pour jouer
         */
        BT_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // si 2 joueurs sont saisis, l'activité GameActivity se lance
                if (vérifierChampsSaisie()) {
                    if (ET_delai_question.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Vous n'avez pas saisi une vitesse de lecture dans les settings!", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent GameActivity = new Intent(MainActivity.this,GameActivity.class);

                        GameActivity.putExtra("delai", delai_entre_question);

                        // l'activité MainActivity se termine
                        finish();
                        // l'activité GameActivity se lance
                        startActivity(GameActivity);
                    }
                }
            }
        });

        /**
         * change le fond d'écran par rapport à l'état du switch
         */
        SW_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // si le switch est a true, un fond d'écran sombre apparait
                if(SW_theme.isChecked()) {
                    StartLayout.setBackgroundResource(R.drawable.espace_orange);
                } else {
                    // sinon, un fond d'écran clair apparait
                    StartLayout.setBackgroundResource(R.drawable.image_clair);
                }
            }
        });

        BT_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ET_delai_question.getText().toString().equals("")) {
                    delai_entre_question = Integer.parseInt(ET_delai_question.getText().toString());
                }
                if (!ET_nouvelle_question.getText().toString().equals(""))
                LL_settings.setVisibility(View.INVISIBLE);
            }
        });

        ET_delai_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
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
     * @param item ce qu'on a sélectionné
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