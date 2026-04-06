import java.util.Random;
import java.util.Scanner;

public class SlotMachineGame {
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        int balance;
        int bet;
        int numOfBets;
        int payout;
        String[] row;
        String playAgain = "S";

        while (playAgain.equals("S")) {

            balance = 200;
            numOfBets = 0;

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(":: BEM-VINDO AO JAVA SLOTS ::");
            System.out.println(" ⇨ Símbolos: 🍒 🍉 🥚 🍋 🌟");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            while (balance > 0) {
                System.out.println("      Saldo: R$" + balance);
                System.out.print(" ⇨ Digite o valor da aposta: ");
                bet = scanner.nextInt();
                scanner.nextLine(); // captura o \n

                if (bet > balance) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("    SALDO INSUFICIENTE :/");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    continue;
                } else if (bet <= 0) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("A aposta deve ser mais alta que 0!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    continue;
                } else {
                    balance -= bet;
                    numOfBets++;
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

                System.out.println("Girando...");
                row = spinRow(bet, numOfBets);
                printRow(row);
                payout = getPayout(row, bet);

                if (payout > 0) {
                    System.out.println("      Você ganhou R$" + payout + "!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    balance += payout;
                } else if (payout == -1) {
                    System.out.println("        PERDEU TUDO");
                    balance = 0;
                } else {
                    System.out.println("    Buaaaah, você perdeu!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            }
            // Recomeça loop caso o jogador queira jogar novamente
            System.out.print("Jogar novamente? (S/N): ");
            playAgain = scanner.nextLine().toUpperCase();
        }

        scanner.close();
    }

    static String[] spinRow(int bet, int numOfBets) {

        String[] symbols = {"🍒", "🍉", "🥚", "🍋", "🌟"};
        String[] riggedWin = {"🍒", "🍉", "🍋", "🌟", "🌟", "🌟", "🌟", "🌟", "🌟", "🌟"};
        String[] riggedLoss = {"🍒", "🍉", "🍋", "🥚", "🥚", "🥚", "🥚", "🥚", "🥚", "🥚"};

        String[] row = new String[3];

        Random random = new Random();

        if (numOfBets < 5)
            for (int i = 0; i < row.length; i++)
                row[i] = riggedWin[random.nextInt(riggedWin.length)];

        else if (bet > 199)
            for (int i = 0; i < row.length; i++)
                row[i] = riggedLoss[random.nextInt(riggedLoss.length)];
        else
            for (int i = 0; i < row.length; i++)
                row[i] = symbols[random.nextInt(symbols.length)];

        return row;
    }
    static void printRow(String[] row) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("        " + String.join(" | ", row));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    static int getPayout(String[] row, int bet) {

        if (row[0].equals(row[1]) && row[0].equals(row[2]))
            return switch (row[0]) {
                case "🍒" -> bet * 3;
                case "🍉" -> bet * 4;
                case "🍋" -> bet * 6;
                case "🌟" -> bet * 20;
                case "🥚" -> -1;
                default -> 0;
            };
        else if (row[0].equals(row[1]))
            return switch(row[0]) {
                case "🍒" -> bet;
                case "🍉" -> bet * 2;
                case "🍋" -> bet * 3;
                case "🌟" -> bet * 10;
                case "🥚" -> 0;
                default   -> 0;
            };
        else if (row[1].equals(row[2]))
            return switch(row[1]) {
                case "🍒" -> bet;
                case "🍉" -> bet * 2;
                case "🍋" -> bet * 3;
                case "🌟" -> bet * 10;
                case "🥚" -> 0;
                default   -> 0;
            };
        return 0;
    }
}
