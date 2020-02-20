package com.lightricks.mightyrecycler.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class MaterialColors {
    private List<MaterialColor> allColors = new ArrayList<>();
    private Random random = new Random();

    public MaterialColors() {
        allColors.add(new MaterialColor("Red", "#B71C1C"));
        allColors.add(new MaterialColor("Pink", "#880E4F"));
        allColors.add(new MaterialColor("Purple", "#4A148C"));
        allColors.add(new MaterialColor("Deep Purple", "#311B92"));
        allColors.add(new MaterialColor("Indigo", "#1A237E"));
        allColors.add(new MaterialColor("Blue", "#0D47A1"));
        allColors.add(new MaterialColor("Light Blue", "#01579B"));
        allColors.add(new MaterialColor("Cyan", "#006064"));
        allColors.add(new MaterialColor("Teal", "#004D40"));
        allColors.add(new MaterialColor("Green", "#1B5E20"));
        allColors.add(new MaterialColor("Light Green", "#33691E"));
        allColors.add(new MaterialColor("Lime", "#827717"));
        allColors.add(new MaterialColor("Yellow", "#F57F17"));
        allColors.add(new MaterialColor("Amber", "#FF6F00"));
        allColors.add(new MaterialColor("Orange", "#E65100"));
        allColors.add(new MaterialColor("Deep Orange", "#BF360C"));
        allColors.add(new MaterialColor("Brown", "#3E2723"));
        allColors.add(new MaterialColor("Gray", "#212121"));
        allColors.add(new MaterialColor("Blue Gray", "#263238"));
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
}
