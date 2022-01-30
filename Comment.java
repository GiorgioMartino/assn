import java.util.Date;
import java.util.UUID;

public class Comment {

    private UUID uuid;
    private String writer;
    private String content;
    private Date createdAt;

    public Comment(UUID uuid, String writer, String content, Date createdAt) {
        this.uuid = uuid;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return writer + ": " + content + "\n" +
                DataServer.dateFormat.format(createdAt);
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
}
