package at.sgeyer.insy04.io;

import java.util.Scanner;

/**
 * Implementation of IOManager that reads and writes from the System streams
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class ConsoleManager implements IOManager {

    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        // blocking
        return scanner.nextLine();
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
