package me.mikedeejay2.GameEngine.game;

import me.mikedeejay2.GameEngine.*;
import me.mikedeejay2.GameEngine.gfx.*;
import me.mikedeejay2.GameEngine.audio.SoundClip;
import java.awt.event.*;
import java.util.*;

public class GameManager extends AbstractGame 
{
    public static final int TS = 16;
    
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private Camera camera;
    
    private boolean[] collision;
    private int levelH, levelW;
    
    public GameManager() 
    {
        objects.add(new Player(6, 4));
        loadLevel("/res/textures/level.png");
        camera = new Camera("player");
    }
    
    @Override
    public void init(GameContainer gc) 
    {
        gc.getRenderer().setAmbientColor(-1); 
    }
    
    @Override
    public void update(GameContainer gc, float dt) 
    {
        for(int i = 0; i < objects.size(); i++) 
        {
            objects.get(i).update(gc, this, dt);
            if(objects.get(i).isDead()) 
            {
                objects.remove(i);
                i--;
            }
        }
        camera.update(gc, this, dt);
    }
    
    @Override
    public void render(GameContainer gc, Renderer r) 
    {
        camera.render(r);
        for(int y = 0; y < levelH; y++)
        {
            for(int x = 0; x < levelW; x++)
            {
                if(collision[x + y * levelW])
                {
                    r.drawFillRect(x * TS, y * TS, TS, TS, 0xff0f0f0f);
                }
                else
                {
                    r.drawFillRect(x * TS, y * TS, TS, TS, 0xff9f9f9f);
                }
            }
        }
        
        for(GameObject obj : objects) 
        {
            obj.render(gc, r);
        }
    }
   
    public void loadLevel(String path)
    {
        Image levelImage = new Image(path);
        
        levelW = levelImage.getW();
        levelH = levelImage.getH();
        collision = new boolean[levelW * levelH];
        
        for(int y = 0; y < levelImage.getH(); y++)
        {
            for(int x = 0; x < levelImage.getW(); x++)
            {
                if(levelImage.getP()[x + y * levelImage.getW()] == 0xff000000)
                {
                    collision[x + y * levelImage.getW()] = true;
                }
                else
                {
                    collision[x + y * levelImage.getW()] = false;
                }
            }
        }
    }
    
    public GameObject getObject(String tag)
    {
        for(int i = 0; i < objects.size(); i++)
        {
            if(objects.get(i).getTag().equals(tag))
            {
                return objects.get(i);
            }
        }
        return null;
    }
    
    public void addObject(GameObject object)
    {
        objects.add(object);
    }
    
    public boolean getCollision(int x, int y)
    {
        if(x < 0 || x >= levelW || y < 0 || y >= levelH)
            return true;
        return collision[x + y * levelW];
    }
    
    public static void main(String args[]) 
    {
        GameContainer gc = new GameContainer(new GameManager());
        gc.setWidth(320);
        gc.setHeight(240);
        gc.setScale(3f);
        gc.start();
    }
    
}

