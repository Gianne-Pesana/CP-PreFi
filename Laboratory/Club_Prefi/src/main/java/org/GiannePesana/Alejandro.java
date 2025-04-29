package org.GiannePesana;

import java.util.Random;

public class Alejandro extends Character {

    private Random random = new Random();

    public Alejandro() {
        super("Alejandro", 5);
    }

    // handles movement logic including movement buff
    @Override
    public int rollMove() {
        int move = random.nextInt(4) + 1; // 1 to 4
        if (toiletBuff) {
            move += 2;
            toiletBuff = false;
        }
        return move;
    }


    @Override
    public void applyTileEffect(String tile) {
        switch (tile) {
            case "😎":
                enjoymentBar -= 2;
                System.out.println(name + " got intimidated talking to 😎 (-2 Enjoyment)");
                break;
            case "🍸":
                enjoymentBar += 1;
                System.out.println(name + " enjoyed some drinks 🍸 (+1 Enjoyment)");
                break;
            case "🎶":
                enjoymentBar += 1;
                System.out.println(name + " enjoyed some music 🎶 (+1 Enjoyment)");
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
                System.out.println(name + " is vibing...");
        }
    }
}