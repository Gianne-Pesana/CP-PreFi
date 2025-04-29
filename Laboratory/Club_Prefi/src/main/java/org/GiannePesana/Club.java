package org.GiannePesana;

public class Club {
    private final String[] clubTilesReference = {
            "🤮", "🍸", "🎶", "😎", "🚽", "🔐", "😎", "🍸", "🎶", "😎",
            "🚽", "🍸", "😎", "🔐", "🍸", "👧", "🎶", "🤮"
    };

    private String[] clubTiles;

    public Club(boolean isAlejandro) {
        // Deep copy the reference
        clubTiles = new String[clubTilesReference.length];
        System.arraycopy(clubTilesReference, 0, clubTiles, 0, clubTilesReference.length);

        // Set the starting positions based on the chosen character
        if (isAlejandro) {
            clubTiles[1] = "👦"; // Alejandro starts at index 1
            // Samantha is already at index 15 from the reference
        } else {
            clubTiles[1] = "👧"; // Samantha starts at index 1
            clubTiles[15] = "👦"; // Alejandro at index 15
        }
    }

    public void updatePosition(int oldPosition, int newPosition, String symbol) {
        clubTiles[oldPosition] = clubTilesReference[oldPosition];
        if (clubTiles[newPosition].equals("👧") || clubTiles[newPosition].equals("👦")) {
            // When characters meet, show them side by side
            if (symbol.equals("👦")) {
                clubTiles[newPosition] = "👦👧";
            } else {
                clubTiles[newPosition] = "👦👧";
            }
        } else {
            clubTiles[newPosition] = symbol;
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