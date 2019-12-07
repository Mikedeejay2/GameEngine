package com.mikedeejay2.engine.gfx;

public class Font {
    private Image fontImage;
    private int[] offsets;
    private int[] widths;

    public Font(String path) {
        fontImage = new Image(path);

        offsets = new int[58];
        widths = new int[58];

        int unicode = 0;

        for(int i = 0; i < fontImage.getW(); i++) {
            if(fontImage.getP()[i] == 0xff0000ff) {
                offsets[unicode] = i;
            }

            if(fontImage.getP()[i] == 0xffffff00) {
                widths[unicode] = i - offsets[unicode];
                unicode++;
            }
        }
    }
}
