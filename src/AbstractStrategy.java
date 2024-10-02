public abstract class AbstractStrategy {
    private IMoveableObject moveableObject;
    private Status state = Status.NOT_INIT;
    private int fieldWidth;
    protected int getFieldWidth(){return fieldWidth;}
    private int fieldHeight;
    protected int getFieldHeight(){return fieldHeight;}
    public Status getStatus() {return state;}

    public void setData(IMoveableObject moveableObject, int width, int height){
        if (moveableObject == null)
        {
            state = Status.NOT_INIT;
            return;
        }
        state = Status.IN_PROGRESS;
        this.moveableObject = moveableObject;
        fieldWidth = width;
        fieldHeight = height;
    }
    public void makeStep(){
        if (state != Status.IN_PROGRESS) {
            return;
        }
        if (isTargetDestination()) {
            state = Status.FINISH;
            return;
        }
        moveToTarget();
    }
    protected boolean moveLeft() {return moveTo(DirectionType.LEFT);}
    protected boolean moveRight() {return moveTo(DirectionType.RIGHT);}
    protected boolean moveUp() {return moveTo(DirectionType.UP);}
    protected boolean moveDown() {return moveTo(DirectionType.DOWN);}
    protected ObjectParameters getObjectParameters(){
        if(moveableObject != null)
            return moveableObject.getObjectPosition();
        else return null;
    }
    protected Integer getStep() {
        if (state != Status.IN_PROGRESS)
        {
            return null;
        }
        return moveableObject.getStep();
    }
    protected abstract void moveToTarget();
    protected abstract boolean isTargetDestination();
    private boolean moveTo(DirectionType directionType) {
        if (state != Status.IN_PROGRESS)
        {
            return false;
        }
        if (moveableObject.checkCanMove(directionType))
        {
            moveableObject.moveObject(directionType);
            return true;
        }
        return false;
    }
}
