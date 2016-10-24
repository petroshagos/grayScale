/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.FX;

import model.PowerUp;
import model.Shape.Rectangle;
import view.ThemeColor;

/**
 *
 * @author Micke
 */
public class PowerUpFX {
    private RectangleFX rectFx;
    
    public PowerUpFX(PowerUp p){
        rectFx = new RectangleFX(p.getThemeColor(),p.getRect());
    }
    public RectangleFX getRectFX(){
        return rectFx;
    }
}
