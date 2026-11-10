import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/* Class: CSE 1322L
Section: J04
Term: Full Term
Instructor: Gar Lock
Name: Michael Pack
Assignment#: 6*/
class ConnectFour {
     char[][] board = new char[6][7];
     String turn;
     char token;

    public ConnectFour() {
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        this.turn = "Red";
        this.token = 'R';
    }

    public void nextTurn() {
        if (turn.equals("Red")) {
            this.turn = "Yellow";
            this.token = 'Y';
        } else {
            this.turn = "Red";
            this.token = 'R';
        }
    }

    public int nextAvailablePosition(int column) {
        for (int row = 5; row >= 0; row--) {
            if (board[row][column] == ' ') {
                return row;
            }
        }
        return -1;
    }

    public void dropPiece(int column, char token) throws ColumnFull {
        int row = nextAvailablePosition(column);
        if (row == -1) {
            throw new ColumnFull("Column is already full. Try again.");
        }
        board[row][column] = token;
    }

    @Override
    public String toString() {
        String to_return = " 0 1 2 3 4 5 6";
        for (int i = 0; i < 6; i++) {
            to_return += "\n-----------------------------\n ";
            to_return += "| ";
            for (int j = 0; j < 7; j++) {
                to_return += board[i][j] + " | ";
            }
        }
        to_return += "\n-----------------------------\n ";
        return to_return;
    }

    public void saveGame() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter File Name: ");
            String filename = scan.next();
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                for (char[] row : board) {
                    writer.println(new String(row));
                }
            }
            System.out.println("Game saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
    public void loadGame(){
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter a file name: ");
            String filename=scan.nextLine();
            try(BufferedReader Reader=new BufferedReader(new FileReader(filename))){
                for(int i = 0; i<6;i++){
                    String line = Reader.readLine();
                    if (line!=null){
                        board[i]=line.toCharArray();
                    }
                }
            }
            System.out.println("Game loaded from "+filename);
        } catch (IOException e){
            System.out.println("Error loading game: "+e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
ConnectFour Game = new ConnectFour();
Scanner scan = new Scanner(System.in);
int column = 0;
while(true){
    try {
        System.out.print(Game.toString());
        System.out.println("Which column would "+Game.turn+" like to go in (7 to save, 8 to load, 9 to quit)");
        column = scan.nextInt();
        if (column==9){
            break;
        } else if (column==7){
            Game.saveGame();
        } else if(column==8){
            Game.loadGame();
        } else{
            if (column>=0&&column<=6){
                try{
                    Game.dropPiece(column,Game.token);
                    Game.nextTurn();
                }catch (ColumnFull e){
                    System.out.println("Error: "+e.getMessage());
            }
        }
    }
    } catch (NumberFormatException e){
        System.out.println("Invalid input, try again.");
    }
} System.out.println("Ending game, goodbye.");

    }
}