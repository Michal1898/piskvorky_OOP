public class PlayerTwo extends Player {
    boolean onMove;

    PlayerTwo(String playerMark) {
        super(playerMark);
    }

    @Override
    Boolean isOnMove() {
        this.onMove = !super.isOnMove();
        return this.onMove;
    }
}