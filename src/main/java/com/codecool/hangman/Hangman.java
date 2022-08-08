package com.codecool.hangman;

import java.util.List;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        //nezd meg a ./sharedStuffs/hangman_flowchart_grou4.drawio fajlt https://app.diagrams.net
        //a feladataleiras miatt az eletet itt kell beallitani,
        //ugyhogy itt kell meghivni a selectDifficulty fuggvenyt.
        int lives = 6; // =selectDifficulty(); lives legalabb 3-7 skalan mozogjon
        String word="Codecool"; //le kell cserelni a randomCountry(Countries.getAll()) fuggvenyre, egyelore tesztelesre maradhat fix


        int difficulty = selectDifficulty();
        String[] arrayDiff = setDifficulty(difficulty);
        // System.out.println(arrayDiff[0] + arrayDiff[1]);

        play(word, lives = 6);
    }

    private static void play(String word, int lives) {
        // TODO Implement the game here!

    }
    private static int selectDifficulty(){
        System.out.println("Please select difficulty! (1-3)");
        System.out.println("1 - Easy");
        System.out.println("2 - Medium");
        System.out.println("3 - Hard");

        int difficulty = 0;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        while (flag) {
            difficulty = scanner.nextInt();

            if (difficulty > 0 && difficulty <= 3) {
                flag = false;
                }
            }
        return difficulty;
    }

    //erre ha van jobb otletetek mondjatok
    private static String[] setDifficulty(int difficulty){
        //a selectDifficulty szerint beallitjuk a nehezseget: beallitja az eletet es az orszagokat//
        String strLives = "";
        switch (difficulty) {
            case 1: strLives = "7";
                break;
            case 2: strLives = "5";
                break;
            case 3: strLives = "3";
                break;
            default: selectDifficulty();
        }
        System.out.println(strLives);

        // word = randomCountry(difficulty, Countries.getAllCountries());
        String word = "Macska";

        return new String[] {word, strLives};
    }
    private static String randomCountry(int difficulty, List<String> countries){
        String country="";
        // itt jön egy algoritmus ami a difficulty szerint kivalogat egy listat az osszes orszag listajabol,
        //aztan visszadob egy randomot
        return country;
    }
}
