public class MoveToBorder extends AbstractStrategy{
    @Override
    protected boolean isTargetDestination() {
        var objParams = getObjectParameters();
        if (objParams == null) {
            return false;
        }
        return objParams.getRightBorder() + getStep() >= getFieldWidth() &&
                objParams.getDownBorder() + getStep() >= getFieldHeight();
    }

    @Override
    protected void moveToTarget() {
        var objParams = getObjectParameters();
        if (objParams == null) {
            return;
        }
        var diffX = objParams.getRightBorder() - getFieldWidth();
        if (Math.abs(diffX) >= getStep()) {
            if (diffX < 0) {
                moveRight();
            }
        }
        var diffY = objParams.getDownBorder() - getFieldHeight();
        if (Math.abs(diffY) >= getStep()) {
            if (diffY < 0) {
                moveDown();
            }
        }
    }
}
