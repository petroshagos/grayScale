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
        color = whichPowerUp;
        this.whichPowerUp = whichPowerUp;
        themeColor = POWERUP;
        System.out.println("hej");
        rect = new Rectangle(x, y, width, height, color, filled, outOfBounds, collidable);
        rect.setVelocity(0, 30);
        System.out.println("PowerUp created");
    }
    //När kollision ska nedanstående hända
    public void getPowerUp(Ship playerShip,Player player){
        switch(whichPowerUp){
            case 0: System.out.println("dd"); doubleDamage(playerShip); break;
            case 1: System.out.println("ms"); multiShot(playerShip); break;
            case 2: System.out.println("bs"); bonusScore(player); break;
        }
    }
    /**
     * Doubles the current player ships damage
     * @param playerShip current player ship
     */
    private void doubleDamage(Ship playerShip){ //whichPowerUp 0
        playerShip.multiplyDamage(2);
    }
    /**
     * Updates the pattern of how projectiles are created for this ship
     * @param playerShip 
     */
    private void multiShot(Ship playerShip){ //whichPowerUp 1
        playerShip.setMultiShot(true);
    }
    /**
     * Adds 5000 score
     * @param player player
     */
    private void bonusScore(Player player){ //whichPowerUp 2
        player.addScore(5000);
    }
    public ThemeColor getThemeColor() {
        return themeColor;
    }
    public Rectangle getRect(){
        return rect;
    }
}
