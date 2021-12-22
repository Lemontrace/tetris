import java.util.ArrayList;

class MainClass {
    public static void main(String[] args) {
        
    }
}

class Board {

    int height,width;
    ArrayList<TetrisPiece> pieces = new ArrayList<TetrisPiece>(); 
    boolean[][] board;


    Board(int height, int width) {
        this.height=height;this.width=width;
        board = new boolean[width][height];
    }

    abstract class TetrisPiece {
        Coordinate coordinate;
        int rotation;
    }

    static class Coordinate {
        int x,y;

        Coordinate(int x,int y) {
            this.x=x;this.y=y;
        }
    }

    class TPiece {

    }


}