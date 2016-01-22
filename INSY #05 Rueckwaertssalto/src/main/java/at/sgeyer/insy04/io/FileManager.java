package at.sgeyer.insy04.io;

import java.io.File;

/**
 * Created by Stefan on 14.01.2015.
 */
public class FileManager implements IOManager {

    private File file;

    public FileManager(String fileName) {
        this.file = new File(fileName);
    }

    @Override
    public String readLine() {
        if (this.file.exists()) {

        }

        return null;
    }

    @Override
    public void writeLine(String line) {
        if (this.file.exists()) {

        }
    }
}
