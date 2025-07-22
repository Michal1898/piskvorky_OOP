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
            Boolean MyBoardUpdated=false;
        do {
             MyBoardUpdated=myBoard.modifySquare('A', "A1");
             myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('B', "A2");
            myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('C', "A3");
            myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('D', "B1");
            myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('E', "B2");
            myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('F', "B3");
            myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('G', "C1");
            myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('H', "C2");
            myBoard.printBoard();

            MyBoardUpdated=myBoard.modifySquare('I', "C3");
            myBoard.printBoard();

        }while (MyBoardUpdated==false);

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
        private String[]  lineDescription={"3","2","1"};
        private String[] columnDescription= {"A","B" ,"C"};

        GameBoard() {
            // Fill Game Board with symbol E - like Empty square
            for (int x = 0; x < LINES_COUNT; x++) {
                for (int y = 0; y < COLUMN_COUNT; y++) {
                    GameZone[x][y] = 'E';
                }
            }
        }

        void printBoard() {
            Character squareDescription;
            for (int x = 0; x < LINES_COUNT; x++) {
                Character linesDescription=decoderCoordinates(0,x).charAt(1);
                System.out.print(ANSI_GREEN+linesDescription+ ": "+ ANSI_RESET);
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
                Character columnDesription=decoderCoordinates(y, 0).charAt(0);
                System.out.print(ANSI_GREEN + " " + columnDesription+ " ");
            }
            System.out.print(ANSI_RESET + "\n");
        }
        String decoderCoordinates(int x, int y){
            // because computer array system is for human somewhat unnatural,
            // I decode it to for human more natural
            // chessboad system and return it.

            String chessboardCoords=this.columnDescription[x]+this.lineDescription[y];
            return (chessboardCoords);
        }
        Integer[] coderCoordinates(String chessBoardCoord){
            String xChessboard = String.valueOf(chessBoardCoord.charAt(0));
            String yChessboard = String.valueOf(chessBoardCoord.charAt(1));

            System.out.println(chessBoardCoord + ":" +yChessboard+"  " + xChessboard);
            Integer xComputer=0;
            Integer yComputer=0;
            for (int x=0; x<this.COLUMN_COUNT; x++){
                if (this.columnDescription[x].equals(xChessboard)){
                    xComputer = this.COLUMN_COUNT -1 - x;
                }
            }
                for (int y=0; y<this.LINES_COUNT; y++){
                    if (lineDescription[y].equals(yChessboard)){
                        yComputer = this.LINES_COUNT -1 - y;
                    }

            }

            Integer[] ComputerCoord = {xComputer, yComputer};
            //System.out.println(xComputer.toString() + " " + yComputer.toString());
        return ComputerCoord;
    }
    Boolean modifySquare(Character playerSymbol, String squareCoordinates){
        System.out.println("Symbol:" +playerSymbol);
        System.out.println("Chess Coords:" +squareCoordinates);
        Integer[] ComputerCoords=this.coderCoordinates(squareCoordinates);
        System.out.println("Computer Coords Column:" +ComputerCoords[0]);
        System.out.println("Computer Coords Line:" +ComputerCoords[1]);

        this.GameZone[ComputerCoords[0]][ComputerCoords[1]]=playerSymbol;
        return true;
    }
}}