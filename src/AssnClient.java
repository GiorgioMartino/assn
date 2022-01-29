import java.rmi.Naming;
import java.util.Scanner;

public class AssnClient {

    public static final String MENU = "\n0 - Menu" +
            "\n1 - Get info about User" +
//            "\n2 - See posts" +
            "\n9 - Exit";

    public static void main(String[] args) {
        if (args.length != 2)
            throw new RuntimeException("Username and Password required.");

        String serverName = "//localhost:1099/ASSNServer";

        IServer server = null;
        User user = null;
        try {
            server = (IServer) Naming.lookup(serverName);
            user = server.login(args[0], args[1]);
            System.out.println("Successfully logged in as " + user.getUserInfo().getName() + " " + user.getUserInfo().getSurname());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        boolean loop = true;
        int choice;
        while (loop) {
            System.out.println(MENU);
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            loop = choiceSwitch(choice, user, server);
        }
    }

    private static boolean choiceSwitch(int choice, User user, IServer server) {
        switch (choice) {
            case 0:
                return true;
            case 1:
                System.out.println(user.toString());
                return true;
            case 9:
            default:
                return false;
        }
    }


}
