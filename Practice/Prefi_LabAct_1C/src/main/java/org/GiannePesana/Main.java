package org.GiannePesana;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Character character;

    public static String[] club = {
            "🤮", "🍸", "🎶", "😎", "🚽",
            "🔐", "😎", "🍸", "🎶", "😎",
            "🚽", "🍸", "😎", "🔐", "🍸",
            "", "🎶", "🤮"
    };

    public static int time = 8;
    public static String modifier = "pm";
    public static boolean isAm;


    public static void main(String[] args) {
        character = new Character(Displays.handleCharacterSelection());
        int characterIndex = 1;

        // initialize character position
        club[1] = character.characterEmoji;
        club[15] = character.name.equalsIgnoreCase("alejandro") ? "👧🏼" : "👦🏼";

        int turns = 8;
        while (turns > 0) {
            System.out.println(getIteration());

            // check
            if (character.enjoymentBars <= 0) {
                System.out.println(character.name + " no longer enjoys being in the club 😞");
                return;
            }

            if (characterIndex == 15) {
                club[15] = "👦🏼👧🏼";
                System.out.println(character.name + " met up with " +
                        (character.name.equalsIgnoreCase("alejandro") ? "samantha" : "alejandro")
                );
                return;
            }

            int move = handleMove();
            System.out.print("[");
            for (String s : club) {
                System.out.print(s + ",");
            }
            System.out.print("]");
            System.out.println();

            turns--;
            time++;
        }

        if (turns <= 0) {
            System.out.println(character.name + " ran out of energy");
        }
    }

    private static int handleMove() {
        Random random = new Random();
        while (true) {
            int move = random.nextInt(character.moveMax - character.moveMin + 1) + character.moveMin;

        }
        return 0;
    }

    private static void handleNerfAndBuff(int move) {

    }

    public static String getIteration() {
        time = time > 12 ? 1 : time;
        isAm = modifier.equals("am");

        if (time > 12) {
            time = 1;
        }

        if (time == 12) {
            isAm = !isAm;
        }

        modifier = isAm ? "am" : "pm";

        return "@" + time + " " + modifier;
    }

    public static void resetClub() {
        String[] clubDuplicate = {"🤮", "🍸", "🎶", "😎", "🚽",
                "🔐", "😎", "🍸", "🎶", "😎",
                "🚽", "🍸", "😎", "🔐", "🍸",
                "", "🎶", "🤮"
        };

        clubDuplicate[15] = character.name.equalsIgnoreCase("alejandro") ? "👧🏼" : "👦🏼";

        for (int i = 0; i < club.length; i++) {
            club[i] = clubDuplicate[i];
        }
    }
}