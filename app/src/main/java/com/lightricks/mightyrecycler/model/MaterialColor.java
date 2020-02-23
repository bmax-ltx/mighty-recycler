package com.lightricks.mightyrecycler.model;

import android.graphics.Color;

public class MaterialColor {
    private final String name;
    private final String colorString;

    MaterialColor(String name, String colorString) {
        this.name = name;
        this.colorString = colorString;
    }

    public String getName() {
        return name;
    }

    public String getColorString() {
        return colorString;
    }

    public int getColorInt() {
        return Color.parseColor(colorString);
    }
}
