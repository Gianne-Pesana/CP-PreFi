package org.GiannePesana;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character player;
        Club club = new Club();
        int turn = 0;
        String[] times = {"@ 8pm", "@ 9pm", "@ 10pm", "@ 11pm", "@ 12am", "@ 1am", "@ 2am", "@ 3am"};

        System.out.println("Choose your character: (1) Alejandro 👦 or (2) Samantha 👧");
        int choice = scanner.nextInt();

        if (choice == 1) {
            player = new Alejandro();
        } else {
            player = new Samantha();
        }

        while (turn < 8 && !player.isOutOfEnergy()) {
            System.out.println("\n" + times[turn]);
            System.out.println(player.name + "'s Enjoyment Bar: " + player.renderEnjoymentBar());
            club.display();

            int move = player.rollMove();
            System.out.println(player.name + " rolled " + move);

            int oldPosition = player.position;
            player.position += move;
            if (player.position >= 18) player.position = 17; // stay at the last tile

            // Apply tile effect
            String landedTile = club.getTile(player.position);
            player.applyTileEffect(landedTile);

            // Update position in club
            club.updatePosition(oldPosition, player.position, player instanceof Alejandro ? "👦" : "👧");

            // Check if met
            if (club.getTile(player.position).equals("👦👧")) {
                System.out.println(player.name + " met up with their friend! Time to leave.");
                break;
            }

            turn++;
        }

        // Ending message
        if (player.isOutOfEnergy()) {
            System.out.println(player.name + " passed out from low enjoyment and left early...");
        } else {
            System.out.println(player.name + " ended the night with good vibes!");
        }
    }
}
