package com.codecool.hangman;

import java.util.List;

public class Hangman {

    public static void main(String[] args) {
        //nezd meg a ./sharedStuffs/hangman_flowchart_grou4.drawio fajlt https://app.diagrams.net
        //a feladataleiras miatt az eletet itt kell beallitani,
        //ugyhogy itt kell meghivni a selectDifficulty fuggvenyt.
        int lives = 6; // =selectDifficulty(); lives legalabb 3-7 skalan mozogjon
        String word="Codecool"; //le kell cserelni a randomCountry(Countries.getAll()) fuggvenyre, egyelore tesztelesre maradhat fix

        int difficulty = selectDifficulty();
        setDifficulty(difficulty, word, lives);

        play(word, lives = 6);
    }

    private static void play(String word, int lives) {
        // TODO Implement the game here!

    }
    private static int selectDifficulty(){
        int difficulty = 1;
        //kiirni a nehezsegi szinteket 1-easy, 2-medium, 3-hard  vagy vmi ilyesmi
        //bekerni egyet
        return difficulty;
    }

    //erre ha van jobb otletetek mondjatok
    private static void setDifficulty(int difficulty, String word, int lives){
        //a selectDifficulty szerint beallitjuk a nehezseget: beallitja az eletet es az orszagokat


        word = randomCountry(difficulty, Countries.getAllCountries());
    }
    private static String randomCountry(int difficulty, List<String> countries){
        String country="";
        // itt jön egy algoritmus ami a difficulty szerint kivalogat egy listat az osszes orszag listajabol,
        //aztan visszadob egy randomot
        return country;
    }
}
