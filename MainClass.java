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

    void clearLines(int y) {
        if(isFull(height)){
            for(int x = 0; x < width; x++){
                board[y][x] = false;
            }

        }
    }

    boolean isFull(int height){
        boolean check = true;
        for(int x = 0; x < width; x++){
            if(board[height][x] == false){
                check = false;
                break;
            }
        }
        return check;
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
