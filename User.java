import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private UserInfo userInfo;
    private String username;
    private String password;
    private List<String> friends;
    private List<String> requests;

    public User(UserInfo userInfo, String username, String password, List<String> friends, List<String> requests) {
        this.userInfo = userInfo;
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.requests = requests;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getRequests() {
        return requests;
    }

    public void setRequests(List<String> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return userInfo +
                "\nUsername='" + username + '\'' +
                "\nPassword='" + password + '\'' +
                "\nFriends=" + friends +
                "\nRequests=" + requests;
    }

    public static class UserInfo implements Serializable {
        private String name;
        private String surname;

        public UserInfo(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        @Override
        public String toString() {
            return "\nName='" + name + '\'' +
                    "\nSurname='" + surname + '\'';
        }
    }
}
