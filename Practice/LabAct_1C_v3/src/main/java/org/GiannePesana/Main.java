package org.GiannePesana;

import java.util.Scanner;
import java.util.Random;

public class Main {
    // Constants for emojis
    private static final String VOMIT = "🤮";
    private static final String BOY = "👦";
    private static final String GIRL = "👧";
    private static final String MUSIC = "🎶";
    private static final String COOL_PERSON = "😎";
    private static final String DRINK = "🍸";
    private static final String TOILET = "🚽";
    private static final String LOCK = "🔐";
    private static final String ENERGY = "⚡";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to Club Adventure!");
        System.out.println("Choose your character:");
        System.out.println("1. Alejandro - Enjoys music and drinks, but has social anxiety");
        System.out.println("2. Samantha - Extroverted and loves meeting people, but avoids alcohol");

        int choice = getUserChoice(scanner, 1, 2);
        boolean isAlejandro = (choice == 1);

        // Initialize club array
        String[] club = new String[18];
        club[0] = VOMIT;
        club[1] = DRINK;
        club[2] = MUSIC;
        club[3] = COOL_PERSON;
        club[4] = TOILET;
        club[5] = LOCK;
        club[6] = COOL_PERSON;
        club[7] = DRINK;
        club[8] = MUSIC;
        club[9] = COOL_PERSON;
        club[10] = TOILET;
        club[11] = DRINK;
        club[12] = COOL_PERSON;
        club[13] = LOCK;
        club[14] = DRINK;
        club[15] = isAlejandro ? GIRL : BOY; // Other character
        club[16] = MUSIC;
        club[17] = VOMIT;

        // Place chosen character at index 1
        int playerPosition = 1;
        int otherPosition = 15;
        club[playerPosition] = isAlejandro ? BOY : GIRL;

        // Set initial enjoyment bars
        int enjoymentBars = isAlejandro ? 5 : 8;

        // Game loop
        boolean gameOver = false;
        int turn = 0;
        int toiletBoost = 0;

        while (!gameOver && turn < 8) {
            // Display current time
            System.out.println("\n@ " + (8 + turn) + "pm");

            // Display enjoyment bars
            System.out.print(isAlejandro ? "Alejandro" : "Samantha");
            System.out.print("'s Enjoyment Bar: ");
            for (int i = 0; i < enjoymentBars; i++) {
                System.out.print(ENERGY);
            }
            System.out.println();

            // Display club
            printClub(club);

            // Roll dice and move
            int minMove = isAlejandro ? 1 : 2;
            int maxMove = isAlejandro ? 4 : 6;

            // If toilet boost is active, increase max movement
            if (toiletBoost > 0) {
                maxMove += 2;
                toiletBoost--;
                System.out.println((isAlejandro ? "Alejandro" : "Samantha") + " got a second wind after a toilet break. +2 to maximum roll!");
            }

            int roll = random.nextInt(maxMove - minMove + 1) + minMove;
            System.out.println((isAlejandro ? "Alejandro" : "Samantha") + " rolled " + roll + ".");

            // Update position
            club[playerPosition] = getTileAtPosition(playerPosition);
            playerPosition = Math.min(playerPosition + roll, 17);
            String landedTile = club[playerPosition];

            // Check if characters meet
            if (playerPosition == otherPosition) {
                club[playerPosition] = (isAlejandro ? BOY : GIRL) + (isAlejandro ? GIRL : BOY);
                System.out.println((isAlejandro ? "Alejandro" : "Samantha") + " met up with " +
                        (isAlejandro ? "Samantha" : "Alejandro") + ".");
                endGame(isAlejandro, enjoymentBars, "reunion");
                gameOver = true;
                break;
            }

            // Process landing on tile
            club[playerPosition] = isAlejandro ? BOY : GIRL;

            switch (landedTile) {
                case MUSIC:
                    enjoymentBars++;
                    System.out.println((isAlejandro ? "Alejandro" : "Samantha") +
                            " enjoyed some music " + MUSIC);
                    break;
                case COOL_PERSON:
                    if (isAlejandro) {
                        enjoymentBars -= 2;
                        System.out.println("Alejandro got intimidated talking to " + COOL_PERSON);
                    } else {
                        enjoymentBars++;
                        System.out.println("Samantha had a great conversation with " + COOL_PERSON);
                    }
                    break;
                case DRINK:
                    if (isAlejandro) {
                        enjoymentBars++;
                        System.out.println("Alejandro enjoyed a refreshing " + DRINK);
                    } else {
                        enjoymentBars -= 3;
                        System.out.println("Samantha was offered a " + DRINK + " and politely declined, feeling uncomfortable");
                    }
                    break;
                case VOMIT:
                    enjoymentBars -= 3;
                    System.out.println((isAlejandro ? "Alejandro" : "Samantha") +
                            " stepped in something gross " + VOMIT + " and lost enjoyment");
                    break;
                case TOILET:
                    System.out.println((isAlejandro ? "Alejandro" : "Samantha") +
                            " found the " + TOILET + ". Next movement will be boosted by 2!");
                    toiletBoost = 1;
                    break;
                case LOCK:
                    System.out.println((isAlejandro ? "Alejandro" : "Samantha") +
                            " got caught by security " + LOCK + " and was escorted back to the entrance");
                    playerPosition = 1;
                    club[playerPosition] = isAlejandro ? BOY : GIRL;
                    break;
            }

            // Check if enjoyment bars are depleted
            if (enjoymentBars <= 0) {
                endGame(isAlejandro, 0, "depleted");
                gameOver = true;
            }

            turn++;
        }

