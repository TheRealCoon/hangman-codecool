package com.codecool.hangman;

import java.sql.SQLOutput;
import java.util.ArrayList;
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
        play(arrayDiff[0], Integer.parseInt(arrayDiff[1]), difficulty);
    }

    private static void play(String word, int lives, int difficulty) {
        System.out.println("You have " + lives + " lives");
        char[] charArray = word.toCharArray();
        char[] wordState = new char[word.length()];
        char[] userInputArr;
        String input;
        char userInput;
        ArrayList<Character> usedLetters = new ArrayList<>();
        boolean isRepeatedLetter = false;
        boolean isInWord = false;

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
            } else if (input.length() == 1) {
                userInputArr = input.toCharArray();
                userInput = userInputArr[0];
                if (!usedLetters.contains(Character.toLowerCase(userInput))) {
                    usedLetters.add(Character.toLowerCase(userInput));
                    isRepeatedLetter = false;
                } else {
                    isRepeatedLetter = true;
                    System.out.println("Letters already used " + usedLetters);
                }
                isInWord = false;
                for (int i = 0; i < charArray.length; i++) {
                    if (Character.toLowerCase(userInput) == Character.toLowerCase(charArray[i])) {
                        wordState[i] = charArray[i];
                        isInWord = true;
                    }
                }
            } else {
                System.out.println("Please input a single character");
            }
            if (!isRepeatedLetter && !isInWord) {
                lives--;
            }
            printAscii(lives, difficulty);
            if (lives == 0) {
                System.out.println("Game over :(");
            }
            System.out.println("You have " + lives + " lives");


        } while (!(input.equals("quit") || Arrays.equals(charArray, wordState)));
        System.out.println( " ____________________\n" +
                            "|                               |\n" +
                            "| CONGRATULATIONS!   | \n" +
                            "|__ ____|\n" +
                            "     \\  YOU ROCK!  /\n" +
                            "      \\__________  /\n" +
                            "           /\\   /\\         \\/\n" +
                            "         /   \\/   \\    /\\\n" +
                            "   /\\  /           \\/   \\\n" +
                            "  /  \\/   O   O        \\\n" +
                            " /           ^             \\\n" +
                            "/       \\____ /          \\");
        System.out.println();
        System.out.println("The word was " + word);
    }

    private static int selectDifficulty() {
        System.out.println("Please select difficulty!");
        System.out.println("1 - Easy");
        System.out.println("2 - Medium");
        System.out.println("3 - Hard");

        int difficulty = 0;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            if (scanner.hasNextInt()) { //a betuket es tortszamokat meg nem vedi ki, de az entert igen...
                difficulty = scanner.nextInt();
                if (difficulty > 0 && difficulty <= 3) {
                    flag = false;
                }
            }
        }
        return difficulty;
    }

    private static String[] setDifficulty(int difficulty) {
        //a selectDifficulty szerint beallitjuk a nehezseget: beallitja az eletet es az orszagokat//
        String word;
        String strLives = "";
        switch (difficulty) {
            case 1:
                strLives = "7";
                break;
            case 2:
                strLives = "5";
                break;
            case 3:
                strLives = "3";
                break;
            default:
                selectDifficulty();
        }
        System.out.println(strLives);

        word = randomCountry(difficulty, Countries.getAllCountries());
        // String word = "Macska";

        return new String[]{word, strLives};
    }

    private static String randomCountry(int difficulty, List<String> countries) {
        String country = "";
        switch (difficulty) {
            case 1: ;
                break;
            case 2:
                break;
            case 3:
                break;
        }
        double randomIndex = Math.random()*countries.size()-1;
        country = countries.get((int)randomIndex);
        return country;
    }

    public static void printAscii(int lives, int difficulty) {
        String[] asciiArr = new String[7];
        asciiArr[0] =   "    _ _\n" +
                        "   |  |\n" +
                        "   |  0\n" +
                        "   | \\|/\n" +
                        "   |  |\n" +
                        "   | / \\\n" +
                        "_ _| ";

        asciiArr[1] =   "    _ _\n" +
                        "   |  |\n" +
                        "   |  0\n" +
                        "   | \\|/\n" +
                        "   |  |\n" +
                        "   |  \n" +
                        "_ _| ";

        asciiArr[2] =   "    _ _\n" +
                        "   |  |\n" +
                        "   |  0\n" +
                        "   | \\|/\n" +
                        "   |  \n" +
                        "   |  \n" +
                        "_ _| ";

        asciiArr[3] =   "    _ _\n" +
                        "   |  |\n" +
                        "   |  0\n" +
                        "   | \n" +
                        "   |  \n" +
                        "   |  \n" +
                        "_ _| ";

        asciiArr[4] =   "    _ _\n" +
                        "   | \n" +
                        "   | \n" +
                        "   | \n" +
                        "   | \n" +
                        "   | \n" +
                        "_ _| ";

        asciiArr[5] = "   \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "   |\n" +
                "   |  \n" +
                "_ _|";

        asciiArr[6] = "   \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "_ _|";

        switch (difficulty) {
            case 1: // EASY - 7
                System.out.println(asciiArr[lives]);
                break;
            case 2: // MEDIUM - 5
                System.out.println(asciiArr[Math.round((float)(lives*6/5))]);
                break;
            case 3: // HARD - 3
                System.out.println(asciiArr[lives*2]);
        }
    }
}
