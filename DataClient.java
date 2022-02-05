import java.util.Date;
import java.util.List;

public class DataClient {

    private User user;
    private List<Post> posts;
    private Date userExpires;
    private Date postsExpires;

    public boolean isUserExpired() {
        Date now = new Date(System.currentTimeMillis());
        return userExpires.before(now);
    }

    public boolean arePostsExpired() {
        Date now = new Date(System.currentTimeMillis());
        return postsExpires.before(now);
    }

    public void setUserExpires(Date userExpires) {
        this.userExpires = userExpires;
    }

    public void setPostsExpires(Date postsExpires) {
        this.postsExpires = postsExpires;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
