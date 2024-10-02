public class MoveToCenter extends AbstractStrategy{
    @Override
    protected boolean isTargetDestination(){
        var objParams = getObjectParameters();
        if(objParams == null)
            return false;
        return objParams.getObjectMiddleHorizontal() <= getFieldWidth() / 2 &&
                objParams.getObjectMiddleHorizontal() + getStep() >= getFieldWidth() / 2 &&
                objParams.getObjectMiddleVertical() <= getFieldHeight() / 2 &&
                objParams.getObjectMiddleVertical() + getStep() >= getFieldHeight() / 2;
    }

    @Override
    protected  void moveToTarget() {
        ObjectParameters objParams = getObjectParameters();
        if (objParams == null) {
            return;
        }
        var diffX = objParams.getObjectMiddleHorizontal() - getFieldWidth() / 2;
        if (Math.abs(diffX) > getStep()) {
            if (diffX > 0) {
                moveLeft();
            }
            else {
                moveRight();
            }
        }
        var diffY = objParams.getObjectMiddleVertical() - getFieldHeight() / 2;
        if (Math.abs(diffY) > getStep()) {
            if (diffY > 0) {
                moveUp();
            }
            else {
                moveDown();
            }
        }
    }
}
