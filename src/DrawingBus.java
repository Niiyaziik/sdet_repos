import java.awt.*;

public class DrawingBus {
    protected EntityBus entityBus;
    public EntityBus getEntityBus() {
        return entityBus;
    }
    private int pictureWidth;
    private int pictureHeight;
    protected int _startPosX;
    public int getPosX() {
        return _startPosX;
    }
    protected int _startPosY;
    public int getPosY() {
        return _startPosY;
    }
    private final int busWidth = 200;
    public int getWidth() {
        return busWidth;
    }
    private final int busHeight = 135;
    public int getHeight() {
        return busHeight;
    }
    private IDrawDoors drawingDoors;
    public DrawingBus(int speed, double weight, Color bodyColor, int width, int height, int doorsNumber, int doorsType) {
        if (width < busWidth || height < busHeight)
            return;
        pictureWidth = width;
        pictureHeight = height;
        entityBus = new EntityBus(speed, weight, bodyColor);
        switch (doorsType) {
            case 1:
                drawingDoors = new DrawingDoorsRoundedUp();
                break;
            case 2:
                drawingDoors = new DrawingDoorsRoundedUpAndDown();
                break;
            default:
                drawingDoors = new DrawingDoors();
                break;
        }
        drawingDoors.setNumber(doorsNumber);
    }
    public void setPosition(int x, int y) {
        if (x < 0 || y < 0 || x + busWidth > pictureWidth || y + busHeight > pictureHeight) {
            x = 0;
            y = 0;
        }
        _startPosX = x;
        _startPosY = y;
    }
    public boolean canMove(DirectionType direction) {
        if (entityBus == null) {
            return false;
        }
        switch (direction) {
            case LEFT:
                return _startPosX - entityBus.step.get().intValue() > 0;
            case UP:
                return _startPosY - entityBus.step.get().intValue() > 0;
            case RIGHT:
                return _startPosX + entityBus.step.get().intValue() + busWidth < pictureWidth;
            case DOWN:
                return _startPosY + entityBus.step.get().intValue() + busHeight < pictureHeight;
            default:
                return false;
        }
    }
    public void moveTransport(DirectionType direction) {
        if (!canMove(direction) || entityBus == null)
            return;
        switch (direction) {
            //влево
            case LEFT:
                _startPosX -= entityBus.step.get().intValue();
                break;
            //вверх
            case UP:
                _startPosY -= entityBus.step.get().intValue();
                break;
            // вправо
            case RIGHT:
                _startPosX += entityBus.step.get().intValue();
                break;
            //вниз
            case DOWN:
                _startPosY += entityBus.step.get().intValue();
                break;
        }
    }
    public void drawTransport(Graphics2D graphics2D) {
        if (entityBus == null)
            return;
        BasicStroke pen = new BasicStroke(2);
        graphics2D.setStroke(pen);
        Color bodyColor = entityBus.getBodyColor();
        //колеса
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fillOval(_startPosX + 31, _startPosY + 106, 30, 30);
        graphics2D.fillOval(_startPosX + 151, _startPosY + 106, 30, 30);
        //кузов
        graphics2D.setPaint(bodyColor);
        graphics2D.fillRect(_startPosX + 6, _startPosY + 31, 200, 90);
        //стекла
        graphics2D.setPaint(Color.BLUE);
        graphics2D.fillRect(_startPosX + 186, _startPosY + 40, 20, 40);
        graphics2D.fillOval(_startPosX + 151, _startPosY + 35, 30, 40);
        graphics2D.fillOval(_startPosX + 118, _startPosY + 35, 30, 40);
        graphics2D.fillOval(_startPosX + 85, _startPosY + 35, 30, 40);
        graphics2D.fillOval(_startPosX + 52, _startPosY + 35, 30, 40);
        graphics2D.fillOval(_startPosX + 19, _startPosY + 35, 30, 40);
        //двери
        graphics2D.setPaint(Color.BLACK);
        drawingDoors.drawDoors(graphics2D, _startPosX, _startPosY);
        //границы троллейбуса
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawRect(_startPosX + 6, _startPosY + 31, 200, 90);
        graphics2D.drawOval(_startPosX + 151, _startPosY + 35, 30, 40);
        graphics2D.drawOval(_startPosX + 118, _startPosY + 35, 30, 40);
        graphics2D.drawOval(_startPosX + 85, _startPosY + 35, 30, 40);
        graphics2D.drawOval(_startPosX + 52, _startPosY + 35, 30, 40);
        graphics2D.drawOval(_startPosX + 19, _startPosY + 35, 30, 40);
        graphics2D.drawRect(_startPosX + 186, _startPosY + 40, 20, 40);
        //задние фары
        graphics2D.setPaint(Color.RED);
        graphics2D.fillRect(_startPosX + 6, _startPosY + 91, 10, 20);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawRect(_startPosX + 6, _startPosY + 91, 10, 20);
        //передние фары
        graphics2D.setPaint(Color.YELLOW);
        graphics2D.fillRect(_startPosX + 196, _startPosY + 91, 10, 20);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawRect(_startPosX + 196, _startPosY + 91, 10, 20);
    }
}
