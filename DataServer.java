import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataServer {

    private List<User> users;

    public List<User> getUsers() {
        return users;
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

        System.out.println("Users initialized");
    }
}
