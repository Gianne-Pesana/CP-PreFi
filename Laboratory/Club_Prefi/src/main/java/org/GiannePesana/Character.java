package org.GiannePesana;

public abstract class Character {
    protected String name;
    protected int position;
    protected int enjoymentBar;
    protected boolean toiletBuff;

    public Character(String name, int startEnjoyment) {
        this.name = name;
        this.enjoymentBar = startEnjoyment;
        this.position = 1; // Always start at index 1
        this.toiletBuff = false;
    }

    public abstract int rollMove();
    public abstract void applyTileEffect(String tile);

    public boolean isOutOfEnergy() {
        return enjoymentBar <= 0;
    }

    public String renderEnjoymentBar() {
        String bar = "";
        for (int i = 0; i < enjoymentBar; i++) {
            bar += "⚡";
        }
        return bar;
    }
}

