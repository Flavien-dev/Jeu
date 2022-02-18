package com.chapfla.jeu.Controllers;

import com.chapfla.jeu.Models.Question;

import java.util.ArrayList;

public class QuestionManager {

    public ArrayList<Question> listeAQuestion = new ArrayList();

    public QuestionManager() {}

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

    public Question distribQuestionAlea(ArrayList liste) {
        return (Question) liste.get((int) (Math.random()*liste.size()));
    }
}
