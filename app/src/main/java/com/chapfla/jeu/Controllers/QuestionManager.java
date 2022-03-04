package com.chapfla.jeu.Controllers;

// importer les librairies
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chapfla.jeu.Models.Question;
import com.chapfla.jeu.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;

public class QuestionManager {

    // créer une liste
    public ArrayList<Question> listeAQuestion = new ArrayList();

    // construit un gestionnaire de questions
    public QuestionManager() {}

    // construit un gestionnaire de question
    public QuestionManager(Context context) {
        listeAQuestion = initQuestionList(context);
    }

    /**
     * remplie la liste avec des questions
     */
    public void fillList() {
        listeAQuestion.add(new Question("La capitale du Vénézuela est Bogotà?",0));
        listeAQuestion.add(new Question("Cette appli est codée en javascript?",0));
        listeAQuestion.add(new Question("Cette appli est seulement pour les appareils Android?",1));
        listeAQuestion.add(new Question("l'OS le plus utilisé est MacOS?",0));
        listeAQuestion.add(new Question("2 x 2 + 2 - 2 / 2 = 5?",1));
        listeAQuestion.add(new Question("Staline est un dictateur?",1));
        listeAQuestion.add(new Question("L'apprentissage d'informaticien se fait en 4 ans",1));
        listeAQuestion.add(new Question("L'homme le plus rapide du monde est Ronaldo?",0));
        listeAQuestion.add(new Question("Un cheval peut produire du lait?",1));
        listeAQuestion.add(new Question("RJ45 est un câble réseau?",0));
    }

    /**
     * choisie une question aléatoirement dans une liste
     * @param liste liste qui contient des questions
     * @return question choisie aléatoirement
     */
    public Question distribQuestionAlea(ArrayList liste) {
        return (Question) liste.get((int) (Math.random()*liste.size()));
    }

    /**
     * Charge une liste de question depuis la DB.
     * @param context Le contexte de l'application pour passer la query
     * @return Une arraylist charger de Question
     */
    public ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idquiz",null);

        // ajoute les questions dans la liste
        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }

        cursor.close();
        db.close();

        // retourne la liste contenant les questions
        return listQuestion;
    }
}
