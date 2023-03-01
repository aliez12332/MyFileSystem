package fileOptions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTestbytesToFile {

    @Test
    void bytesToFile() throws IOException {
        byte[] bytes = Utils.fileToBytes("C:\\Users\\86191\\Desktop\\新建文本文档.txt");
        System.out.println(bytes.length);
        Utils.bytesToFile(bytes,"C:\\Users\\86191\\Desktop","新建文本文档2.txt");


    }
}