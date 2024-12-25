package game.Utils;

public class Color {


    public static final int WHITE = 0xffffffff;
    public static final int BLACK = 0xff000000;

    public int alpha;
    public int red;
    public int green;
    public int blue;

    public Color(int color){
        this.alpha = (color>>24)&0xff;
        this.red = (color>>16)&0xff;
        this.green = (color>>8)&0xff;
        this.blue = (color)&0xff;
    }

    public Color(int red, int green, int blue){
        this.alpha = 0xff;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color(int alpha, int red, int green, int blue){
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getColor(){
        int color = alpha<<24;
            color = color|(red<<16);
            color = color|(green<<8);
            color = color|(blue);
        return color;
    }

    public static int getAlpha(int color){
        int alpha = (color>>24)&0xff;
        return alpha;
    }

    public static int overlayColor(int top, int bottom){

        Color topColor = new Color(top);
        Color bottomColor = new Color(bottom);
        // normalize the alpha values to values between 0 and 1
        float topAlpha = (float) (255.0/topColor.alpha);
        float bottomAlpha = (float) (255.0/bottomColor.alpha);

        int overlayedAlpha = (int) ((255-topColor.alpha)*bottomAlpha)+topColor.alpha;
        int overlayedRed = (int) (((1-topAlpha)*bottomAlpha*(bottomColor.red  ))+topColor.alpha*(topColor.red  ))/overlayedAlpha;
        int overlayedGreen = (int) (((1-topAlpha)*bottomAlpha*(bottomColor.green))+topColor.alpha*(topColor.green))/overlayedAlpha;
        int overlayedBlue = (int) (((1-topAlpha)*bottomAlpha*(bottomColor.blue ))+topColor.alpha*(topColor.blue ))/overlayedAlpha;

        return new Color(overlayedAlpha, overlayedRed, overlayedGreen, overlayedBlue).getColor();
    }
}
