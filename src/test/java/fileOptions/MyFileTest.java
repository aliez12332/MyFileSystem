package fileOptions;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class MyFileTest {

    @Test
    void set() throws IOException, ExecutionException, InterruptedException {
        MyFile myFile = new MyFile();
        myFile.setFile("new","C:\\Users\\86191\\Desktop\\新建文本文档11.txt");
        Thread.sleep(5000);
        myFile.getFile("new","a");

    }
}