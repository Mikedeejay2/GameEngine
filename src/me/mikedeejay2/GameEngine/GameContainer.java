package me.mikedeejay2.GameEngine;

import java.awt.event.*;
public class GameContainer implements Runnable
{
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;
    
    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;
    private int width = 320,  height = 240;
    private float scale = 2f;
    private String title = "Game Engine";
    
    public GameContainer(AbstractGame game) 
    {
        this.game = game;
    }
    
    public void start() 
    {
        window = new Window(this); 
        renderer = new Renderer(this);
        input = new Input(this);
        
        thread = new Thread(this);
        thread.run();
    }
    
    public void stop() 
    {
        
    }
    
    public void run() 
    {
        running = true;
        
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
        
        double frameTime = 0;
        int frames = 0;
        int fps = 0;
        
        game.init(this);
        
        while(running) 
        {
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            
            unprocessedTime += passedTime;
            frameTime += passedTime;
            
            while(unprocessedTime >= UPDATE_CAP) 
            {
                unprocessedTime -= UPDATE_CAP;
                render = true;
                game.update(this, (float)UPDATE_CAP);
                
                input.update();
                
                if(frameTime >= 1.0) 
                {
                    frameTime = 0;
                    fps = frames;
                    frames = 0; 
                }
            } 
            
            if(render) 
            {
                render = false;
                renderer.clear();
                game.render(this, renderer); 
                renderer.process();
                renderer.setCamX(0);
                renderer.setCamY(0);
                renderer.drawText("FPS:" + fps, 0, 0, 0xffffffff);
                window.update();
                frames++;
            }
            else 
            {
                try 
                {
                Thread.sleep(1);
                }
                catch(InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        
        dispose();
    }
    
    private void dispose() 
    {
        
    }
    
    public int getWidth() 
    {
        return width;
    }
    
    public void setWidth(int width) 
    {
        this.width = width;
    }
    
    public int getHeight() 
    {
        return height;
    }
    
    public void setHeight(int height) 
    {
        this.height = height;
    }
    
    public float getScale() 
    {
        return scale;
    }
    
    public void setScale(float scale) 
    {
        this.scale = scale;
    }
    
    public String getTitle() 
    {
        return title;
    }
    
    public void setTitle(String title) 
    {
        this.title = title;
    }
    
    public Window getWindow() 
    {
        return window;
    }
    
    public Input getInput() 
    {
        return input;
    }
    
    public Renderer getRenderer() 
    {
        return renderer;
    }
}
