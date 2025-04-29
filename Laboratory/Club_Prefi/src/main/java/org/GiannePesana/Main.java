package org.GiannePesana;

import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character player;
        int turn = 0;
        String[] times = {"@ 8pm", "@ 9pm", "@ 10pm", "@ 11pm", "@ 12am", "@ 1am", "@ 2am", "@ 3am"};

        System.out.println("Choose your character: (1) Alejandro 👦 or (2) Samantha 👧");
        int choice = scanner.nextInt();
        boolean isAlejandro = (choice == 1);

        // Initialize club with the chosen character
        Club club = new Club(isAlejandro);

        if (isAlejandro) {
            player = new Alejandro();
            System.out.println("Alejandro got lost in the crowd at the club and wants to meet back with Samantha.");
        } else {
            player = new Samantha();
            System.out.println("Samantha got separated from Alejandro in the chaos and hopes to find him again.");
        }

        while (turn < 8 && !player.isOutOfEnergy()) {
            System.out.println("\n" + times[turn]);
            System.out.println(player.name + "'s Enjoyment Bar: " + player.renderEnjoymentBar());
            club.display();

            int move = player.rollMove();
            System.out.println(player.name + " rolled " + move);

            int oldPosition = player.position;
            player.position += move;
            if (player.position >= 18) {
                // Wrap around the array instead of staying at the last tile
                player.position = player.position % 18;
                System.out.println(player.name + " has gone all the way around the club!");
            }

            // Update position in club
            club.updatePosition(oldPosition, player.position, player instanceof Alejandro ? "👦" : "👧");

            // Check if met (do this before applying tile effect)
            if (club.getTile(player.position).equals("👦👧")) {
                System.out.println(player.name + " met up with their friend! Time to leave.");
                break;
            }

            // Apply tile effect
            String landedTile = club.getTile(player.position);
            player.applyTileEffect(landedTile);

            turn++;
        }

        // Ending message
        if (player.isOutOfEnergy()) {
            System.out.println(player.name + " passed out from exhaustion and had to be carried home. What a night to forget!");
        } else if (turn >= 8) {
            System.out.println("As the night came to an end, " + player.name + " never found their friend. " +
                    "They left the club with a mix of disappointment and " +
                    (player.enjoymentBar > 5 ? "great memories from a wild night!" : "relief that it was finally over."));
        } else {
            System.out.println(player.name + " and their friend reunited! They decided to grab late-night food " +
                    "and share stories about their separate adventures at the club.");
        }
    }
}