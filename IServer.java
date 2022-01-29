import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

    User login(String user, String pw) throws RemoteException;
}
