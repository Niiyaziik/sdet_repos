import java.awt.*;

public class DrawingTrolleybus extends DrawingBus{
    public DrawingTrolleybus(int speed, double weight, Color bodyColor, Color additionalColor,
                        boolean roga, boolean battery, int width, int height, int doorsNumber, int doorsType)
    {
        super(speed, weight, bodyColor, width, height, doorsNumber,doorsType);
        if (entityBus != null)
            entityBus = new EntityTrolleybus(speed, weight, bodyColor, additionalColor, roga, battery);
    }
    public void drawTransport(Graphics2D graphics2D) {
        if (!(entityBus instanceof EntityTrolleybus))
            return;
        EntityTrolleybus entityTrolleybus = (EntityTrolleybus) entityBus;
        BasicStroke pen = new BasicStroke(2);
        graphics2D.setStroke(pen);
        Color additionalColor = entityTrolleybus.getAdditionalColor();
        super.drawTransport(graphics2D);
        //рога
        graphics2D.setPaint(Color.BLACK);
        if (entityTrolleybus.getRoga()) {
            graphics2D.setPaint(Color.BLACK);
            graphics2D.drawLine(_startPosX + 186, _startPosY + 31, _startPosX + 86, _startPosY + 1);
            graphics2D.drawLine(_startPosX + 86, _startPosY + 1, _startPosX + 126, _startPosY + 31);
            graphics2D.drawLine(_startPosX + 146, _startPosY + 31, _startPosX + 46, _startPosY + 1);
            graphics2D.drawLine(_startPosX + 46, _startPosY + 1, _startPosX + 86, _startPosY + 31);
        }
        //батарея
        if (entityTrolleybus.getBattery()) {
            graphics2D.setPaint(additionalColor);
            graphics2D.fillRect(_startPosX + 176, _startPosY + 101, 15, 20);
            graphics2D.setPaint(Color.YELLOW);
            graphics2D.drawLine(_startPosX + 183, _startPosY + 103, _startPosX + 178, _startPosY + 111);
            graphics2D.drawLine(_startPosX + 178, _startPosY + 111, _startPosX + 189, _startPosY + 111);
            graphics2D.drawLine(_startPosX + 189, _startPosY + 111, _startPosX + 183, _startPosY + 119);
        }
    }
}