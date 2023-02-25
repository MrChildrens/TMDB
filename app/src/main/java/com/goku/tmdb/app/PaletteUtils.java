package com.goku.tmdb.app;

import androidx.palette.graphics.Palette;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaletteUtils {

    public static int[] getColorRandom(Palette palette) {
        int[] vibrant = getVibrantColor(palette);
        int[] vibrantDark = getDarkVibrantColor(palette);
        int[] vibrantLight = getLightVibrantColor(palette);
        int[] muted = getMutedColor(palette);
        int[] mutedDark = getDarkMutedColor(palette);
        int[] mutedLight = getLightMutedColor(palette);
        List<int[]> list = new ArrayList<int[]>();
        list.clear();
        list.add(vibrant);
        list.add(vibrantDark);
        list.add(vibrantLight);
        list.add(muted);
        list.add(mutedDark);
        list.add(mutedLight);
        for (int i = 0; i < list.size(); i++) {
            int[] arry = list.get(i);
            if (arry == null) list.remove(arry);
        }
        int[] arry = list.get(new Random().nextInt(list.size() - 1));
        return arry;
    }

    public static int[] getVibrantColor(Palette palette) {
        if (palette == null || palette.getVibrantSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = palette.getVibrantSwatch().getTitleTextColor();
        arry[1] = palette.getVibrantSwatch().getBodyTextColor();
        arry[2] = palette.getVibrantSwatch().getRgb();
        return arry;
    }

    public static int[] getDarkVibrantColor(Palette palette) {
        if (palette == null || palette.getDarkVibrantSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = palette.getDarkVibrantSwatch().getTitleTextColor();
        arry[1] = palette.getDarkVibrantSwatch().getBodyTextColor();
        arry[2] = palette.getDarkVibrantSwatch().getRgb();
        return arry;
    }

    public static int[] getLightVibrantColor(Palette palette) {
        if (palette == null || palette.getLightVibrantSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = palette.getLightVibrantSwatch().getTitleTextColor();
        arry[1] = palette.getLightVibrantSwatch().getBodyTextColor();
        arry[2] = palette.getLightVibrantSwatch().getRgb();
        return arry;
    }

    public static int[] getMutedColor(Palette palette) {
        if (palette == null || palette.getMutedSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = palette.getMutedSwatch().getTitleTextColor();
        arry[1] = palette.getMutedSwatch().getBodyTextColor();
        arry[2] = palette.getMutedSwatch().getRgb();
        return arry;
    }

    public static int[] getDarkMutedColor(Palette palette) {
        if (palette == null || palette.getDarkMutedSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = palette.getDarkMutedSwatch().getTitleTextColor();
        arry[1] = palette.getDarkMutedSwatch().getBodyTextColor();
        arry[2] = palette.getDarkMutedSwatch().getRgb();
        return arry;
    }

    public static int[] getLightMutedColor(Palette palette) {
        if (palette == null || palette.getLightMutedSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = palette.getLightMutedSwatch().getTitleTextColor();
        arry[1] = palette.getLightMutedSwatch().getBodyTextColor();
        arry[2] = palette.getLightMutedSwatch().getRgb();
        return arry;
    }

}
