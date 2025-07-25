import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
    Color color = new Color();

    private final Integer LINES_COUNT = 3;
    private final Integer COLUMN_COUNT = 3;
    private final Integer WINNER_SERIES = 3;
    protected Character[][] GameZone = new Character[LINES_COUNT][COLUMN_COUNT];
    private String[] lineDescription = {"3", "2", "1"};
    private String[] columnDescription = {"A", "B", "C"};
    private Boolean gameIsOver;
    private Boolean gameIsDraw;
    private Character winnersSymbol = ' ';

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
            System.out.print(color.ANSI_GREEN + linesDescription + ": " + color.ANSI_RESET);
            for (int y = 0; y < COLUMN_COUNT; y++) {
                String squareValue = Character.toString(this.GameZone[x][y]);
                if (squareValue.equals("E")) {
                    // Replace E symbols with empty space.
                    squareValue = " ";
                }
                if ((x + y) % 2 == 0) {
                    System.out.print(color.PURPLE_BACKGROUND + color.ANSI_BLACK + " " + squareValue + " ");
                } else {
                    System.out.print(color.WHITE_BACKGROUND + color.ANSI_BLACK + " " + squareValue + " ");
                }
                System.out.print(color.ANSI_RESET);
            }
            System.out.print("\n");
        }
        System.out.print("   ");
        for (int y = 0; y < COLUMN_COUNT; y++) {
            Character columnDesription = decoderCoordinates(y, 0).charAt(0);
            System.out.print(color.ANSI_GREEN + " " + columnDesription + " ");
        }
        System.out.print(color.ANSI_RESET + "\n");
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
                throw new Exception(color.ANSI_RED + "Zadej prave 2 znaky!" + color.ANSI_RESET);
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
                        throw new Exception(color.ANSI_RED + "Vyber si prazdne pole!" + color.ANSI_RESET);
                    }
                } else {
                    System.out.println("Mimo rozsah!");
                    throw new Exception(color.ANSI_RED + "Zadej hodnotu v rozsahu!" + color.ANSI_RESET);
                }
            }


        } catch (Exception e) {
            System.out.println(color.ANSI_RED + "Invalid input!" + e.getMessage() + color.ANSI_RESET);

        }
        return false;
    }

    public Boolean gameIsOver() {
        return this.gameIsOver;
    }

    public Boolean gameIsDraw() {
        return this.gameIsDraw;
    }

    public Character winnerIs() {
        return (this.winnersSymbol);
    }

    public Boolean evaluateBoard(Character playerOneSymbol, Character playerTwoSymbol) {
        int emptySquareTotal = 0;

        //all possible String for evalution are
        List<String> accumulatorOfSeries = new ArrayList<>();
        //add columns to accumulator:
        for (int y = 0; y < COLUMN_COUNT; y++) {
            // check column
            String tempString = "";
            for (int x = 0; x < LINES_COUNT; x++) {
                String squareValue = Character.toString(this.GameZone[x][y]);
                tempString += squareValue;
                if (squareValue.equals("E")) {
                    emptySquareTotal += 1;
                    //counter of empty squares on the board incremented.
                }
                if (x == LINES_COUNT - 1) {
                    accumulatorOfSeries.add(tempString);
                    // add complete string for evalution into accumulator
                }
            }
        }
        // add lines to accumulator:
        for (int x = 0; x < COLUMN_COUNT; x++) {
            // check lines
            String tempString = "";
            for (int y = 0; y < LINES_COUNT; y++) {
                String squareValue = Character.toString(this.GameZone[x][y]);
                tempString += squareValue;

                if (y == COLUMN_COUNT - 1) {
                    accumulatorOfSeries.add(tempString);
                    // add complete string for evalution into accumulator
                }
            }
        }
        //add both diagonals to accumulator:
        String diagonal1 = "";
        String diagonal2 = "";
        for (int y = 0; y < LINES_COUNT; y++) {
            String squareValue = Character.toString(this.GameZone[y][y]);
            diagonal1 += squareValue;

            squareValue = Character.toString(this.GameZone[LINES_COUNT - 1 - y][y]);
            diagonal2 += squareValue;
        }
        accumulatorOfSeries.add(diagonal1);
        accumulatorOfSeries.add(diagonal2);

        // all possible series are in accumulor
        // evalution of series in accumulator
        this.gameIsOver = true;
        this.gameIsDraw = true;
        for (String oneSerie : accumulatorOfSeries) {
            int emptySquare = 0;
            int playerOneSquare = 0;
            int playerTwoSquare = 0;
            for (int a = 0; a < WINNER_SERIES; ++a) {
                if (oneSerie.charAt(a) == playerOneSymbol) {
                    playerOneSquare += 1;
                } else if (oneSerie.charAt(a) == playerTwoSymbol) {
                    playerTwoSquare += 1;
                } else {
                    emptySquare += 1;
                    emptySquareTotal += 1;
                }
            }

            switch (emptySquare) {
                case 0:
                    // the serie is full

                    // one of player have completed 3 symbol in row
                    //end the game and decide the winner"
                    if (playerOneSquare == WINNER_SERIES) {
                        this.gameIsOver = true;
                        this.gameIsDraw = false;
                        this.winnersSymbol = playerOneSymbol;
                        return this.gameIsOver;
                    } else if (playerTwoSquare == WINNER_SERIES) {
                        this.gameIsOver = true;
                        this.gameIsDraw = false;
                        this.winnersSymbol = playerTwoSymbol;
                        return this.gameIsOver;
                    } else {
                        // but in the serie are different values.
                        // ->keep on next serie
                        ;
                    }
                    break;
                case 1:
                    // one empty space in the serie
                    if (playerOneSquare == 1) {
                        ;
                        //each of player have one symbol in this serie.
                        // -> this serie can never be winner
                    } else {
                        this.gameIsDraw = false;
                        this.gameIsOver = false;
                        // on the board is still some square to place symbol.
                        // one of player have both symbols in this serie.
                        // it can be still winner.
                    }
                    break;
                default:
                    // if only one or none symbol is filled in this serie,
                    // it can be still winner.
                    this.gameIsOver = false;
                    this.gameIsDraw = false;
                    break;
            }
        }

        if (emptySquareTotal == 0) {
            // there are no more empty squares on the board
            this.gameIsDraw = true;
        }
        // draw can also be result of the game
        if (this.gameIsDraw == true) {
            this.gameIsOver = true;
        }
        return this.gameIsOver;
    }

}
