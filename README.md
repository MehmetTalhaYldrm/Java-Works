# Java-Workspace

package denemezıbabos;
import java.util.Scanner;
public class DenemeZıbabos {
public static void main(String[] args) {
char[][] board = {
{' ', ' ', ' '},
{' ', ' ', ' '},
{' ', ' ', ' '}
};    
char currentPlayer = 'X';
    boolean gameWon = false;
    System.out.println("XOX Oyununa Hoş Geldiniz!");
    while (true) {
        printBoard(board);
        System.out.println("Sıra: " + currentPlayer);
        int row, col;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Satır ve sütun numarasını gir (0-2 arası, boşlukla ayır): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Geçersiz giriş! Lütfen 0-2 arasında bir sayı girin.");
            } else if (board[row][col] != ' ') {
                System.out.println("Bu hücre dolu! Tekrar deneyin.");
            } else {
                break;
            }
        }
        board[row][col] = currentPlayer;
        if (checkWinner(board, currentPlayer)) {
            printBoard(board);
            System.out.println("Tebrikler! Oyunu " + currentPlayer + " kazandı!");
            gameWon = true;
            break;
        }
        if (isBoardFull(board)) {
            printBoard(board);
            System.out.println("Oyun berabere!");
            break;
        }
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    if (!gameWon) {
        System.out.println("Oyun bitti. Teşekkürler!");
    }
}
private static void printBoard(char[][] board) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            System.out.print(board[i][j]);
            if (j < 2) System.out.print(" | ");
        }
        System.out.println();
        if (i < 2) System.out.println("---------");
    }
}
private static boolean checkWinner(char[][] board, char player) {
    // Satırları kontrol et
    for (int i = 0; i < 3; i++) {
        if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
            return true;
        }
    }
    // Sütunları kontrol et
    for (int i = 0; i < 3; i++) {
        if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
            return true;
        }
    }
    // Çaprazları kontrol et
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
        return true;
    }
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
        return true;
    }
    return false;
}
private static boolean isBoardFull(char[][] board) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] == ' ') {
                return false;
            }
        }
    }
    return true;
}}
