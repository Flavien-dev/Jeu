package com.chapfla.jeu;

// importer les librairies
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chapfla.jeu.Controllers.QuestionManager;
import com.chapfla.jeu.Models.Question;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    // créer des objets
    private Button BT_player_1;
    private Button BT_player_2;
    private TextView TV_quest_1;
    private TextView TV_quest_2;
    private TextView TV_score_1;
    private TextView TV_score_2;
    private Button BT_menu;
    private Button BT_rejouer;

    // créer des variable
    int nbRéponsesJustesJ1 = 0;
    int nbRéponsesJustesJ2 = 0;
    int réponseQuestion = 0;
    Runnable questionRunnable = null;

    // créer des objets grâce aux classes
    QuestionManager qManager;
    ArrayList<Question> listeQuestion = new ArrayList<>();
    Question questionEnCours;

    /**
     * créer le programme quand il se lance
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // initialiser les objets de l'application
        BT_player_1 = findViewById(R.id.button_player_1);
        BT_player_2 = findViewById(R.id.button_player_2);
        TV_quest_1 = findViewById(R.id.game_quest_1);
        TV_quest_2 = findViewById(R.id.game_quest_2);
        TV_score_1 = findViewById(R.id.score_player_1);
        TV_score_2 = findViewById(R.id.score_player_2);
        BT_menu = findViewById(R.id.menu);
        BT_rejouer = findViewById(R.id.rejouer);

        // rend les boutons de fin invisibles
        BT_menu.setVisibility(View.INVISIBLE);
        BT_rejouer.setVisibility(View.INVISIBLE);

        qManager = new QuestionManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        listeQuestion = qManager.initQuestionList(this);

        afficherQuestion(listeQuestion);

        /**
         * s'effectue si le bouton du joueur 1 est pressé
         */
        BT_player_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                réponseQuestion = 1;
                // vérifie si la réponse du joueur 1 est juste
                comparerRésultat(1);
                réponseQuestion = 0;
                // grise les boutons
                BT_player_1.setEnabled(false);
                BT_player_2.setEnabled(false);
            }
        });

        /**
         * s'effectue si le bouton du joueur 2 est pressé
         */
        BT_player_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                réponseQuestion = 1;
                // vérifie si la réponse du joueur 2 est juste
                comparerRésultat(2);
                réponseQuestion = 0;
                // grise les boutons
                BT_player_1.setEnabled(false);
                BT_player_2.setEnabled(false);
            }
        });

        /**
         * s'effectue si le bouton du menu est pressé
         */
        BT_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity = new Intent(GameActivity.this,MainActivity.class);
                finish();
                startActivity(MainActivity);
            }
        });

        /**
         * s'effectue si le bouton rejouer est pressé
         */
        BT_rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GameActivity = getIntent();
                // permet de finir l'activité GameActivity
                finish();
                // permet de recommencer l'activité GameActivity
                startActivity(GameActivity);
            }
        });

        // créer un handler
        Handler handler = new Handler();

        /**
         * exécute une liste de programmes
         */
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                // si la liste est vide
                if (listeQuestion.size() == 1) {
                    // affiche une question
                    afficherQuestion(listeQuestion);

                    handler.removeCallbacks(this);

                    // grise les boutons
                    BT_player_1.setEnabled(false);
                    BT_player_2.setEnabled(false);
                    BT_menu.setVisibility(View.VISIBLE);
                    BT_rejouer.setVisibility(View.VISIBLE);

                    // affiche un message aux deux joueurs
                    TV_quest_1.setText("Attendez...");
                    TV_quest_2.setText("Attendez...");
                } else {
                    // affiche une question
                    afficherQuestion(listeQuestion);

                    // grise les boutons
                    BT_player_1.setEnabled(true);
                    BT_player_2.setEnabled(true);

                    // affecte un délai entre chaque question
                    handler.postDelayed(this,2000);
                }
            }
        };
        // affecte un délai entre chaque question
        handler.postDelayed(questionRunnable,2000);
    }

    /**
     * affiche une question tirée au hasard dans la liste
     * @param liste liste de questions
     */
    public void afficherQuestion(ArrayList liste) {
        // prend une question au hasard dans la liste
        Question question = qManager.distribQuestionAlea(liste);

        // affiche la question pour les deux joueurs
        TV_quest_1.setText(question.getQuestion());
        TV_quest_2.setText(question.getQuestion());

        // supprime la question de la liste pour ne pas revenir dessus
        liste.remove(question);

        questionEnCours = question;
    }

    /**
     * vérifie qui gagne ou perd un point
     * @param joueur numéro du joueur
     */
    public void comparerRésultat(int joueur) {
        // si la question est juste
        if (questionEnCours.getReponse() == réponseQuestion) {
            // si le joueur 1 à juste
            if (joueur == 1) {
                // le joueur 1 gagne un point
                nbRéponsesJustesJ1++;
                // son score change
                TV_score_1.setText(Integer.toString(nbRéponsesJustesJ1));
            } else {
                // le joueur 2 gagne un point
                nbRéponsesJustesJ2++;
                // son score change
                TV_score_2.setText(Integer.toString(nbRéponsesJustesJ2));
            }
        }
        /*
        else {
            // si le joueur 1 a répondu faux
            if (joueur == 1) {
                // le joueur 1 perd un point
                nbRéponsesJustesJ1--;
                // son score change
                TV_score_1.setText(Integer.toString(nbRéponsesJustesJ1));
            } else {
                // le joueur 1 perd un point
                nbRéponsesJustesJ2--;
                // son score change
                TV_score_2.setText(Integer.toString(nbRéponsesJustesJ2));
            }
        }
         */
    }
}
