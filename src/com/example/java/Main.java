package com.example.java;

import edu.princeton.cs.introcs.Picture;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Picture pic = new Picture("4.jpg");
        int width = pic.width();
        int height = pic.height();
        int rWidth = (int) Math.ceil(pic.width() / 9);
        int rHeight = (int) Math.ceil(pic.height() / 9);
        Picture res = new Picture(rWidth, rHeight);
        java.util.List<Integer> key_x = new ArrayList<Integer>();
        java.util.List<Integer> key_y = new ArrayList<Integer>();

        for (int i = 0; i < rWidth; i++) {
            for (int j = 0; j < rHeight; j++) {
                int red = 0;
                int green = 0;
                int blue = 0;
                int count = 0;

                for (int x = i * 9; x < Math.min((i * 9) + 9, width); x++) {
                    for (int y = j * 9; y < Math.min((j * 9) + 9, height); y++) {
                        count++;
                        Color pixel = pic.get(x, y);
                        red += pixel.getRed();
                        green += pixel.getGreen();
                        blue += pixel.getBlue();
                    }
                }
                red = red / count;
                green = green / count;
                blue = blue / count;
                int lum = luminance(new Color(red, green, blue));

                if (lum < 80) res.set(i, j, new Color(0, 0, 0));
                else res.set(i, j, new Color(255, 255, 255));

                if (i + 1 < rWidth && i + 2 < rWidth && j + 1 < rHeight && j + 2 < rHeight && 0 < i - 1 && 0 < i - 2 && 0 < j - 1 && 0 < j - 2) {
                    if (luminance(res.get(i, j)) == 0 && luminance(res.get(i + 1, j)) == 0 && luminance(res.get(i - 1, j)) == 0 &&
                            luminance(res.get(i + 2, j)) == 0 && luminance(res.get(i - 2, j)) == 0 &&
                            luminance(res.get(i, j + 1)) == 0 && luminance(res.get(i, j - 1)) == 0 && luminance(res.get(i, j + 2)) == 0
                            && luminance(res.get(i, j - 2)) == 0
                            && luminance(res.get(i + 1, j + 1)) == 0
                            && luminance(res.get(i - 1, j - 1)) == 0 && luminance(res.get(i + 1, j - 1)) == 0 && luminance(res.get(i - 1, j + 1)) == 0
                            && luminance(res.get(i + 2, j + 2)) == 0
                            && luminance(res.get(i - 2, j - 2)) == 0 && luminance(res.get(i + 2, j - 2)) == 0 && luminance(res.get(i - 2, j + 2)) == 0
                    ) {
                        key_x.add(i);
                        key_y.add(j);

                    }
                }

            }
        }
        res.save("5.jpg");
        res.show();
        findEdges find=new findEdges();
        find.main(null);
    }

    private static int luminance(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        return (int) ((0.299 * red) + (0.587 * green) + (0.114 * blue));
    }
}
