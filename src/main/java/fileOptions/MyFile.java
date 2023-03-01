package fileOptions;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Data
public class MyFile {
    private String name;
    private String filePath;
    private ArrayList<String> fileList;
    private static ArrayList<MyFile> myFileArrayList = new ArrayList<>();

    public MyFile() {
    }

    public MyFile(String name, String filePath, ArrayList<String> fileList) {
        this.name = name;
        this.filePath = filePath;
        this.fileList = fileList;
        // Hello
    }

    public static void addMyFile(MyFile myFile){
        myFileArrayList.add(myFile);
    }

    public boolean setFile(String name,String filePath) throws IOException {
        byte[] bytes = Utils.fileToBytes(filePath);
        //将数组分成十份放在ArrayList中
        ArrayList<byte[]> bytes1 = Utils.bytesToMoreBytes(10,bytes);
        ArrayList<String> fileList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //建立线程
            String ss = String.valueOf(i);
            fileList.add("C:\\Users\\86191\\Desktop"+"\\"+"新建文本文档"+ss+".txt");
            ThreadOfByteToFile threadOfByteToFile = new ThreadOfByteToFile(bytes1.get(i),"C:\\Users\\86191\\Desktop","新建文本文档"+ss+".txt");
            Thread t = new Thread(threadOfByteToFile);
            t.start();
        }
        MyFile myFile = new MyFile(name,filePath,fileList);
        addMyFile(myFile);
        return true;
    }
    public MyFile getMyFile(String name){
        for (int i = 0; i < myFileArrayList.size(); i++) {
            if(name.equals(myFileArrayList.get(i).name)){
                return myFileArrayList.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param name 写好之后存入的文件名
     * @param fileAllPath
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean getFile(String name,String fileAllPath) throws ExecutionException, InterruptedException {
        MyFile myFile = getMyFile(name);
        ArrayList<String> fileList = myFile.getFileList();

        ExecutorService threadpool = null;
        threadpool = Executors.newFixedThreadPool(10);
        ArrayList<Future<byte[]>> futureArrayList = new ArrayList<>();
        ArrayList<byte[]> bytesArr = new ArrayList<>();
        try{
            for (int i = 0; i < 10; i++) {
                Future<byte[]> future = threadpool.submit(new ReadToBytesTask(fileList.get(i)));
                futureArrayList.add(future);
                System.out.println("提交一个callable任务到线程池");
            }
        }finally {
            for (int i = 0; i < 10; i++) {
                bytesArr.add(futureArrayList.get(i).get());
                System.out.println("成功取到线程"+i+"的结果");
            }
        }


        //拿到所有的byte数组

        byte[] bytes = Utils.bytesArrToBytes(bytesArr);

        String outPath = "C:\\Users\\86191\\Desktop";
        String fileName = name + ".txt";
        Utils.bytesToFile(bytes,outPath,fileName);
        return true;
    }

    public  static boolean delete(){
        return true;
    }



}
