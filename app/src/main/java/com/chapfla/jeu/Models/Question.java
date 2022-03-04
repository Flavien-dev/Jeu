package com.chapfla.jeu.Models;

// importer les librairies
import android.database.Cursor;

public class Question {

    private String question;

    private int reponse;

    /**
     * construit une question
     * @param question ce qu'on va demandé à l'utilisateur
     * @param réponse ce qui est juste
     */
    public Question(String question, int réponse) {
        this.question = question;
        this.reponse = réponse;
    }

    /**
     * construit une question
     * @param cursor ce qui contient la question + la réponse
     */
    public Question(Cursor cursor){
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    /**
     * permet de récupèrer une question
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * permet de récupèrer une réponse
     * @return réponse
     */
    public int getReponse() {
        return reponse;
    }
}
