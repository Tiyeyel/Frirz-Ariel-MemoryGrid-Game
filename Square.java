package com.mycompany.memorygrid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Square {
    float x, y, size;
    boolean glowing = false;
    boolean clicked = false;

    public Square(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(SpriteBatch batch, Texture normal, Texture glow) {
        if (glowing) {
            batch.draw(glow, x, y, size, size);
        } else {
            batch.draw(normal, x, y, size, size);
        }
    }

    public boolean isClicked(float touchX, float touchY) {
        return touchX >= x && touchX <= x + size && touchY >= y && touchY <= y + size;
    }
}
