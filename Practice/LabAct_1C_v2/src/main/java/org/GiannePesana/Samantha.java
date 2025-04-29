package org.GiannePesana;

import java.util.Random;

public class Samantha extends Character {

    private Random random = new Random();

    public Samantha() {
        super("Samantha", 8);
    }

    @Override
    public int rollMove() {
        int move = random.nextInt(5) + 2; // 2 to 6
        if (toiletBuff) {
            move += 2;
            toiletBuff = false;
        }
        return move;
    }

    @Override
    public void applyTileEffect(String tile) {
        switch (tile) {
            case "🍸":
                enjoymentBar -= 3;
                System.out.println(name + " is a teetotaler and hated the 🍸 (-3 Enjoyment)");
                break;
            case "😎":
            case "🎶":
                enjoymentBar += 1;
                System.out.println(name + " enjoyed " + tile + " (+1 Enjoyment)");
                break;
            case "🚽":
                toiletBuff = true;
                System.out.println(name + " got a second wind after a toilet break! +2 Move next turn");
                break;
            case "🔐":
                position = 1;
                System.out.println(name + " got stuck at 🔐 and returned to the entrance!");
                break;
            case "🤮":
                enjoymentBar -= 3;
                System.out.println(name + " felt sick 🤮 (-3 Enjoyment)");
                break;
            default:
                System.out.println(name + " is socializing...");
        }
    }
}

