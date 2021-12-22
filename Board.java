import java.util.ArrayList;

class Board {

    TetrisPiece currentPiece;

    int height,width;
    PieceType[][] board;



    Board(int width, int height) {
        //set height and width
        this.width=width;this.height=height;
        //initialize board (to be all nulls)
        board = new PieceType[width][height];
        for (int i=0;i<width;i++) for (int j=0;j<height;j++) board[i][j]=null;
    }


    //tries to add new piece
    //returns true if new piece was actually added    
    boolean addPiece(PieceType type, int position) {
        //create new piece
        TetrisPiece newPiece;
        switch(type) {
            case T:
                newPiece = new TPiece();
                newPiece.center = new Coordinate(position,height-1);
                newPiece.rotation = 0;
                break;
            default:
                throw new IllegalStateException("not implemented");
        }

        //check if the piece is in a valid position

        boolean canBePlaced = true;
        for (Coordinate coordinate : newPiece.getCoordinates()) {
            if (!isAvailable(coordinate)) {canBePlaced = false; break;}
        }

        //replace current piece
        if (canBePlaced) currentPiece = newPiece;


        return canBePlaced;        
    }



    void rotateCurrentPiece(boolean right) {
        currentPiece.rotate(right);
    }


    //tries to drop current piece by 1 block
    //returns true if current piece was actually dropped by 1 block
    boolean dropCurrentPiece() {
        //check if current piece can be dropped by checking 1 block below every block in current piece
        boolean canBeDropped = true;
        for (Coordinate coordinate : currentPiece.getCoordinates()) {
            coordinate.y-=1; //check block below
            if (!isAvailable(coordinate)) {canBeDropped = false; break;}
        }
        if (canBeDropped) currentPiece.center.y-=1;
        return canBeDropped;
    }

    void hardDropCurrentPiece() {
        while(true){
            boolean drop = dropCurrentPiece();
            if(!drop){
               break;
            }
        }
    }

    boolean isAvailable(Coordinate coordinate) {
        return coordinate.x>=0&&coordinate.x<width&&coordinate.y>=0&&coordinate.y<height&&(board[coordinate.x][coordinate.y] != null);
    }

    void clearLines(int y, int stackedHeight) {
        ArrayList<Integer> fullList = fullLine(stackedHeight);
        while(!fullList.isEmpty()){
            int delete = fullList.remove(0);
            for(int x = 0; x < width; x++){
                board[delete][x] = null;
            }
            for(int d = delete; d < stackedHeight; d++) {
                for (int x = 0; x < width; x++) {
                    board[d][x] = board[d+1][x];
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
            if(board[height][x] == null){
                check = false;
                break;
            }
        }
        return check;
    }

    String render() {
        StringBuilder rendered = new StringBuilder();

        //add current piece to board
        for (Coordinate coordinate : currentPiece.getCoordinates()) {
            board[coordinate.x][coordinate.y] = currentPiece.getType();
        }

        //render last(top) line first
        for (int line = height-1;line>=0;line--) {
            StringBuilder lineBuilder = new StringBuilder();
            lineBuilder.append("|");
            for (int i=0;i<width;i++) {
                String blockString = getBlockString(board[i][line]);
                lineBuilder.append(blockString);
            }
            lineBuilder.append("|\n");

            rendered.append(lineBuilder.toString());
        }
        for(int i=0;i<width+2;i++) rendered.append("-");


        //remove current piece from board
        for (Coordinate coordinate : currentPiece.getCoordinates()) {
            board[coordinate.x][coordinate.y] = null;
        }

        return rendered.toString();
    }

    private String getBlockString(PieceType type) {
        if (type==null) return " ";
        switch (type) {
            case T:
                return "T";
            default:
                return "?";
        }
    }



    static enum PieceType {
        T, S, Z, L, J, O, I
    }

    abstract class TetrisPiece {
        Coordinate center;
        int rotation;
        
        abstract PieceType getType();
        abstract Coordinate[] getCoordinates();

        void rotate(boolean right) {
            if (right) rotation = (rotation + 1) % 4;
            else rotation = (rotation - 1) % 4;
        }

    }

    class TPiece extends TetrisPiece {

        @Override
        Board.PieceType getType() {
            return PieceType.T;
        }

        @Override
        Board.Coordinate[] getCoordinates() {
            Coordinate[] coordinates = new Coordinate[4];

            //avoid repetition
            int x=center.x,y=center.y;

            //in all cases, center stays the same
            coordinates[0]=new Coordinate(x,y);
            switch (rotation) {
                case 0: //ㅜ
                    coordinates[1] = new Coordinate(x,y-1); // x,y-1
                    coordinates[2] = new Coordinate(x+1,y); // x+1,y
                    coordinates[3] = new Coordinate(x-1,y); // x-1,y
                    break;
                case 1://ㅓ
                    coordinates[1] = new Coordinate(x-1,y); // x-1,y
                    coordinates[2] = new Coordinate(x,y+1); // x,y+1
                    coordinates[3] = new Coordinate(x,y-1); // x,y-1
                case 2://ㅗ
                    coordinates[1] = new Coordinate(x,y+1); // x, y+1
                    coordinates[2] = new Coordinate(x+1,y);// x+1,y
                    coordinates[3] = new Coordinate(x-1,y); // x-1,y
                case 3: //ㅏ
                    coordinates[1] = new Coordinate(x+1,y); // x+1,y
                    coordinates[2] = new Coordinate(x,y+1); // x, y+1
                    coordinates[3] = new Coordinate(x,y-1);// x, y-1

                default:
                    throw new IllegalStateException("wrong value for rotation on TetrisPiece instance");
            }
            return coordinates;
        }
    }

    static class Coordinate {
        int x,y;

        Coordinate(Coordinate coordinate) {
            this.x=coordinate.x;this.y=coordinate.y;
        }

        Coordinate(int x,int y) {
            this.x=x;this.y=y;
        }
    }

}
