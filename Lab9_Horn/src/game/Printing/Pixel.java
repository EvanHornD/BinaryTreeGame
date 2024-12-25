package game.Printing;

import game.Utils.Color;

public class Pixel {
    private char character = ' ';
    private int color = 0xff000000;
    private int backGroundColor = 0x00ffffff;

    public static final Pixel NULL_PIXEL = new Pixel(' ',0xff000000,0x00ffffff);

    //
    // Private Constructors to force the use of the factory methods
    //

    public static Pixel Create(){
        return NULL_PIXEL;
    }

    private Pixel(char character){
        this.character = character;
    }

    private Pixel(int backGroundColor){
        this.backGroundColor = backGroundColor;
    }

    private Pixel(char character, int color){
        this.character = character;
        this.color = color;
    }

    private Pixel(char character, int color, int backGroundColor){
        this.character = character;
        this.color = color;
        this.backGroundColor = backGroundColor;
    }

    //
    // factory methods which take into account the possibility of Null Pixels existing
    //

    public static Pixel create(char character){
        if(character==' '){
            return NULL_PIXEL;
        }
        return new Pixel(character);
    }

    public static Pixel create(int backGroundColor){
        if(backGroundColor==0x00ffffff){
            return NULL_PIXEL;
        }
        return new Pixel(backGroundColor);
    }

    public static Pixel create(Color backGroundColor){
        return create(backGroundColor.getColor());
    }

    public static Pixel create(char character, int color){
        if(character==' '){
            return NULL_PIXEL;
        }
        return new Pixel(character, color);
    }

    public static Pixel create(char character, Color color){
        return create(character, color.getColor());
    }

    public static Pixel create(char character, int color, int backGroundColor){
        if(character==' '&&backGroundColor==0x00ffffff){
            return NULL_PIXEL;
        }
        return new Pixel(character,color,backGroundColor);
    }

    public static Pixel create(char character, Color color, Color backGroundColor){
        return create(character, color.getColor(), backGroundColor.getColor());
    }

    //
    // setters which take into account the possibility of the pixel being a NULL Pixel
    //

    public Pixel setCharacter(char character) {
        if (this == NULL_PIXEL) {
            return Pixel.create(character);
        }
        this.character = character;
        return this;
    }

    public Pixel setColor(int color) {
        if (this == NULL_PIXEL) {
            return this;
        }
        this.color = color;
        return this;
    }

    public Pixel setColor(Color color) {
        return setColor(color.getColor());
    }

    public Pixel setBackGroundColor(int backGroundColor) {
        if (this == NULL_PIXEL) {
            return Pixel.create(backGroundColor);
        }
        this.backGroundColor = backGroundColor;
        return this;
    }

    public Pixel setBackGroundColor(Color backGroundColor) {
        return setBackGroundColor(backGroundColor.getColor());
    }

    //
    // regular getters
    // 

    public int getColor() {
        return color;
    }

    public char getCharacter() {
        return character;
    }

    public int getBackGroundColor() {
        return backGroundColor;
    }

    //
    // properly overlaps pixels based on their attributes
    //

    public static Pixel overlayPixels(Pixel topPixel, Pixel bottomPixel){
        char topCharacter = topPixel.getCharacter();
        char bottomCharacter = bottomPixel.getCharacter();
        int topColor = topPixel.getColor();
        int bottomColor = bottomPixel.getColor();
        int topBackGround = topPixel.getBackGroundColor();
        int bottomBackGround = bottomPixel.getBackGroundColor();

        // this single check is the reason for the null pixels existance because there can be many nullpixels which would all be computed many times persecond and this saves on runtime
        if(topPixel==NULL_PIXEL){
            return new Pixel(bottomCharacter, bottomColor, bottomBackGround);
        }
        
        if(Color.getAlpha(topBackGround)==255){
            return new Pixel(topCharacter, topColor, topBackGround);
        }

        char newChar = topCharacter;
        int newColor = topColor;

        if(topCharacter==' '){
            newChar = bottomCharacter;
            newColor = Color.overlayColor(topBackGround, bottomColor);
        }

        return new Pixel(newChar, newColor, Color.overlayColor(topBackGround, bottomBackGround));
    }

    @Override
    public String toString() {
        if (this == NULL_PIXEL) {
            return "_";
        }
        return ""+character;
    }
}
