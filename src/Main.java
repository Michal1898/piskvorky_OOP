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
            Boolean MyBoardUpdated = false;
            do {
                PlayerOne playerOne = new PlayerOne("X");
                PlayerTwo playerTwo = new PlayerTwo("O");


                myBoard.printBoard();
                playerOne.printPlayer();
                playerTwo.printPlayer();

                if (playerOne.onMove==true){
                    myBoard.modifySquare(playerOne.getPlayerSymbol().charAt(0));
                } else{
                    myBoard.modifySquare(playerTwo.getPlayerSymbol().charAt(0));
                }
                myBoard.printBoard();
                playerOne.printPlayer();
                playerTwo.printPlayer();
                playerOne.switchOnMove();
                playerOne.printPlayer();
                playerTwo.printPlayer();

                if (playerOne.onMove==true){
                    myBoard.modifySquare(playerOne.getPlayerSymbol().charAt(0));
                } else{
                    myBoard.modifySquare(playerTwo.getPlayerSymbol().charAt(0));
                }
                myBoard.printBoard();
                playerOne.printPlayer();
                playerTwo.printPlayer();
                playerOne.switchOnMove();
                playerOne.printPlayer();
                playerTwo.printPlayer();

                if (playerOne.onMove==true){
                    myBoard.modifySquare(playerOne.getPlayerSymbol().charAt(0));
                } else{
                    myBoard.modifySquare(playerTwo.getPlayerSymbol().charAt(0));
                }
                myBoard.printBoard();
                playerOne.printPlayer();
                playerTwo.printPlayer();
                playerOne.switchOnMove();
                playerOne.printPlayer();
                playerTwo.printPlayer();

                if (playerOne.onMove==true){
                    myBoard.modifySquare(playerOne.getPlayerSymbol().charAt(0));
                } else{
                    myBoard.modifySquare(playerTwo.getPlayerSymbol().charAt(0));
                }
                myBoard.printBoard();
                playerOne.printPlayer();
                playerTwo.printPlayer();
                playerOne.switchOnMove();
                playerOne.printPlayer();
                playerTwo.printPlayer();
            } while (false);

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




    static class Player {
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

    static class PlayerOne extends Player {
        boolean onMove;
        PlayerOne (String playerMark) {
            super(playerMark);
        }
        @Override
        Boolean isOnMove () {
            this.onMove = super.isOnMove();
            return this.onMove;
        }
    }
    static class PlayerTwo extends Player {
        boolean onMove;

        PlayerTwo (String playerMark) {
            super(playerMark);
        }
        @Override
        Boolean isOnMove () {
            this.onMove = !super.isOnMove();
            return this.onMove;
        }
    }
    static class GameBoard {
        private final Integer LINES_COUNT = 3;
        private final Integer COLUMN_COUNT = 3;
        protected Character[][] GameZone = new Character[LINES_COUNT][COLUMN_COUNT];
        private String[] lineDescription = {"3", "2", "1"};
        private String[] columnDescription = {"A", "B", "C"};

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
                Character linesDescription = decoderCoordinates(0, x).charAt(1);
                System.out.print(ANSI_GREEN + linesDescription + ": " + ANSI_RESET);
                for (int y = 0; y < COLUMN_COUNT; y++) {
                    if ((x + y) % 2 == 0) {
                        System.out.print(PURPLE_BACKGROUND + " " + GameZone[x][y] + " ");
                    } else {
                        System.out.print(WHITE_BACKGROUND + " " + GameZone[x][y] + " ");
                    }
                    System.out.print(ANSI_RESET);
                }
                System.out.print("\n");
            }
            System.out.print("   ");
            for (int y = 0; y < COLUMN_COUNT; y++) {
                Character columnDesription = decoderCoordinates(y, 0).charAt(0);
                System.out.print(ANSI_GREEN + " " + columnDesription + " ");
            }
            System.out.print(ANSI_RESET + "\n");
        }

        String decoderCoordinates(int x, int y) {
            // because computer array system is for human somewhat unnatural,
            // I decode it to for human more natural
            // chessboad system and return it.

            String chessboardCoords = this.columnDescription[x] + this.lineDescription[y];
            return (chessboardCoords);
        }

        Integer[] coderCoordinates(String chessBoardCoord) {
            String xChessboard = String.valueOf(chessBoardCoord.charAt(0));
            String yChessboard = String.valueOf(chessBoardCoord.charAt(1));

            Integer xComputer = 0;
            Integer yComputer = 0;
            for (int x = 0; x < this.COLUMN_COUNT; x++) {
                if (this.columnDescription[x].equals(xChessboard)) {
                    xComputer = x;
                }
            }
            for (int y = 0; y < this.LINES_COUNT; y++) {
                if (lineDescription[y].equals(yChessboard)) {
                    yComputer = y;
                }

            }

            Integer[] ComputerCoord = {yComputer, xComputer};
            return ComputerCoord;
        }


        Boolean modifySquare(Character playerSymbol) {
            String targetColumn;
            String targetLine;

            Scanner inputCoord = new Scanner(System.in);
            String targetSquare;

            try {
                System.out.println("Zadej souradnice: ");
                targetSquare = inputCoord.nextLine().toUpperCase();
                if (targetSquare.length() != 2) {
                    System.out.println("Invalid Input!");
                    throw new Exception(ANSI_RED + "Zadej prave 2 znaky!" + ANSI_RESET);
                } else {
                    Boolean xFound = false;
                    Boolean yFound = false;
                    targetColumn = targetSquare.substring(0, 1);
                    targetLine = targetSquare.substring(1, 2);
                    System.out.println(targetColumn + ":" + targetLine);
                    for (int x = 0; x < this.COLUMN_COUNT; x++) {
                        if (columnDescription[x].equals(targetColumn)) {
                            xFound = true;
                        }
                    }
                    for (int y = 0; y < this.LINES_COUNT; y++) {
                        if (lineDescription[y].equals(targetLine)) {
                            yFound = true;
                        }

                    }
                    if (xFound && yFound) {
                        // target square successfully found!
                        Integer[] ComputerCoords = this.coderCoordinates(targetSquare);
                        //Check, if target square is empty:
                        String squareValue = Character.toString(this.GameZone[ComputerCoords[0]][ComputerCoords[1]]);

                        if (squareValue.equals("E")) {
                            this.GameZone[ComputerCoords[0]][ComputerCoords[1]] = playerSymbol;
                            return true;
                        } else {
                            System.out.println("Cilove pole je uz obsazeno!");
                            throw new Exception(ANSI_RED + "Vyber si prazdne pole!" + ANSI_RESET);
                        }
                    } else {
                        System.out.println("Mimo rozsah!");
                        throw new Exception(ANSI_RED + "Zadej hodnotu v rozsahu!" + ANSI_RESET);
                    }
                }


            } catch (Exception e) {
                System.out.println(ANSI_RED + "Invalid input!" + e.getMessage() + ANSI_RESET);

            }
            return false;
        }
    }
}