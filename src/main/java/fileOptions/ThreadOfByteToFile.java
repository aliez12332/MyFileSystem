package fileOptions;

import lombok.Data;

/**
 * 将一个byte数组转换成文件
 */
@Data
public class ThreadOfByteToFile implements Runnable{

    private byte[] bytes;
    String outPath;
    String fileName;

    public ThreadOfByteToFile(byte[] bytes, String outPath, String fileName) {
        this.bytes = bytes;
        this.outPath = outPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Utils.bytesToFile(bytes, outPath, fileName);
    }
}
