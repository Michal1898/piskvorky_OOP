import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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

    private static String string;


    public static void main(String[] args) {
        Boolean newGame;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println(ANSI_BLUE + "Piskvorky" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "vytvoril Michal Volf" + ANSI_RESET);

        do {
            /* init game */
            GameBoard myBoard = new GameBoard();

            myBoard.printBoard();

            // Do you wish new game (ano / ne)
            newGame = anotherGame();
        } while (newGame);
        System.out.println(ANSI_RED + "Konec programu" + ANSI_RESET);
    }

    public static Boolean anotherGame() {
        Scanner again = new Scanner(System.in);
        String yesNo;
        while (true) {
            try {
                System.out.println("Nova hra?");
                yesNo = again.nextLine().toLowerCase();
                if ((yesNo.equals("ano")) || (yesNo.equals("ne"))) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "Odpovez ano, nebo ne!" + ANSI_RESET);
                    again.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                System.out.println("Odpovez ano, nebo ne!");
            }

        }
        if (yesNo.equals("ano")) {
            return true;
        } else {
            return false;
        }
    }


    static class GameBoard {
        private final Integer LINES_COUNT = 3;
        private final Integer COLUMN_COUNT = 3;
        protected Character[][] GameZone = new Character[LINES_COUNT][COLUMN_COUNT];

        GameBoard() {
            // Fill Game Board with symbol E - like Empty square
            for (int x = 0; x < LINES_COUNT; x++) {
                for (int y = 0; y < COLUMN_COUNT; y++) {
                    GameZone[x][y] = 'E';
                }
            }
        }

        void printBoard() {
            for (int x = 0; x < LINES_COUNT; x++) {
                System.out.print(x + ": ");
                for (int y = 0; y < COLUMN_COUNT; y++) {
                    if ((x + y) % 2 == 0) {
                        System.out.print(PURPLE_BACKGROUND + " "+  GameZone[x][y]+ " ");
                    } else {
                        System.out.print(WHITE_BACKGROUND + " "+GameZone[x][y]+  " ");
                    }
                    System.out.print(ANSI_RESET);
                }
                System.out.print("\n");
            }
            System.out.print("   ");
            for (int y = 0; y < COLUMN_COUNT; y++) {
                System.out.print(" "+y + " ");
            }
            System.out.print("\n");
        }
    }
}