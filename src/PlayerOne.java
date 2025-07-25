public class PlayerOne extends Player {
    boolean onMove;

    PlayerOne(String playerMark) {
        super(playerMark);
    }

    @Override
    Boolean isOnMove() {
        this.onMove = super.isOnMove();
        return this.onMove;
    }
}
