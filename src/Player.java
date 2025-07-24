import java.util.*;
public class Player {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";
    String playerSymbol;
    private static Boolean xOnMove;
    private ArrayList<String> squares = new ArrayList<>();

    Player (String playerMark) {
        try {
            if (playerMark.length() !=1) {
                System.out.println("Symbol musi mit delku 1 znak");
                throw new Exception(ANSI_RED + "Zadej symbol prave 1 znak dlouhy" + ANSI_RESET);
            }
            else {
                this.playerSymbol = playerMark;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Random trueOrFalse = new Random();
        xOnMove = trueOrFalse.nextBoolean();
    }

    Boolean isOnMove(){
        return xOnMove;
    }

    Boolean switchOnMove(){
        xOnMove = !xOnMove;
        return xOnMove;
    }

    String getPlayerSymbol() {
        return this.playerSymbol;
    }

    Boolean printPlayer() {
        System.out.print("Hrac :" + this.playerSymbol);
        if(this.isOnMove()==true){
            System.out.print(" je na tahu.");
        }
        else  {
            System.out.print(" neni na tahu.");
        }
        System.out.print("\n");
        return true;
    }

}

