public class chessBackend {
    private static long wP; // White pawns bitboard
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
        //legalPawnMoves();
        printBitBoard();
    }   
    private static void promptAndShowLegalMoves() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter piece position (e.g., e2): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.length() != 2 || input.charAt(0) < 'a' || input.charAt(0) > 'h' || input.charAt(1) < '1' || input.charAt(1) > '8') {
            System.out.println("Invalid input.");
            return;
        }

        int file = input.charAt(0) - 'a';
        int rank = input.charAt(1) - '1';
        int square = rank * 8 + file;
        long mask = 1L << square;

        if ((wP & mask) != 0) {
            long moves = legalPawnMoves(mask);
            System.out.println("White Pawn legal moves:");
            printMoves(moves);
        } else if ((bP & mask) != 0) {
            long moves = legalPawnMoves(mask);
            System.out.println("Black Pawn legal moves:");
            printMoves(moves);
        } else if ((wR & mask) != 0) {
            System.out.println("White Rook selected. (Legal moves not implemented)");
        } else if ((bR & mask) != 0) {
            System.out.println("Black Rook selected. (Legal moves not implemented)");
        } else if ((wN & mask) != 0) {
            System.out.println("White Knight selected. (Legal moves not implemented)");
        } else if ((bN & mask) != 0) {
            System.out.println("Black Knight selected. (Legal moves not implemented)");
        } else if ((wB & mask) != 0) {
            System.out.println("White Bishop selected. (Legal moves not implemented)");
        } else if ((bB & mask) != 0) {
            System.out.println("Black Bishop selected. (Legal moves not implemented)");
        } else if ((wQ & mask) != 0) {
            System.out.println("White Queen selected. (Legal moves not implemented)");
        } else if ((bQ & mask) != 0) {
            System.out.println("Black Queen selected. (Legal moves not implemented)");
        } else if ((wK & mask) != 0) {
            System.out.println("White King selected. (Legal moves not implemented)");
        } else if ((bK & mask) != 0) {
            System.out.println("Black King selected. (Legal moves not implemented)");
        } else {
            System.out.println("No piece found at that position.");
        }
    }

    private static void printMoves(long moves) {
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                long mask = 1L << (rank * 8 + file);
                if ((moves & mask) != 0) {
                    char col = (char) ('a' + file);
                    int row = rank + 1;
                    System.out.print("" + col + row + " ");
                }
            }
        }
        System.out.println();
    }


    //given a pawn, returns a long with all the legal pawn moves
    private static long legalPawnMoves(long pawnPosition) {
        long moves = 0x0L;
        // Determine if the pawn is white or black
        boolean isWhite = (pawnPosition & wP) != 0;
        long allPieces = wP | wR | wN | wB | wQ | wK | bP | bR | bN | bB | bQ | bK;

        if (isWhite) {
            // Single move forward
            long singleMove = (pawnPosition >>> 8) & ~allPieces;
            moves |= singleMove;

            // Double move forward from starting rank (rank 6)
            long startRank = 0x00FF000000000000L;
            if ((pawnPosition & startRank) != 0) {
            long doubleMove = (pawnPosition >>> 16) & ~allPieces & ~(allPieces >>> 8);
            moves |= doubleMove;
            }

            // Capture left
            long captureLeft = (pawnPosition >>> 9) & (bP | bR | bN | bB | bQ | bK) & 0x7F7F7F7F7F7F7F7FL;
            moves |= captureLeft;

            // Capture right
            long captureRight = (pawnPosition >>> 7) & (bP | bR | bN | bB | bQ | bK) & 0xFEFEFEFEFEFEFEFEL;
            moves |= captureRight;
        } else {
            // Single move forward
            long singleMove = (pawnPosition << 8) & ~allPieces;
            moves |= singleMove;

            // Double move forward from starting rank (rank 1)
            long startRank = 0x000000000000FF00L;
            if ((pawnPosition & startRank) != 0) {
            long doubleMove = (pawnPosition << 16) & ~allPieces & ~(allPieces << 8);
            moves |= doubleMove;
            }

            // Capture left
            long captureLeft = (pawnPosition << 7) & (wP | wR | wN | wB | wQ | wK) & 0xFEFEFEFEFEFEFEFEL;
            moves |= captureLeft;

            // Capture right
            long captureRight = (pawnPosition << 9) & (wP | wR | wN | wB | wQ | wK) & 0x7F7F7F7F7F7F7F7FL;
            moves |= captureRight;
        }
        return moves;
    }

    // Function to initialize the bitboards with starting positions
    private static void initializeBitBoard() {
        //white pieces
        wP = 0x00FF000000000000L;
        wR = 0x8100000000000000L;
        wN = 0x4200000000000000L;
        wB = 0x2400000000000000L;
        wQ = 0x0800000000000000L;
        wK = 0x1000000000000000L;
        //black pieces
        bP = 0x000000000000FF00L;
        bR = 0x0000000000000081L;
        bN = 0x0000000000000042L;
        bB = 0x0000000000000024L;
        bQ = 0x0000000000000008L;
        bK = 0x0000000000000010L;
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
