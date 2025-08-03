import java.util.*;

public class Main {
    static Color color = new Color();

    public static void main(String[] args) {
        Boolean newGame;

        System.out.println(color.ANSI_BLUE + "Piskvorky" + color.ANSI_RESET);
        System.out.println(color.ANSI_BLUE + "vytvoril Michal Volf" + color.ANSI_RESET);

        do {
            /* init game */
            GameBoard myBoard = new GameBoard();
            Boolean MyBoardUpdated = false;
            Boolean gameOver;
            Boolean boardChanged;

            Color color = new Color();

            Player playerOne = new Player("X");
            Player playerTwo = new Player("O");
            System.out.println("Hra zacina:");
            myBoard.printBoard();
            printPlayer(playerOne.playerSymbol(), playerOne.xOnMove());
            printPlayer(playerTwo.playerSymbol(), !playerTwo.xOnMove());

            do {
                do {
                    if (playerOne.xOnMove()) {
                        boardChanged = myBoard.modifySquare(playerOne.getPlayerSymbol().charAt(0));
                    } else {
                        boardChanged = myBoard.modifySquare(playerTwo.getPlayerSymbol().charAt(0));
                    }
                } while (!boardChanged);

                playerOne.switchOnMove();
                myBoard.printBoard();
                gameOver = myBoard.evaluateBoard(playerOne.getPlayerSymbol().charAt(0), playerTwo.getPlayerSymbol().charAt(0));

                printPlayer(playerOne.playerSymbol(), playerOne.xOnMove());
                printPlayer(playerTwo.playerSymbol(), !playerTwo.xOnMove());

            } while (!gameOver);

            System.out.println("Zaverecna pozice:");
            System.out.println("*****************");
            myBoard.printBoard();
            printPlayer(playerOne.playerSymbol(), playerOne.xOnMove());
            printPlayer(playerTwo.playerSymbol(), !playerTwo.xOnMove());

            if (myBoard.gameIsDraw()) {
                System.out.println("Hra konci nerozhodne.");
            } else {
                System.out.println("Vitezem je: " + myBoard.winnerIs());
            }

            newGame = anotherGame();
        } while (newGame);
        System.out.println(color.ANSI_RED + "Konec programu" + color.ANSI_RESET);
    }

    public static void printPlayer(String PlayerSymbol, Boolean isOnMove) {
        System.out.print("Hrac " + PlayerSymbol);
        if (isOnMove) {
            System.out.print(" je na tahu.\n");
        } else {
            System.out.print(": na tahu je souper.\n");
        }
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
                    System.out.println(color.ANSI_RED + "Odpovez ano, nebo ne!" + color.ANSI_RESET);
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

}