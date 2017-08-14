package test.y.model;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * <p>This application demonstrates usage of hibernate OGM MongoDB provider
 * with spring MVC. Application is a sample Blog web site which uses MongoDB
 * as persistence store. This demo uses standard JPA annotations and methods to
 * query data store.</p>
 *
 * <p>Model class of comment to be used as embedded entity.</p>
 *
 * @author Yasitha Thilakaratne
 * Date: 04/25/2017
 *
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
