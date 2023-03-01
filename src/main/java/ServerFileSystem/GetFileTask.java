package ServerFileSystem;

import Server.DataFile;
import Server.IDao;

import java.util.concurrent.Callable;

import static ServerFileSystem.RPCClient.getInfaceObj;

/**
 * 完成服务端对一个客户端的get请求
 */
public class GetFileTask implements Callable {
    String name;
    public GetFileTask(String name) {
        this.name = name;
    }


    @Override
    public DataFile call()  {
        IDao dao = (IDao) getInfaceObj(IDao.class);
        DataFile result = dao.getFile(name);
        System.out.println("已收到服务端返回的DataFile数组");
        return result;
    }
}
