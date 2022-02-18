package com.chapfla.jeu.Models;

public class Question {

    private String question;

    private int reponse;

    public Question(){};

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

    /**
     * permet de modifier la question
     * @param question question posée
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * permet de modifier la réponse
     * @param reponse réponse de la question
     */
    public void setReponse(int reponse) {
        this.reponse = reponse;
    }
}
