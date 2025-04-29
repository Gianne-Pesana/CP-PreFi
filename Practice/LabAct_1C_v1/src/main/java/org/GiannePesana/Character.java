package org.GiannePesana;

public class Character {
    String name = null;
    String characterEmoji = null;
    int enjoymentBars = 0;

    String nerf = null;
    int nerfValue = 0;

    String buff = null;
    int buffValue = 0;

    int moveMin;
    int moveMax;

    public Character(String name) {
        this.name = name;
        if (name.equalsIgnoreCase("alejandro")) {
            characterEmoji = "👦🏼";
            enjoymentBars = 5;
            nerf = "😎";
            nerfValue = 2;

            buff = "🍸🎶";
            buffValue = 1;

            moveMin = 1;
            moveMax = 4;
        } else if (name.equalsIgnoreCase("samantha")){
            characterEmoji = "👧🏼";
            enjoymentBars = 8;
            nerf = "🍸";
            nerfValue = 3;

            buff = "😎🎶";
            buffValue = 1;
            moveMin = 2;
            moveMax = 6;
        }

    }

    public void updateEnjoyment(String tile) {
        if (tile.contains(nerf)) {
            enjoymentBars -= nerfValue;
        } else if (tile.contains(buff)) {
            enjoymentBars += buffValue;
        }
    }

    public void showEnjoymentBar() {
        for (int i = 0; i < enjoymentBars; i++) {
            System.out.print("⚡");
        }
    }
}
