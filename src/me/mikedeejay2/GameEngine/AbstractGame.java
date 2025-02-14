package me.mikedeejay2.GameEngine;


public abstract class AbstractGame 
{
    
    public abstract void init(GameContainer gc);
    public abstract void update(GameContainer gc, float dt);
    public abstract void render(GameContainer gc, Renderer r);
    
}
