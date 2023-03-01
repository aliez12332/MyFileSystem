package Server;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 12345L;
    public String className;
    public String methodName;
    public Class<?>[] parameterTypes;
    public Object[] parameters;

}
