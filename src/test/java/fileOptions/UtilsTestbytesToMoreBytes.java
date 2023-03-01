package fileOptions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static fileOptions.Utils.bytesArrToBytes;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTestbytesToMoreBytes {

    @Test
    void bytesToMoreBytes() {
        byte[] bytes = new byte[105];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) i ;
        }
        ArrayList<byte[]> bytes1 = Utils.bytesToMoreBytes(10,bytes);
        for (int i = 0; i < bytes1.size(); i++) {
            System.out.println(bytes1.get(i).length);
            for (int j = 0; j < bytes1.get(i).length; j++) {
                System.out.print(" " + bytes1.get(i)[j]);
            }
        }
        byte[] result =  bytesArrToBytes(bytes1);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]+ " ");
        }

    }
}