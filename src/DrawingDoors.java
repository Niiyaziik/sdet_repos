import java.awt.*;

public class DrawingDoors implements IDrawDoors{
    private DoorsNumber number;
    @Override
    public void setNumber(int x){
        if(x <= 3)
            number = DoorsNumber.THREE;
        if(x == 4)
            number = DoorsNumber.FOUR;
        if(x >= 5)
            number = DoorsNumber.FIVE;
    }
    @Override
    public void drawDoors(Graphics2D graphics2D, int _startX, int _startY){
        graphics2D.fillRect(_startX+52, _startY+81, 25, 40);
        graphics2D.fillRect(_startX+85, _startY+81, 25, 40);
        graphics2D.fillRect(_startX+118, _startY+81, 25, 40);
        if (number == DoorsNumber.FOUR || number == DoorsNumber.FIVE){
            graphics2D.fillRect(_startX+151, _startY+81, 25, 40);
        }
        if (number == DoorsNumber.FIVE){
            graphics2D.fillRect(_startX+19, _startY+81, 25, 40);
        }
    }
}