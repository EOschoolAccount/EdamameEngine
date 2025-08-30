public class chessBackend {
    private static long wR; // White rooks bitboard
    private static long wN; // White knights bitboard
    private static long wB; // White bishops bitboard
    private static long wQ; // White queen bitboard
    private static long wK; // White king bitboard

    private static long bP; // Black pawns bitboard
    private static long bR; // Black rooks bitboard
    private static long bN; // Black knights bitboard
    private static long bB; // Black bishops bitboard
    private static long bQ; // Black queen bitboard
    private static long bK; // Black king bitboard

    public static void main(String[] args) {
        initializeBitBoard();
        printBitBoard();
    }

    private static void initializeBitBoard() {
        //white pieces
        wP = 0x00FF000000000000L;
        wR = 0x8100000000000000L;
        wN = 0x4200000000000000L;
        wB = 0x2400000000000000L;
        wQ = 0x1000000000000000L;
        wK = 0x8000000000000000L;
        //black pieces
        bP = 0x000000000000FF00L;
        bR = 0x0000000000000081L;
        bN = 0x0000000000000042L;
        bB = 0x0000000000000024L;
        bQ = 0x0000000000000010L;
        bK = 0x0000000000000080L;
        System.out.println("Chess Backend Initialized");
    }

    // Function to print the bitboard in a human-readable format
    private static void printBitBoard() {
    
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                long mask = 1L << (rank * 8 + file);
                if ((wP & mask) != 0) System.out.print("P ");
                else if ((wR & mask) != 0) System.out.print("R ");
                else if ((wN & mask) != 0) System.out.print("N ");
                else if ((wB & mask) != 0) System.out.print("B ");
                else if ((wQ & mask) != 0) System.out.print("Q ");
                else if ((wK & mask) != 0) System.out.print("K ");
                else if ((bP & mask) != 0) System.out.print("p ");
                else if ((bR & mask) != 0) System.out.print("r ");
                else if ((bN & mask) != 0) System.out.print("n ");
                else if ((bB & mask) != 0) System.out.print("b ");
                else if ((bQ & mask) != 0) System.out.print("q ");
                else if ((bK & mask) != 0) System.out.print("k ");
                else System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
