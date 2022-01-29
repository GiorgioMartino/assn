import java.util.ArrayList;
import java.util.List;

public class DataServer {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void initUsers() {
        users = List.of(new User(new User.UserInfo("Ad", "Min"),
                        "admin",
                        "admin",
                        new ArrayList<>(),
                        new ArrayList<>()),
                new User(new User.UserInfo("Giorgio", "Martino"),
                        "gmartino",
                        "password",
                        new ArrayList<>(),
                        new ArrayList<>()),
                new User(new User.UserInfo("Ilaria", "Incerti"),
                        "lallina",
                        "pwd",
                        new ArrayList<>(),
                        new ArrayList<>()));
        System.out.println("Users initialized");
    }
}
