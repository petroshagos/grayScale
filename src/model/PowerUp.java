/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.util.Random;
import model.Shape.Rectangle;
import view.ThemeColor;
import static view.ThemeColor.POWERUP;

/**
 *
 * @author Micke
 */
public class PowerUp {

    private double x;
    private double y;
    private double width = 10;
    private double height = 10;
    int color;
    boolean filled;
    boolean outOfBounds;
    boolean collidable;
    private int whichPowerUp;
    private ThemeColor themeColor;
    private Rectangle rect;

    public PowerUp(double x, double y, int whichPowerUp) {
        this.x = x;
        this.y = y;
        color = 0;
        Random rand = new Random();
        this.whichPowerUp = whichPowerUp;
        themeColor = POWERUP;
        System.out.println("hej");
        rect = new Rectangle(x, y, width, height, color, filled, outOfBounds, collidable);
        rect.setVelocity(0, 20);
    }
    public void doubleDamage(PlayerShip playerShip){
        playerShip.multiplyDamage(2);
    }
    public void multiShot(){
        
    }
    public ThemeColor getThemeColor() {
        return themeColor;
    }
    public Rectangle getRect(){
        return rect;
    }
}
