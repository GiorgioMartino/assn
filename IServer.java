import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

    User login(String user, String pw) throws RemoteException;

    String handleFriendRequest(String from, String to) throws RemoteException;

    String handleAcceptRequest(String from, String to) throws RemoteException;

    User getUserInfo(String username) throws RemoteException;
}
