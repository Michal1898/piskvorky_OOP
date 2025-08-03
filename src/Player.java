import java.util.*;

public class Player {

    String playerSymbol;
    private static Boolean xOnMove;
    private ArrayList<String> squares = new ArrayList<>();
    static Color color = new Color();

    Player(String playerMark) {
        try {
            if (playerMark.length() != 1) {
                System.out.println("Symbol musi mit delku 1 znak");
                throw new Exception(color.ANSI_RED + "Zadej symbol prave 1 znak dlouhy" + color.ANSI_RESET);
            } else {
                this.playerSymbol = playerMark;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        Random trueOrFalse = new Random();
        this.xOnMove = trueOrFalse.nextBoolean();
    }

    Boolean xOnMove() {
        return this.xOnMove;
    }

    String playerSymbol() {return this.playerSymbol; }

    Boolean switchOnMove() {
        this.xOnMove = !xOnMove;
        return this.xOnMove;
    }

    String getPlayerSymbol() {
        return this.playerSymbol;
    }

}

