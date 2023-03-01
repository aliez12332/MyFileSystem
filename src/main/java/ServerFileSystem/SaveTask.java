package ServerFileSystem;

import Server.DataFile;
import Server.IDao;

import java.util.concurrent.Callable;

import static ServerFileSystem.RPCClient.getInfaceObj;

public class SaveTask implements Callable {
    DataFile dataFile;

    public SaveTask(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("进入Callable代码块");
        IDao dao = (IDao) getInfaceObj(IDao.class);
        Boolean result = dao.saveFile(dataFile);
        System.out.println(result);
        return result;

    }
}
