package com.codecool.hangman;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        //nezd meg a ./sharedStuffs/hangman_flowchart_grou4.drawio fajlt https://app.diagrams.net
        //a feladataleiras miatt az eletet itt kell beallitani,
        //ugyhogy itt kell meghivni a selectDifficulty fuggvenyt.

        System.out.println("=======================");
        System.out.println("||      HANGMAN      ||");
        System.out.println("=======================");
        int difficulty = selectDifficulty();
        String[] arrayDiff = setDifficulty(difficulty);
        play(arrayDiff[0], Integer.parseInt(arrayDiff[1]));

        //ha a fo programreszt akarnank tesztelni, elore megadott parameterekkel
        //int lives = 6;
        //String word="Codecool";
        //play(word, lives);
    }

    private static void play(String word, int lives) {
        char[] charArray = word.toCharArray();
        char[] wordState = new char[word.length()];
        char[] userInputArr = new char[1];
        String input;
        char userInput;

        for (int i = 0; i < charArray.length; i++) {
            wordState[i] = '_';
        }
        do {
            for (int i = 0; i < charArray.length; i++) {
                System.out.print(wordState[i] + " ");
            }
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.equals("quit")) { // Maybe put into a function later
                // STOP PROGRAM
            } else if(input.length() == 1) {
                userInputArr = input.toCharArray();
                userInput = userInputArr[0];

                for (int i = 0; i < charArray.length; i++) {
                    if (Character.toLowerCase(userInput) == Character.toLowerCase(charArray[i])) {
                        wordState[i] = charArray[i];
                    }
                }
            } else {
                System.out.println("Please input a single character");
            }
        } while (!(input.equals("quit") || Arrays.equals(charArray,wordState)));
    }
    private static int selectDifficulty(){
        System.out.println("Please select difficulty!");
        System.out.println("1 - Easy");
        System.out.println("2 - Medium");
        System.out.println("3 - Hard");

        int difficulty = 0;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            if(scanner.hasNextInt()) { //a betuket es tortszamokat meg nem vedi ki, de az entert igen...
                difficulty = scanner.nextInt();
                if (difficulty > 0 && difficulty <= 3) {
                    flag = false;
                }
            }
        }
        return difficulty;
    }

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
