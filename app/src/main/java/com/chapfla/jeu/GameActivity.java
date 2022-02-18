package com.chapfla.jeu;

import android.content.Intent;
import android.os.Bundle;
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

    int nbRéponsesJustesJ1 = 0;
    int nbRéponsesJustesJ2 = 0;
    QuestionManager gestionQuestion = new QuestionManager();
    ArrayList<Question> listeQuestion = new ArrayList<>();

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
                nbRéponsesJustesJ1++;
                TV_score_1.setText(Integer.toString(nbRéponsesJustesJ1));
                afficherQuestion(listeQuestion);
            }
        });

        BT_player_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nbRéponsesJustesJ2++;
                TV_score_2.setText(Integer.toString(nbRéponsesJustesJ2));
            }
        });
    }

    public void afficherQuestion(ArrayList liste) {
        for (int i = 0;i < liste.size()-1;i++) {
            gestionQuestion.changerQuestion();
            TV_quest_1.setText(gestionQuestion.distribQuestionAlea(liste).getQuestion());
            Log.wtf("afficher question : ","fonctionne");
        }
    }

}
