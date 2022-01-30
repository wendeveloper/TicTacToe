import java.util.*;

//https://www.youtube.com/watch?v=gQb3dE-y1S4&t=76s
public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        // create tic tac toe game board
        // 2D array of chars - 3 rows and 3 columns, with row and column of lines to create board
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
                };
        printGameBoard(gameBoard);   // call the gameBoard method to print to screen

        // create game loop
        while(true) {
                // get user input
            //-------------player------------------
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");          // prompt user to enter position
            int playerPos = scan.nextInt();                             // get position
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter a different position.");  // if position already taken
                playerPos = scan.nextInt();
                //Prevent user from printing on top of CPU
//                while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
//                    System.out.println("Position taken! Enter another position:");
//                    playerPos= scan.nextInt();
//                }
            }

            placePiece(gameBoard, playerPos, "player");     //stores playerPos

            String result = checkWinner();   // check if there's a winner
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }


            //---------------cpu----------------
            Random rand = new Random();    // cpu move
            int cpuPos = rand.nextInt(9) + 1;   // generate random position
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;  // if position already taken
            }

            placePiece(gameBoard, cpuPos, "cpu");   //cpu

            printGameBoard(gameBoard);

            result = checkWinner();     // check if there's a winner
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        System.out.println(user + " pos: " + pos);

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);   // add position to the list
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;       // change element in array
                break;
            case 2:
                gameBoard[0][2] = symbol;       // change element in array
                break;
            case 3:
                gameBoard[0][4] = symbol;       // change element in array
                break;
            case 4:
                gameBoard[2][0] = symbol;       // change element in array
                break;
            case 5:
                gameBoard[2][2] = symbol;       // change element in array
                break;
            case 6:
                gameBoard[2][4] = symbol;       // change element in array
                break;
            case 7:
                gameBoard[4][0] = symbol;       // change element in array
                break;
            case 8:
                gameBoard[4][2] = symbol;       // change element in array
                break;
            case 9:
                gameBoard[4][4] = symbol;       // change element in array
                break;
            default:
                break;
        }

    }

    // winner conditions
    public static String checkWinner() {
        List topRow     = Arrays.asList(1, 2, 3);   // winner positions rows
        List midRow     = Arrays.asList(4, 5, 6);
        List bottomRow  = Arrays.asList(7, 8, 9);
        List leftCol    = Arrays.asList(1, 4, 7);   // winner positions cols
        List midCol     = Arrays.asList(2, 5, 8);
        List rightCol   = Arrays.asList(3, 6, 9);
        List cross1     = Arrays.asList(1, 5, 9);   // winner positions diagonal
        List cross2     = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>(); //list of lists called winning
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l: winning) {                  // for each List l inside of winning

            if(playerPositions.containsAll(l)) {
                return "Congratulations.  You won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU Wins!  Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "This is a tie!";
            }
        }

        return "";
    }

    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {           // for each row inside gameBoard
            for(char charSymbol : row) {        // for each symbol inside of row
                System.out.print(charSymbol);   // print out charSymbol
            }
            System.out.println();               // after each row, print a line
        }
    }

}
