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

    Boolean isOnMove() {
        return this.xOnMove;
    }

    Boolean switchOnMove() {
        this.xOnMove = !xOnMove;
        return this.xOnMove;
    }

    String getPlayerSymbol() {
        return this.playerSymbol;
    }

    Boolean printPlayer() {
        System.out.print("Hrac :" + this.playerSymbol);
        if (this.isOnMove() == true) {
            System.out.print(" je na tahu.");
        } else {
            System.out.print(" neni na tahu.");
        }
        System.out.print("\n");
        return true;
    }

}

