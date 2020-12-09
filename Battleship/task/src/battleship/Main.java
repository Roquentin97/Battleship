package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ClassicBattleshipConsole cbc = new ClassicBattleshipConsole(scanner);
            Player a = new ConsolePlayer("Player 1");
            Player b = new ConsolePlayer("Player 2");

            a.setFoe(b);
            b.setFoe(a);

            cbc.setPlayerA(a);
            cbc.setPlayerB(b);

            cbc.play();

        }
    }
}
