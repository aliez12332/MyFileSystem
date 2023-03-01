package fileOptions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @org.junit.jupiter.api.Test
    void fileToBytes() throws IOException {
        byte[] bytes = Utils.fileToBytes("C:\\Users\\86191\\Desktop\\新建文本文档.txt");
        System.out.println(bytes.length);
    }
}