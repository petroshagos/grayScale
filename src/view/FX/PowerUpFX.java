
package view.FX;

import model.PowerUp;

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