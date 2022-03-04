package com.chapfla.jeu.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {
    // créer les variables
    static String DB_NAME="SpeedQuiz.db";
    static int DB_VERSION=1;

    /**
     * construit un SpeedQuizSQLiteOpenHelper
     * @param context tout les éléments de l'interface
     */
    public SpeedQuizSQLiteOpenHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    /**
     * remplie la base de donnée avec les questions et les réponses
     * @param db base de donnée SQLite
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatatableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY,question TEXT,reponse INTEGER);";
        db.execSQL(sqlCreateDatatableQuiz);

        // ajouter toutes les questions
        db.execSQL("INSERT INTO quiz VALUES(1,\"La capitale du Vénézuela est Bogotà?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"Cette appli est codée en javascript?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"Cette appli est seulement pour les appareils Android?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"l'OS le plus utilisé est MacOS?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"2 x 2 + 2 - 2 / 2 = 5?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(6,\"Staline est un dictateur?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(7,\"L'apprentissage d'informaticien se fait en 4 ans\",1)");
        db.execSQL("INSERT INTO quiz VALUES(8,\"L'homme le plus rapide du monde est Ronaldo?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(9,\"Un cheval peut produire du lait?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(10,\"RJ45 est un câble réseau?\",0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
