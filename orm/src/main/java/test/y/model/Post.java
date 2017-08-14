package test.y.model;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
/*import org.hibernate.search.annotations.Field;*/

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>This application demonstrates usage of hibernate OGM MongoDB provider
 * with spring MVC. Application is a sample Blog web site which uses MongoDB
 * as persistence store. This demo uses standard JPA annotations and methods to
 * query data store.</p>
 *
 * <p>Model class to be stored in data store and defines all properties of blog post.
 * <i>Comments on post are defined as an embedded collection.</i></p>
 *
 * @author Yasitha Thilakaratne
 * Date: 04/25/2017
 *
 */
@Indexed
@Entity
@Table(name = "post_collection")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "objectid")
    private String id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "headline")
    private String title;

    @Column(name = "content")
    private String description;

    @Column(name = "imgUrl")
    private String imgUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
