public interface IMoveableObject {
    ObjectParameters getObjectPosition();
    int getStep();
    boolean checkCanMove(DirectionType direction);
    void moveObject(DirectionType direction);
}
