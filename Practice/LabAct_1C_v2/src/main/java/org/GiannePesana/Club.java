package org.GiannePesana;

public class Club {
    private final String[] clubTilesReference = {
            "🤮", "🍸", "🎶", "😎", "🚽", "🔐", "😎", "🍸", "🎶", "😎",
            "🚽", "🍸", "😎", "🔐", "🍸", "👧", "🎶", "🤮"
    };

    private String[] clubTiles = {
            "🤮", "🍸", "🎶", "😎", "🚽", "🔐", "😎", "🍸", "🎶", "😎",
            "🚽", "🍸", "😎", "🔐", "🍸", "👧", "🎶", "🤮"
    };

    public void updatePosition(int oldPos, int newPos, String symbol) {
        clubTiles[oldPos] = clubTilesReference[oldPos];
        if (clubTiles[newPos].equals("👧") || clubTiles[newPos].equals("👦")) {
            clubTiles[newPos] = "👦👧"; // when meeting
        } else {
            clubTiles[newPos] = symbol;
        }
    }

    public String getTile(int position) {
        return clubTiles[position];
    }

    public void display() {
        for (String tile : clubTiles) {
            System.out.print("[" + tile + "]");
        }
        System.out.println();
    }
}

