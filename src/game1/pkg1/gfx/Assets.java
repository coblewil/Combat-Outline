package game1.pkg1.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 *
 * @author acob1
 */
public class Assets {

    //make cropping into a method? i.e. pass in which sheet, then width, height, frame number and direction
    private static final int WIDTH = 320, HEIGHT = 255; //make this into a dimension or a rectangle, have multiple
    private static final int ATTACK1 = 33, ATTACK2 = 38; //number of images in an animation, have multiple
    //all images and animations
    public static Font font28;
    public static BufferedImage arrow;
    public static BufferedImage redStandingL, blueStandingL, yellowStandingL, greenStandingL, redStandingR, blueStandingR, yellowStandingR, greenStandingR;
    public static BufferedImage[] redAttack1L, blueAttack1L, yellowAttack1L, greenAttack1L, redAttack1R, blueAttack1R, yellowAttack1R, greenAttack1R, redAttack2L, blueAttack2L, yellowAttack2L, greenAttack2L, redAttack2R, blueAttack2R, yellowAttack2R, greenAttack2R;
    public static BufferedImage redIcon, blueIcon, yellowIcon, greenIcon;
    //END all images animations
    //temp
    //private static String rootPath = "C:/Users/acob1/OneDrive/Documents/Programming/NetBeansProjects/Other/Tutorials/Misc/2DGameProtoTypeTutorial/res";

