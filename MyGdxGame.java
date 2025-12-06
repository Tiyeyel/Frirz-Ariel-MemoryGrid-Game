package com.mycompany.memorygrid;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture normalTexture;
    Texture glowTexture;
    Square[][] grid;
    int GRID_SIZE = 3;
    float squareSize;

    @Override
    public void create() {
        batch = new SpriteBatch();
        normalTexture = new Texture("normal.png");
        glowTexture = new Texture("glow.png");

        grid = new Square[GRID_SIZE][GRID_SIZE];
        squareSize = 100;
        float startX = 200;
        float startY = 200;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = new Square(startX + j * (squareSize + 10), startY + i * (squareSize + 10), squareSize);
            }
        }

        // Glow 3 random squares
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);
            grid[row][col].glowing = true;
        }

        // Turn off glowing after 2 seconds
        Gdx.app.postRunnable(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    grid[i][j].glowing = false;
                }
            }
        });
    }

    @Override
    public void render() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    if (grid[i][j].isClicked(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        grid[i][j].clicked = true;
                        if (grid[i][j].glowing) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    }
                }
            }
        }

        batch.begin();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j].draw(batch, normalTexture, glowTexture);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        normalTexture.dispose();
        glowTexture.dispose();
    }
}

