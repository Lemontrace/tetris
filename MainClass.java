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
        currentPiece.rotate(right);
    }

    void drop() {

    }

    void hardDrop() {

    }

    void clearLines() {

    }




    abstract class TetrisPiece {
        Coordinate center;
        int rotation;
        
        abstract Coordinate[] getRelativeCoordinates();

        void rotate(boolean right) {
            if (right) rotation = (rotation + 1) % 4;
            else rotation = (rotation - 1) % 4;
        }

    }

    static class Coordinate {
        int x,y;

        Coordinate(int x,int y) {
            this.x=x;this.y=y;
        }
    }

}