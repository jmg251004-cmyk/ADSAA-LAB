import java.util.*;

public class NQueens {

    static int n;
    static int solutionCount = 0;

    static boolean isSafe(int board[][], int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }

    static void solve(int board[][], int row) {
        if (row == n) {
            solutionCount++;
            // Added formatting for clearer separation
            System.out.println("-------------------------");
            System.out.println(" Solution Number: " + solutionCount);
            System.out.println("-------------------------");
            printSolution(board);
            System.out.println(); 
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                solve(board, row + 1);
                board[row][col] = 0; // Backtrack
            }
        }
    }

    static void printSolution(int board[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1)
                    System.out.print(" Q "); // Added spaces for better grid look
                else
                    System.out.print(" . ");
            }
            System.out.println(); // Moves to next row
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of Queens (N): ");
        if (sc.hasNextInt()) {
            n = sc.nextInt();
            int board[][] = new int[n][n];
            solve(board, 0);

            if (solutionCount == 0) {
                System.out.println("No solution exists for N = " + n);
            } else {
                System.out.println("=========================");
                System.out.println(" TOTAL SOLUTIONS: " + solutionCount);
                System.out.println("=========================");
            }
        }
        sc.close();
    }
}