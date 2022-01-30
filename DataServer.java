import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataServer {

    public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    private List<User> users;
    private List<Post> posts;

    public List<User> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void initUsers() {
        users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String row;
            while ((row = br.readLine()) != null) {
                String[] values = row.split(";");
                users.add(new User(new User.UserInfo(values[0], values[1]),
                        values[2],
                        values[3],
                        new ArrayList<>(),
                        new ArrayList<>()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV");
        }
        posts = new ArrayList<>();
        System.out.println("Users initialized");
    }
}