    //END temp
    public static void init() {
        //temp
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        SpriteSheet sheetRedAttack1 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Red Guy/Stab/sprite sheet.png"));
        SpriteSheet sheetRedAttack2 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Red Guy/Air Attack/sprite sheet.png"));
        SpriteSheet sheetBlueAttack1 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Blue Guy/Stab/sprite sheet.png"));
        SpriteSheet sheetBlueAttack2 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Blue Guy/Air Attack/sprite sheet.png"));
        SpriteSheet sheetYellowAttack1 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Yellow Guy/Stab/sprite sheet.png"));
        SpriteSheet sheetYellowAttack2 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Yellow Guy/Air Attack/sprite sheet.png"));
        SpriteSheet sheetGreenAttack1 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Green Guy/Stab/sprite sheet.png"));
        SpriteSheet sheetGreenAttack2 = new SpriteSheet(ImageLoader.loadImage("res/textures/Fire Emblem/Green Guy/Air Attack/sprite sheet.png"));

        arrow = ImageLoader.loadImage("res/textures/Fire Emblem/Misc/arrow.png");
        
        redStandingL = ImageLoader.loadImage("res/textures/Fire Emblem/Red Guy/standing face left.png");
        redStandingR = ImageLoader.loadImage("res/textures/Fire Emblem/Red Guy/standing face right.png");
        blueStandingL = ImageLoader.loadImage("res/textures/Fire Emblem/Blue Guy/standing face left.png");
        blueStandingR = ImageLoader.loadImage("res/textures/Fire Emblem/Blue Guy/standing face right.png");
        yellowStandingL = ImageLoader.loadImage("res/textures/Fire Emblem/Yellow Guy/standing face left.png");
        yellowStandingR = ImageLoader.loadImage("res/textures/Fire Emblem/Yellow Guy/standing face right.png");
        greenStandingL = ImageLoader.loadImage("res/textures/Fire Emblem/Green Guy/standing face left.png");
        greenStandingR = ImageLoader.loadImage("res/textures/Fire Emblem/Green Guy/standing face right.png");
        
        redIcon = ImageLoader.loadImage("res/textures/Fire Emblem/Red Guy/icon.png");
        blueIcon = ImageLoader.loadImage("res/textures/Fire Emblem/Blue Guy/icon.png");
        yellowIcon = ImageLoader.loadImage("res/textures/Fire Emblem/Yellow Guy/icon.png");
        greenIcon = ImageLoader.loadImage("res/textures/Fire Emblem/Green Guy/icon.png");

        redAttack1L = new BufferedImage[ATTACK1];
        redAttack1R = new BufferedImage[ATTACK1];
        blueAttack1L = new BufferedImage[ATTACK1];
        blueAttack1R = new BufferedImage[ATTACK1];
        yellowAttack1L = new BufferedImage[ATTACK1];
        yellowAttack1R = new BufferedImage[ATTACK1];
        greenAttack1L = new BufferedImage[ATTACK1];
        greenAttack1R = new BufferedImage[ATTACK1];

        redAttack2L = new BufferedImage[ATTACK2];
        redAttack2R = new BufferedImage[ATTACK2];
        blueAttack2L = new BufferedImage[ATTACK2];
        blueAttack2R = new BufferedImage[ATTACK2];
        yellowAttack2L = new BufferedImage[ATTACK2];
        yellowAttack2R = new BufferedImage[ATTACK2];
        greenAttack2L = new BufferedImage[ATTACK2];
        greenAttack2R = new BufferedImage[ATTACK2];

        for (int i = 0; i < ATTACK1; i++) {
            redAttack1L[i] = sheetRedAttack1.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            redAttack1R[i] = sheetRedAttack1.crop(sheetRedAttack1.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
            blueAttack1L[i] = sheetBlueAttack1.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            blueAttack1R[i] = sheetBlueAttack1.crop(sheetBlueAttack1.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
            yellowAttack1L[i] = sheetYellowAttack1.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            yellowAttack1R[i] = sheetYellowAttack1.crop(sheetYellowAttack1.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
            greenAttack1L[i] = sheetGreenAttack1.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            greenAttack1R[i] = sheetGreenAttack1.crop(sheetGreenAttack1.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
        }
        
        for (int i = 0; i < ATTACK2; i++) {
            redAttack2L[i] = sheetRedAttack2.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            redAttack2R[i] = sheetRedAttack2.crop(sheetRedAttack2.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
            blueAttack2L[i] = sheetBlueAttack2.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            blueAttack2R[i] = sheetBlueAttack2.crop(sheetBlueAttack2.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
            yellowAttack2L[i] = sheetYellowAttack2.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            yellowAttack2R[i] = sheetYellowAttack2.crop(sheetYellowAttack2.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
            greenAttack2L[i] = sheetGreenAttack2.crop(WIDTH * (i % 10), HEIGHT * (i / 10), WIDTH, HEIGHT);
            greenAttack2R[i] = sheetGreenAttack2.crop(sheetGreenAttack2.getSheetWidth() - WIDTH - WIDTH * (i % 10), HEIGHT * ((i / 10) + 4), WIDTH, HEIGHT);
        }
        Animations.init();
//END temp
    }
    
    public static class Animations{
        public static Animation redA1LAnim, blueA1LAnim, yellowA1LAnim, greenA1LAnim, redA1RAnim, blueA1RAnim, yellowA1RAnim, greenA1RAnim, redA2LAnim, blueA2LAnim, yellowA2LAnim, greenA2LAnim, redA2RAnim, blueA2RAnim, yellowA2RAnim, greenA2RAnim;
        
        public static void init(){
            redA1LAnim = new Animation(redAttack1L, 35);
            redA1RAnim = new Animation(redAttack1R, 35);
            blueA1LAnim = new Animation(blueAttack1L, 35);
            blueA1RAnim = new Animation(blueAttack1R, 35);
            yellowA1LAnim = new Animation(yellowAttack1L, 35);
            yellowA1RAnim = new Animation(yellowAttack1R, 35);
            greenA1LAnim = new Animation(greenAttack1L, 35);
            greenA1RAnim = new Animation(greenAttack1R, 35);
            redA2LAnim = new Animation(redAttack2L, 35);
            redA2RAnim = new Animation(redAttack2R, 35);
            blueA2LAnim = new Animation(blueAttack2L, 35);
            blueA2RAnim = new Animation(blueAttack2R, 35);
            yellowA2LAnim = new Animation(yellowAttack2L, 35);
            yellowA2RAnim = new Animation(yellowAttack2R, 35);
            greenA2LAnim = new Animation(greenAttack2L, 35);
            greenA2RAnim = new Animation(greenAttack2R, 35);
        }
    }
}
