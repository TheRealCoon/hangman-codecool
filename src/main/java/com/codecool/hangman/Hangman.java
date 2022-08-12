package com.codecool.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        System.out.println("=======================");
        System.out.println("||      HANGMAN      ||");
        System.out.println("=======================");
        int difficulty = selectDifficulty();
        String[] arrayDiff = setDifficulty(difficulty);
        play(arrayDiff[0], Integer.parseInt(arrayDiff[1]), difficulty);
    }

    private static void play(String word, int lives, int difficulty) {
        System.out.println("You have " + lives + " lives");
        char[] wordAsCharArray = word.toCharArray();
        char[] wordState = new char[word.length()];
        String input;
        char userInput;
        ArrayList<Character> usedLetters = new ArrayList<>();
        boolean isRepeatedLetter = false;
        boolean isInWord = false;

        fillUpWordState(wordAsCharArray, wordState);
            do {
                displayWordState(wordAsCharArray, wordState);
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();
                quitProgram(input.equals("quit"));
                if (!input.isBlank() && isValidChar(input.charAt(0))) {
                    userInput = input.toLowerCase().charAt(0);
                    isRepeatedLetter = CheckIfRepeatedLetter(userInput, usedLetters);
                    isInWord = CheckIfInWord(wordAsCharArray, wordState, userInput);
                } else System.out.println("Please input a single character");
                if (!isRepeatedLetter && !isInWord ) lives--;
                printAscii(lives, difficulty);
                System.out.println("You have " + lives + " lives");
            } while (!Arrays.equals(wordAsCharArray, wordState) && lives != 0);
        System.out.printf("The word was \"%s\".\n",word);
            if (lives == 0) System.out.println("Game over :(");
        if(Arrays.equals(wordAsCharArray, wordState)){
                printWin(word);
            }
        askForNewGame();
    }

    private static void askForNewGame() {
        System.out.println("Do you want to continue playing?");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer.toLowerCase()) {
            case "y":
                main(new String[]{});
                break;
            case "n":
                quitProgram(true);
                break;
            default:
                System.out.println("Answer y or n!");
                askForNewGame();
        }
    }

    private static void quitProgram(boolean wantToQuit) {
        if (wantToQuit) {
            System.out.println("Good bye!");
            System.exit(0);
        }
    }

    private static boolean CheckIfRepeatedLetter(char userInput, ArrayList<Character> usedLetters) {
        boolean isRepeatedLetter;
        if (!usedLetters.contains(userInput)) {
            usedLetters.add(userInput);
            isRepeatedLetter = false;
        } else {
            isRepeatedLetter = true;
            System.out.println("Letters already used " + usedLetters);
        }
        return isRepeatedLetter;
    }

    private static boolean CheckIfInWord(char[] wordAsCharArray, char[] wordState, char userInput) {
        boolean isInWord = false;
        for (int i = 0; i < wordAsCharArray.length; i++) {
            if (userInput == Character.toLowerCase(wordAsCharArray[i])) {
                wordState[i] = wordAsCharArray[i];
                isInWord = true;
            }
        }
        return isInWord;
    }

    private static void displayWordState(char[] wordAsCharArray, char[] wordState) {
        for (int i = 0; i < wordAsCharArray.length; i++) {
            System.out.print(wordState[i] + " ");
        }
        System.out.println();
    }

    private static void fillUpWordState(char[] wordAsCharArray, char[] wordState) {
        for (int i = 0; i < wordAsCharArray.length; i++) {
            switch (wordAsCharArray[i]) {
                case ' ':
                    wordState[i] = ' ';
                    break;
                case '-':
                    wordState[i] = '-';
                    break;
                default:
                    wordState[i] = '_';
            }
        }
    }

    private static boolean isValidChar(char letter) {
        char[] englishAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char character : englishAlphabet) {
            if (Character.toLowerCase(letter) == character) {
                return true;
            }
        }
        return false;
    }

    private static void printWin(String word) {
        System.out.println();
        System.out.println("   ___________________________\n" +
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
        System.out.println("Type \"quit\" to quit.");
        int difficulty = 0;
        String input;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
                input = scanner.nextLine();
                quitProgram(input.equals("quit"));
                try {
                    difficulty = Integer.parseInt(input);
                }catch (Exception e){
                    System.out.println("Wrong input!");
                }
                if (difficulty > 0 && difficulty <= 3) {
                    flag = false;
                }

        }
        return difficulty;
    }

    private static String[] setDifficulty(int difficulty) {
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
        word = randomCountry(difficulty, Countries.getAllCountries());
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
        String[] asciiArr = new String[8];
        asciiArr[0] = "    ___\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \\|/\n" +
                "   |  |\n" +
                "   | / \\\n" +
                "___| ";

        asciiArr[1] = "    ___\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \\|/\n" +
                "   |  |\n" +
                "   |  \n" +
                "___| ";

        asciiArr[2] = "    ___\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \\|/\n" +
                "   |  \n" +
                "   |  \n" +
                "___| ";

        asciiArr[3] = "    ___\n" +
                "   |  |\n" +
                "   |  0\n" +
                "   | \n" +
                "   |  \n" +
                "   |  \n" +
                "___| ";

        asciiArr[4] = "    ___\n" +
                "   | \n" +
                "   | \n" +
                "   | \n" +
                "   | \n" +
                "   | \n" +
                "___| ";

        asciiArr[5] = "   \n" +
                "   |\n" +
                "   |\n" +
                "   |\n" +
                "   |\n" +
                "   |  \n" +
                "___|";

        asciiArr[6] = "   \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "___|";
        asciiArr[7] = "   \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "____";
        if (lives >= 0) {
            switch (difficulty) {
                case 1: // EASY - 7
                    System.out.println(asciiArr[lives]);
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
