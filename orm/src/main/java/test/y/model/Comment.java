package test.y.model;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 *
 *
 * Created by yasitha on 4/19/17.
 */
@Embeddable
public class Comment {
    private String email;

    private Date time;

    private String comment;

    public Comment(String email, Date time, String comment) {
        this.email = email;
        this.time = time;
        this.comment = comment;
    }

    public Comment() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
