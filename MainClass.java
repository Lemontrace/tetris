class MainClass {
    public static void main(String[] args) {
        Board board = new Board(5,5);
        board.addPiece(Board.PieceType.T, 2);
        board.rotateCurrentPiece(true);
        board.hardDropCurrentPiece();
        System.out.print(board.render());
    }
}
