class MainClass {
    public static void main(String[] args) {

    }
}

class Board {

    TetrisPiece currentPiece;

    int height,width;
    PieceType[][] board;



    Board(int height, int width) {
        //set height and width
        this.height=height;this.width=width;
        //initialize board
        board = new boolean[width][height];
    }

    void rotate(boolean right) {
        currentPiece.rotate(right);
    }


    //assumes the piece can be dropped
    //returns true if currentPiece is directly on top of another piece
    boolean drop() {
        currentPiece.center.y-=1;
        boolean touching = false;
        for (Coordinate coordinate : currentPiece.getCoordinates()) {
            if (board[coordinate.x][coordinate.y-1] != null) {touching = true; break;}
        }
        return touching;
    }

    void hardDrop() {

    }

    void clearLines(int y, int stackedHeight) {
        ArrayList<Integer> fullList = fullLine(stackedHeight);
        while(!fullList.isEmpty()){
            int delete = fullList.remove(0);
            for(int x = 0; x < width; x++){
                board[delete][x] = false;
            }
            for(int d = delete; d < stackedHeight; d++) {
                for (int x = 0; x < width; x++) {
                    board[d + 1][x] = board[d][x];
                }
            }
        }
    }

    ArrayList<Integer> fullLine(int stacked){ // 한방에 처리하기 위한 Integer ArrayList를 반환
        ArrayList<Integer> fullList = new ArrayList<>();
        for(int y = 0; y < stacked; y++){
            if(isFull(y)){
                fullList.add(y);
            }
        }
        return fullList;
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

    static enum PieceType {
        T, S, Z, L, J, O, I
    }

    abstract class TetrisPiece {
        Coordinate center;
        int rotation;
        
        abstract Coordinate[] getCoordinates();

        void rotate(boolean right) {
            if (right) rotation = (rotation + 1) % 4;
            else rotation = (rotation - 1) % 4;
        }

    }

    class TPiece extends TetrisPiece {
        @Override
        Board.Coordinate[] getCoordinates() {
            Coordinate[] coordinates = new Coordinate[4];

            return coordinates;
        }
    }

    static class Coordinate {
        int x,y;

        Coordinate(int x,int y) {
            this.x=x;this.y=y;
        }
    }

}
