import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Color color = new Color();

    public static void main(String[] args) {
        Boolean newGame;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println(color.ANSI_BLUE + "Piskvorky" + color.ANSI_RESET);
        System.out.println(color.ANSI_BLUE + "vytvoril Michal Volf" + color.ANSI_RESET);

        do {
            /* init game */
            GameBoard myBoard = new GameBoard();
            Boolean MyBoardUpdated = false;
            Boolean gameOver;
            Boolean boardChanged;

            Color color = new Color();

            PlayerOne playerOne = new PlayerOne("X");
            PlayerTwo playerTwo = new PlayerTwo("O");
            System.out.println("Hra zacina:");
            myBoard.printBoard();
            playerOne.printPlayer();
            playerTwo.printPlayer();

            do {

                do {
                    if (playerOne.onMove == true) {
                        boardChanged = myBoard.modifySquare(playerOne.getPlayerSymbol().charAt(0));
                    } else {
                        boardChanged = myBoard.modifySquare(playerTwo.getPlayerSymbol().charAt(0));
                    }
                } while (boardChanged == false);

                playerOne.switchOnMove();
                myBoard.printBoard();
                gameOver = myBoard.evaluateBoard(playerOne.getPlayerSymbol().charAt(0), playerTwo.getPlayerSymbol().charAt(0));

                playerOne.printPlayer();
                playerTwo.printPlayer();

            } while (gameOver == false);
            System.out.println("Zaverecna pozice:");
            System.out.println("*****************");
            myBoard.printBoard();
            playerOne.printPlayer();
            playerTwo.printPlayer();

            if (myBoard.gameIsDraw() == true) {
                System.out.println("Hra konci nerozhodne.");
            } else {
                System.out.println("Vitezem je: " + myBoard.winnerIs());
            }


            newGame = anotherGame();
        } while (newGame);
        System.out.println(color.ANSI_RED + "Konec programu" + color.ANSI_RESET);
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