        // If game ended due to maximum turns
        if (!gameOver) {
            endGame(isAlejandro, enjoymentBars, "timeUp");
        }

        scanner.close();
    }

    // Helper method to get user choice
    private static int getUserChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < min || choice > max) {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return choice;
    }

    // Helper method to print the club array
    private static void printClub(String[] club) {
        System.out.print("[");
        for (int i = 0; i < club.length; i++) {
            System.out.print(club[i]);
            if (i < club.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Helper method to get the original tile at a position
    private static String getTileAtPosition(int position) {
        switch (position) {
            case 0:
            case 17:
                return VOMIT;
            case 1:
            case 7:
            case 11:
            case 14:
                return DRINK;
            case 2:
            case 8:
            case 16:
                return MUSIC;
            case 3:
            case 6:
            case 9:
            case 12:
                return COOL_PERSON;
            case 4:
            case 10:
                return TOILET;
            case 5:
            case 13:
                return LOCK;
            case 15:
                return BOY; // This should be the opposite character but for simplicity using BOY
            default:
                return " ";
        }
    }

    // Helper method to handle game ending
    private static void endGame(boolean isAlejandro, int enjoymentBars, String reason) {
        System.out.println("\n----- Club Night Ended -----");

        switch (reason) {
            case "depleted":
                if (isAlejandro) {
                    System.out.println("Alejandro's social anxiety got the best of him. After too many awkward encounters,\n" +
                            "he texted Samantha that he wasn't feeling well and headed home early,\n" +
                            "promising to make it up to her next weekend.");
                } else {
                    System.out.println("Samantha couldn't take it anymore. After being offered drinks repeatedly\n" +
                            "throughout the night, she felt out of place and uncomfortable.\n" +
                            "She sent Alejandro a quick message and called a rideshare home,\n" +
                            "wishing she had suggested a different activity instead.");
                }
                break;

            case "reunion":
                if (enjoymentBars > (isAlejandro ? 3 : 5)) {
                    System.out.println((isAlejandro ? "Alejandro" : "Samantha") + " had a great time at the club despite\n" +
                            "the initial separation. When they finally found each other,\n" +
                            "they decided to go for late-night pancakes and laughed about their adventures.");
                } else {
                    System.out.println((isAlejandro ? "Alejandro" : "Samantha") + " ended the night with mediocre enjoyment.\n" +
                            "When they finally reconnected with " + (isAlejandro ? "Samantha" : "Alejandro") + ",\n" +
                            "they both agreed it was time to head home and try a quieter venue next time.");
                }
                break;

            case "timeUp":
                if (enjoymentBars > (isAlejandro ? 3 : 5)) {
                    System.out.println("As the club announced last call, " + (isAlejandro ? "Alejandro" : "Samantha") + "\n" +
                            "realized they had spent the entire night without finding\n" +
                            (isAlejandro ? "Samantha" : "Alejandro") + ". Surprisingly, they had such a good time\n" +
                            "that they weren't even upset. They texted to meet outside and shared stories\n" +
                            "of their separate adventures on the ride home.");
                } else {
                    System.out.println("When the lights came on at closing time, " + (isAlejandro ? "Alejandro" : "Samantha") + "\n" +
                            "was relieved the night was finally over. They met " + (isAlejandro ? "Samantha" : "Alejandro") + "\n" +
                            "outside as planned, both looking exhausted. \"Next time, let's just do a movie night,\" they agreed.");
                }
                break;
        }

        // Final enjoyment rating
        System.out.print("Final enjoyment level: ");
        for (int i = 0; i < enjoymentBars; i++) {
            System.out.print(ENERGY);
        }
        System.out.println("\n");

        if (enjoymentBars <= 2) {
            System.out.println("Rating: Terrible night");
        } else if (enjoymentBars <= 4) {
            System.out.println("Rating: Mediocre experience");
        } else if (enjoymentBars <= 6) {
            System.out.println("Rating: Pretty good time");
        } else {
            System.out.println("Rating: Amazing night to remember!");
        }
    }
}