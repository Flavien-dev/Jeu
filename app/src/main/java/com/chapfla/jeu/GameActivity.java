package com.chapfla.jeu;

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
    private Button BT_player_1;
    private Button BT_player_2;
    private TextView TV_quest_1;
    private TextView TV_quest_2;
    private TextView TV_score_1;
    private TextView TV_score_2;
    private Button BT_menu;
    private Button BT_rejouer;

    int nbRéponsesJustesJ1 = 0;
    int nbRéponsesJustesJ2 = 0;
    QuestionManager gestionQuestion = new QuestionManager();
    ArrayList<Question> listeQuestion = new ArrayList<>();
    Runnable questionRunnable = null;
    Question questionEnCours;
    int réponseQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        BT_player_1 = findViewById(R.id.button_player_1);
        BT_player_2 = findViewById(R.id.button_player_2);
        TV_quest_1 = findViewById(R.id.game_quest_1);
        TV_quest_2 = findViewById(R.id.game_quest_2);
        TV_score_1 = findViewById(R.id.score_player_1);
        TV_score_2 = findViewById(R.id.score_player_2);
        BT_menu = findViewById(R.id.menu);
        BT_rejouer = findViewById(R.id.rejouer);

        BT_menu.setVisibility(View.INVISIBLE);
        BT_rejouer.setVisibility(View.INVISIBLE);

        gestionQuestion.fillList();
    }

    @Override
    protected void onStart() {
        super.onStart();

        listeQuestion = gestionQuestion.listeAQuestion;

        afficherQuestion(listeQuestion);

        BT_player_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                réponseQuestion = 1;
                comparerRésultat(1);
                réponseQuestion = 0;
                BT_player_1.setEnabled(false);
                BT_player_2.setEnabled(false);
            }
        });

        BT_player_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                réponseQuestion = 1;
                comparerRésultat(2);
                réponseQuestion = 0;
                BT_player_1.setEnabled(false);
                BT_player_2.setEnabled(false);
            }
        });

        BT_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity = new Intent(GameActivity.this,MainActivity.class);
                finish();
                startActivity(MainActivity);
            }
        });

        BT_rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GameActivity = getIntent();
                finish();
                startActivity(GameActivity);
            }
        });

        Handler handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if(listeQuestion.size() == 1){
                    afficherQuestion(listeQuestion);

                    handler.removeCallbacks(this);

                    BT_player_1.setEnabled(false);
                    BT_player_2.setEnabled(false);
                    TV_quest_1.setText("Attendez...");
                    TV_quest_2.setText("Attendez...");
                    BT_menu.setVisibility(View.VISIBLE);
                    BT_rejouer.setVisibility(View.VISIBLE);
                }else{
                    afficherQuestion(listeQuestion);
                    BT_player_1.setEnabled(true);
                    BT_player_2.setEnabled(true);
                    handler.postDelayed(this,4000);
                }
            }
        };
        handler.postDelayed(questionRunnable,4000);
    }

    public void afficherQuestion(ArrayList liste) {
        Question question = gestionQuestion.distribQuestionAlea(liste);
        TV_quest_1.setText(question.getQuestion());
        TV_quest_2.setText(question.getQuestion());
        Log.wtf("quesiton : ",question.getQuestion());
        liste.remove(question);
        Log.wtf("nb éléments : ",Integer.toString(liste.size()));
        questionEnCours = question;
    }

    public void comparerRésultat(int joueur) {
        if (questionEnCours.getReponse() == réponseQuestion) {
            if (joueur == 1) {
                nbRéponsesJustesJ1++;
                TV_score_1.setText(Integer.toString(nbRéponsesJustesJ1));
            } else {
                nbRéponsesJustesJ2++;
                TV_score_2.setText(Integer.toString(nbRéponsesJustesJ2));
            }
        } else {
            if (joueur == 1) {
                nbRéponsesJustesJ1--;
                TV_score_1.setText(Integer.toString(nbRéponsesJustesJ1));
            } else {
                nbRéponsesJustesJ2--;
                TV_score_2.setText(Integer.toString(nbRéponsesJustesJ2));
            }
        }
    }
}
