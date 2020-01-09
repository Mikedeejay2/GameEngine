package me.mikedeejay2.GameEngine.game;

import me.mikedeejay2.GameEngine.*;
import me.mikedeejay2.GameEngine.gfx.*;
import me.mikedeejay2.GameEngine.audio.SoundClip;
import java.awt.event.*;

public class GameManagerDeprecated extends AbstractGame 
{
    private Image forest;
    private Image image;
    private Image imageBlendTest;
    private ImageTile imageTile;
    private Image lighting;
    private SoundClip clip;
    private Light light;
    
    public GameManagerDeprecated() 
    {
        forest = new Image("/res/textures/forest.png");
        
        image = new Image("/res/textures/test1.png");
        imageTile = new ImageTile("/res/textures/test2.png", 16, 16);
        imageBlendTest = new Image("/res/textures/alphablendtest.png");
        imageBlendTest.setAlpha(true);
        image.setLightBlock(Light.FULL);
        image.setAlpha(false);
        light = new Light(250, 0xffffffff);
        
        
        clip = new SoundClip("/res/audio/example.wav");
    }
    
    @Override
    public void init(GameContainer gc)
    {
        
    }
    @Override
    public void update(GameContainer gc, float dt) 
    {
        if(gc.getInput().isKeyDown(KeyEvent.VK_A)) 
        {
            System.out.println("A was pressed.");
            clip.play();
            clip.setVolume(0);
        }
        
        temp += dt * 10;
        
        if(temp > 4) {
            temp = 0;
        }
    }
    
    float temp = 0;
    
    @Override
    public void render(GameContainer gc, Renderer r) 
    {
        r.drawImage(forest, 0, 0);
        
        r.setZDepth(1);
        r.drawImage(imageBlendTest, 200, 150);
        r.drawImage(image, 320/3, 180/3);
        r.drawFillRect(90, 90, 32, 32, 0xffffff00);
        r.drawImageTile(imageTile, 100, 50, (int)temp, 0);
        r.drawRect(20, 20, 32, 32, 0xffffccff);
        
        r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());
    }
   
    
    public static void main(String args[]) 
    {
        GameContainer gc = new GameContainer(new GameManagerDeprecated());
        gc.setWidth(320);
        gc.setHeight(180);
        gc.setScale(3f);
        gc.start();
    }
}

