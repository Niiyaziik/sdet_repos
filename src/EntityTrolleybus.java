import java.awt.*;

public class EntityTrolleybus extends EntityBus{
    private Color additionalColor;
    public Color getAdditionalColor(){return additionalColor;}
    private boolean roga;
    public boolean getRoga() {
        return roga;
    }
    private boolean battery;
    public boolean getBattery() {
        return battery;
    }
    public EntityTrolleybus(int speed, double weight, Color bodyColor, Color
            additionalColor, boolean roga, boolean battery) {
        super(speed, weight, bodyColor);
        this.additionalColor = additionalColor;
        this.roga = roga;
        this.battery = battery;
    }
}