import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

    private final String writer;
    private final String content;
    private final Date createdAt;

    public Comment(String writer, String content, Date createdAt) {
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "\n\t("+DataServer.dateFormat.format(createdAt)+") " +
                writer + ": " + content;
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
