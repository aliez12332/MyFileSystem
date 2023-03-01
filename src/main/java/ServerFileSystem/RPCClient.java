package ServerFileSystem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import Server.DataFile;
import Server.RpcRequest;
import fileOptions.Utils;

public class RPCClient {
    private static int[] ports ={
            20000,
            20001,
            20002,
            20003,
            20004,
            20005,
            20006,
            20007,
            20008,
            20009,
    } ;

    public static AtomicInteger atomicInteger = new AtomicInteger(0) ;
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        saveOneFile("100");
        atomicInteger.set(0);
        getOneFile("100");
    }

    public static Boolean saveOneFile(String name) throws IOException, ExecutionException, InterruptedException {
        byte[] bytes = Utils.fileToBytes(name);
        ArrayList<byte[]> bytes1 = Utils.bytesToMoreBytes(10,bytes);
        ArrayList<Future<Boolean>> futureArrayList = new ArrayList<>();
        ExecutorService threadpool = null;
        threadpool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < bytes1.size(); i++) {
            Future<Boolean> future = threadpool.submit(new SaveTask(new DataFile(name,bytes1.get(i),i)) );
            futureArrayList.add(future);
        }
        for (Future<Boolean> future : futureArrayList) {
            Boolean result = future.get();
            System.out.println("服务器返回的："+result);
        }
        return true;
    }
    public static Boolean getOneFile(String name) throws IOException, ExecutionException, InterruptedException {

        ArrayList<Future<DataFile>> futureArrayList = new ArrayList<>();
        ExecutorService threadpool = null;
        threadpool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10 ; i++) {
            Future<DataFile> future = threadpool.submit(new GetFileTask(name));
            futureArrayList.add(i,future);
        }
        ArrayList<byte[]> results = new ArrayList<>(15);
        ArrayList<DataFile> dataFileArrayList = new ArrayList<>();
        for (Future<DataFile> future : futureArrayList ) {
            DataFile dataFile = future.get();
            dataFileArrayList.add(dataFile);
        }
        Comparator<DataFile> comparator = new Comparator<DataFile>() {
            public int compare(DataFile o1, DataFile o2) {
                return  o1.ranking - o2.ranking ; // 降序排序
            }
        };
        Collections.sort(dataFileArrayList,comparator);
        for (int i = 0; i < dataFileArrayList.size(); i++) {
            results.add( dataFileArrayList.get(i).dataToSave);
        }
        byte[] bytes = Utils.bytesArrToBytes(results);
        Utils.bytesToFile(bytes,"E:\\getFile","get到的文件.txt");
        return true;
    }


    public static Object getInfaceObj(Class inface){
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RpcRequest para = new RpcRequest();
                para.className = inface.getName();
                para.parameterTypes = method.getParameterTypes();
                para.parameters = args;
                para.methodName = method.getName();
                Object result = send(para);
                return result;
            }
        };
        return Proxy.newProxyInstance(inface.getClassLoader(),new Class[]{inface},handler);
    }
    public static Object send(RpcRequest para){
        try {
            int n = atomicInteger.getAndIncrement();
            System.out.println(ports[n]);
            Socket socket = new Socket("localhost" , ports[n]);
            System.out.println("已连接到服务器端口："+ports[n]);
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ous.writeObject(para);
            System.out.println("已发送请求");
            Object result = ois.readObject();
            socket.close();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
