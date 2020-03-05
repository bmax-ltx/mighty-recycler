package com.lightricks.mightyrecycler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Collection of material design colors
 */
public class ColorPalette {
    private List<MaterialColor> allColors = new ArrayList<>();
    private Random random = new Random();

    public ColorPalette() {
        allColors.add(new MaterialColor("Red", "#F44336"));
        allColors.add(new MaterialColor("Pink", "#E91E63"));
        allColors.add(new MaterialColor("Purple", "#9C27B0"));
        allColors.add(new MaterialColor("Deep Purple", "#673AB7"));
        allColors.add(new MaterialColor("Indigo", "#3F51B5"));
        allColors.add(new MaterialColor("Blue", "#1E88E5"));
        allColors.add(new MaterialColor("Light Blue", "#0288D1"));
        allColors.add(new MaterialColor("Cyan", "#0097A7"));
        allColors.add(new MaterialColor("Teal", "#009688"));
        allColors.add(new MaterialColor("Green", "#43A047"));
        allColors.add(new MaterialColor("Light Green", "#558B2F"));
        allColors.add(new MaterialColor("Lime", "#827717"));
        allColors.add(new MaterialColor("Yellow", "#F57F17"));
        allColors.add(new MaterialColor("Amber", "#FF6F00"));
        allColors.add(new MaterialColor("Orange", "#E65100"));
        allColors.add(new MaterialColor("Deep Orange", "#F4511E"));
        allColors.add(new MaterialColor("Brown", "#795548"));
        allColors.add(new MaterialColor("Gray", "#757575"));
        allColors.add(new MaterialColor("Blue Gray", "#607D8B"));
    }

    public List<MaterialColor> getAllColors() {
        return new ArrayList<>(allColors);
    }

    public List<MaterialColor> getRandomColors(int n) {
        return random.ints(0, allColors.size())
                .limit(n)
                .mapToObj(allColors::get)
                .collect(toList());
    }

    public List<MaterialColor> getNColors(int n) {
        return allColors.stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}
