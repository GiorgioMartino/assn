import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Post implements Serializable {

    private UUID uuid;
    private String writer;
    private String content;
    private Date createdAt;
    private List<Comment> comments;

    public Post(UUID uuid, String writer, String content, Date createdAt, List<Comment> comments) {
        this.uuid = uuid;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "\n" + writer + ":\n\t" +
                content + "\n\t" +
                (comments.isEmpty() ? "" : "Comments: \n" + comments + "\n\t") +
                "[\"" + DataServer.dateFormat.format(createdAt) + "\" \"" + uuid + "\"]";
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
