package fileOptions;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static fileOptions.Utils.fileToBytes;

public class ReadToBytesTask implements Callable {
    // TRE
    private String fileName;

    public ReadToBytesTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public byte[] call() throws Exception {
        return fileToBytes(fileName);
    }
}
