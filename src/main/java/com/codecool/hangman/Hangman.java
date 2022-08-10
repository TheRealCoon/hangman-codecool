package com.codecool.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        //nezd meg a ./sharedStuffs/hangman_flowchart_grou4.drawio fajlt https://app.diagrams.net
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
        printWin(word);
    }
    private static void printWin(String word){
        System.out.println("The word was " + word);
        System.out.println();
        System.out.println( "   ___________________________\n" +
                            "  |                           |\n" +
                            "  |       CONGRATULATIONS!    |\n" +
                            "  |_______                ____|\n" +
                            "          \\  YOU ROCK!  /\n" +
                            "           \\_________  /\n" +
                            "          /\\  /\\     \\/\n" +
                            "         /  \\/  \\  /\\\n" +
                            "   /\\  /         \\/  \\\n" +
                            "  /  \\/    O   O      \\\n" +
                            " /            ^        \\\n" +
                            "/          \\_____/      \\");
        System.out.println();
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

        //word = randomCountry(difficulty, Countries.getAllCountries());
        word = "Macska";

        return new String[]{word, strLives};
    }

    private static String randomCountry(int difficulty, List<String> countries) {
        String country = "";
        List<String> countriesSortedByLength;
        int minWordLength = 1;
        int maxWordLength = 2;
        switch (difficulty) {
            case 1:
                minWordLength = 1;
                maxWordLength = 6;
                break;
            case 2:
                minWordLength = 7;
                maxWordLength = 10;
                break;
            case 3:
                minWordLength = 11;
                maxWordLength = 30;
                break;
        }
        countriesSortedByLength = getCountryListByDifficulty(minWordLength, maxWordLength, countries);
        double randomIndex = Math.random() * countriesSortedByLength.size();
        country = countriesSortedByLength.get((int) randomIndex);
        return country;
    }

    private static List<String> getCountryListByDifficulty(int minWordLength, int maxWordLength, List<String> countries) {
        List<String> resultList = new ArrayList<>();
        for (String country : countries) {
            if (country.length() >= minWordLength && country.length() <= maxWordLength) {
                resultList.add(country);
            }
        }
        return resultList;
    }

    public static void printAscii(int lives, int difficulty) {
        String[] asciiArr = new String[7];
        asciiArr[0] = "    _ _\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \\|/\n" +
                "   |  |\n" +
                "   | / \\\n" +
                "_ _| ";

        asciiArr[1] = "    _ _\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \\|/\n" +
                "   |  |\n" +
                "   |  \n" +
                "_ _| ";

        asciiArr[2] = "    _ _\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \\|/\n" +
                "   |  \n" +
                "   |  \n" +
                "_ _| ";

        asciiArr[3] = "    _ _\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \n" +
                "   |  \n" +
                "   |  \n" +
                "_ _| ";

        asciiArr[4] = "    _ _\n" +
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
        if (lives >= 0) {
            switch (difficulty) {
                case 1: // EASY - 7
                    System.out.println(asciiArr[lives-1]);
                    break;
                case 2: // MEDIUM - 5
                    System.out.println(asciiArr[Math.round((float) (lives * 6 / 5))]);
                    break;
                case 3: // HARD - 3
                    System.out.println(asciiArr[lives * 2]);
            }
        }
    }
}
