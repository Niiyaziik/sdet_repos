public class DrawingObjectBus implements IMoveableObject{
    private final DrawingBus drawingBus;
    public DrawingObjectBus(DrawingBus drawingBus){
        this.drawingBus = drawingBus;
    }

    @Override
    public ObjectParameters getObjectPosition(){
        if(drawingBus == null || drawingBus.getEntityBus() == null)
            return null;
        return new ObjectParameters(drawingBus.getPosX(), drawingBus.getPosY(),
                drawingBus.getWidth(), drawingBus.getHeight());
    }
    public int getStep(){
        if(drawingBus.getEntityBus() == null)
            return 0;
        return drawingBus.getEntityBus().step.get().intValue();
    }
    public boolean checkCanMove(DirectionType direction){
        if(drawingBus == null)
            return false;
        return drawingBus.canMove(direction);
    }
    public void moveObject(DirectionType direction){
        if(drawingBus == null)
            return;
        drawingBus.moveTransport(direction);
    }
}
