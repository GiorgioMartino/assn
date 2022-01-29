import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class Server extends UnicastRemoteObject implements IServer {

    static DataServer dataServer;
    private final String serverName;

    public Server(String serverName) throws RemoteException {
        super();
        dataServer = new DataServer();
        dataServer.initUsers();
        this.serverName = serverName;
    }

    public static void main(String[] args) {
        String serverName = "ASSNServer";
        try {
            Server server = new Server(serverName);
            Naming.rebind(serverName, server);
            System.out.println("Server: ASSNServer registered");
        } catch (RemoteException | MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }

    public User login(String username, String pw) {
        System.out.println("Remote login called");
        Optional<User> optUser = dataServer.getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .filter(u -> u.getPassword().equals(pw))
                .findFirst();

        if (optUser.isPresent()) {
            return optUser.get();
        } else
            throw new RuntimeException("User not found");
    }


}
