package com.lightricks.mightyrecycler.model;

import android.graphics.Color;

import com.lightricks.mightyrecycler.util.Identifiable;

import java.util.Objects;

public class MaterialColor implements Identifiable {
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

    @Override
    public String getId() {
        return getColorString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialColor that = (MaterialColor) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(colorString, that.colorString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, colorString);
    }
}
