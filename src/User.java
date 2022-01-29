import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private UserInfo userInfo;
    private String username;
    private String password;
    private List<UserInfo> friends;
    private List<UserInfo> requests;

    public User(UserInfo userInfo, String username, String password, List<UserInfo> friends, List<UserInfo> requests) {
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

    public List<UserInfo> getFriends() {
        return friends;
    }

    public void setFriends(List<UserInfo> friends) {
        this.friends = friends;
    }

    public List<UserInfo> getRequests() {
        return requests;
    }

    public void setRequests(List<UserInfo> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return userInfo +
                "\nusername='" + username + '\'' +
                "\npassword='" + password + '\'' +
                "\nfriends=" + friends +
                "\nrequests=" + requests;
    }

    //    @Data
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
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
            return "\nname='" + name + '\'' +
                    "\nsurname='" + surname + '\'';
        }
    }
}
