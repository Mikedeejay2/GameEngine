package me.mikedeejay2.GameEngine.gfx;

public class Font {
    public static final Font STANDARD = new Font("/res/fonts/standard2.png");
    
    private Image fontImage;
    private int[] offsets;
    private int[] widths;
    
    public Font(String path) {
        fontImage = new Image(path);
        
        offsets = new int[256];
        widths = new int[256];
        
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

    public Image getFontImage() {
        return fontImage;
    }
    
    public void setFontImage(Image fontImage) {
        this.fontImage = fontImage;
    }
    
    public int[] getOffsets() {
        return offsets;
    }
    
    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }
    
    public int[] getWidths() {
        return widths;
    }
    
    public void setWidths(int[] widths) {
        this.widths = widths;
    }
    
}
