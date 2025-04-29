package org.GiannePesana;

import java.util.Scanner;

public class Displays {
    public static String handleCharacterSelection() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        while (true) {
            System.out.print(
                    "Are you:\n" +
                    "[1] Alejandro\n" +
                    "[2] Samantha\n\n" +
                    "Who are you? "
            );
            choice = scanner.nextLine().toLowerCase();

            if (choice.contains("1")  || choice.contains("alejandro"))
                return "Alejandro";
            else if (choice.contains("2") || choice.contains("samantha"))
                return "Samantha";
            else System.out.println("Invalid input");
        }


    }
}
