import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println(String.format("----- Remote login called for %s %s", username, pw));
        return dataServer.getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .filter(u -> u.getPassword().equals(pw))
                .findFirst()
                .orElse(null);
    }

    public String handleFriendRequest(String from, String to) {
        System.out.println(String.format("----- Remote friend request called by %s", from));
        User userTo = filterUser(to);
        User userFrom = filterUser(from);

        if (userTo == null)
            return "User not found";
        if (userTo.getFriends().contains(userFrom.getUsername()))
            return "Already friends";

        userTo.getRequests().add(userFrom.getUsername());
        System.out.println(String.format("%s requested friendship to %s",
                userFrom.getUsername(),
                userTo.getUsername()));
        return "";
    }

    public String handleAcceptRequest(String from, String to) {
        System.out.println(String.format("----- Remote accept request called by %s", from));
        User requestingUser = filterUser(to);
        User acceptingUser = filterUser(from);

        if (requestingUser == null)
            return "User not found";
        if (!acceptingUser.getRequests().contains(requestingUser.getUsername()))
            return "No request to accept";

        acceptingUser.getRequests().remove(requestingUser.getUsername());
        acceptingUser.getFriends().add(requestingUser.getUsername());
        requestingUser.getFriends().add(acceptingUser.getUsername());

        System.out.println(String.format("%s and %s are now friends!",
                acceptingUser.getUsername(),
                requestingUser.getUsername()));
        return "";
    }

    public User getUserInfo(String username) {
        System.out.println(String.format("----- Remote user info called by %s", username));
        return filterUser(username);
    }

    private User filterUser(String username) {
        return dataServer.getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public String createPostForUser(String username, String content) {
        System.out.println(String.format("----- Remote create post called by %s", username));

        User user = filterUser(username);

        if (user == null)
            return "User not found";

        Post post = new Post(UUID.randomUUID(),
                user.getUsername(),
                content,
                new Date(System.currentTimeMillis()),
                new ArrayList<>());

        dataServer.getPosts().add(post);

        System.out.println(String.format("New Post %s created by %s",
                post.getUuid(),
                post.getWriter()));
        return "";
    }

    public List<Post> seePostsForUser(String username) {
        System.out.println(String.format("----- Remote see posts called by %s", username));

        User user = filterUser(username);

        List<Post> posts = dataServer.getPosts().stream()
                .filter(p -> user.getFriends().contains(p.getWriter()) ||
                        p.getWriter().equals(user.getUsername()))
                .collect(Collectors.toList());
        Collections.reverse(posts);
        return posts;
    }

    public String commentPostByUuid(String username, UUID uuid, String content) {
        System.out.println(String.format("----- Remote comment post called by %s", username));

        User user = filterUser(username);

        Post post = dataServer.getPosts().stream()
                .filter(p -> user.getFriends().contains(p.getWriter()) ||
                        p.getWriter().equals(user.getUsername()))
                .filter(p ->  p.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);

        if (post == null)
            return "Cannot find post for uuid " + uuid;

        post.getComments().add(new Comment(user.getUsername(),
                content,
                new Date(System.currentTimeMillis())));

        System.out.println(String.format("New Comment added for Post %s by %s",
                post.getUuid(),
                user.getUsername()));

        return "";
    }
}
