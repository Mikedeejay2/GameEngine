package com.mikedeejay2.engine.game;

import com.mikedeejay2.engine.AbstractGame;
import com.mikedeejay2.engine.GameContainer;
import com.mikedeejay2.engine.Renderer;
import com.mikedeejay2.engine.gfx.Image;
import com.mikedeejay2.engine.gfx.ImageTile;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {
    private Image image1;
    private ImageTile image2;

    public GameManager() {
        image1 = new Image("/test1.png");
        image2 = new ImageTile("/test2.png", 16, 16);
    }

    @Override
    public void update(GameContainer gc, float dt) {
        if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
            System.out.println("A was pressed");
        }

        temp += dt * 10;

        if(temp > 4) {
            temp = 0;
        }
    }

    float temp = 0;

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(image1, gc.getInput().getMouseX() - 32, gc.getInput().getMouseY() - 32);
        r.drawImageTile(image2, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 8, (int)temp, 0);
    }

    public static void main(String args[]) {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}
