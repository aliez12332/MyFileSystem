package Server;

import java.io.Serializable;

public class DataFile implements Serializable {
    private static final long serialVersionUID = 22345L;

    public String name;
    public byte[] dataToSave;
    public int ranking;

    public DataFile(String name, byte[] dataToSave, int ranking) {
        this.name = name;
        this.dataToSave = dataToSave;
        this.ranking = ranking;
    }

    public DataFile() {
    }
}
