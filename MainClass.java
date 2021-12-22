class MainClass {
    public static void main(String[] args) {

    }
}

class Board {

    TetrisPiece currentPiece;

    int height,width;
    boolean[][] board;


    Board(int height, int width) {
        //set height and width
        this.height=height;this.width=width;
        //initialize board
        board = new boolean[width][height];
    }

    void rotate(boolean right) {

    }

    void drop() {

    }

    void hardDrop() {

    }

    void clearLines() {
        
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

}