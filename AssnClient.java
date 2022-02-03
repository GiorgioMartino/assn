import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;

public class AssnClient {

    public static final String MENU = "\n0 - Menu" +
            "\n1 - Get info about User" +
            "\n2 - Send request to User" +
            "\n3 - Accept request from User" +
            "\n4 - Write new Post" +
            "\n5 - See Posts" +
            "\n6 - Comment Post" +
            "\n9 - Exit";
    static IServer server = null;

    public static void main(String[] args) {
        if (args.length != 2)
            throw new RuntimeException("Username and Password required.");

        String serverName = "//localhost:1099/ASSNServer";

        User user = null;
        try {
            server = (IServer) Naming.lookup(serverName);
            user = server.login(args[0], args[1]);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            System.out.println("Successfully logged in as " + user.getUserInfo().getName() + " " + user.getUserInfo().getSurname());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        boolean loop = true;
        int choice;
        while (loop) {
            System.out.println(MENU);
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            loop = choiceSwitch(choice, user.getUsername());
        }
    }

    private static boolean choiceSwitch(int choice, String username) {
        switch (choice) {
            case 0:
                return true;
            case 1:
                System.out.println(getUserInfo(username));
                return true;
            case 2:
                sendRequest(username);
                return true;
            case 3:
                acceptRequest(username);
                return true;
            case 4:
                writeNewPost(username);
                return true;
            case 5:
                seePosts(username);
                return true;
            case 6:
                commentPost(username);
                return true;
            case 9:
            default:
                return false;
        }
    }

    private static void commentPost(String username) {
        System.out.print("Comment <post-id>: ");
        Scanner scanner = new Scanner(System.in);
        UUID uuid = UUID.fromString(scanner.next());
        System.out.println("Write comment: ");
        scanner = new Scanner(System.in);
        String content = scanner.nextLine();

        try {
            String res = server.commentPostByUuid(username, uuid, content);
            if (res.isEmpty())
                System.out.println("Successfully commented Post " + uuid);
            else
                System.out.println(res);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private static void seePosts(String username) {
        try {
            server.seePostsForUser(username)
                    .forEach(System.out::println);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void writeNewPost(String username) {
        System.out.println("Write content:");
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        try {
            String res = server.createPostForUser(username, content);
            if (res.isEmpty())
                System.out.println("Successfully created Post.");
            else
                System.out.println(res);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String getUserInfo(String username) {
        try {
            return server.getUserInfo(username).toString();
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void acceptRequest(String username) {
        System.out.print("Accept request from <username>: ");
        Scanner scanner = new Scanner(System.in);
        String param = scanner.next();
        try {
            String res = server.handleAcceptRequest(username, param);
            if (res.isEmpty())
                System.out.println("Successfully accepted friendship request.");
            else
                System.out.println(res);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private static void sendRequest(String username) {
        System.out.print("Username: ");
        Scanner scanner = new Scanner(System.in);
        String param = scanner.next();
        try {
            String res = server.handleFriendRequest(username, param);
            if (res.isEmpty())
                System.out.println("Request successfully sent.");
            else
                System.out.println(res);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
