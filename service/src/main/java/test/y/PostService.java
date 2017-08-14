package test.y;

import org.springframework.stereotype.Service;
import test.y.model.Comment;
import test.y.model.Post;

import java.util.List;

/**
 * <p>This application demonstrates usage of hibernate OGM MongoDB provider
 * with spring MVC. Application is a sample Blog web site which uses MongoDB
 * as persistence store. This demo uses standard JPA annotations and methods to
 * query data store.</p>
 *
 * <p>Interface to post based service implementation.</p>
 *
 * @author Yasitha Thilakaratne
 * Date: 04/25/2017
 *
 */
public interface PostService {

    /**
     * Saves new post instance
     * @param post post object to persist
     */
    void create(Post post);

    /**
     * @return list of all posts
     */
    List<Post> getAllPosts();

    /**
     * This method uses Hibernate search (Lucene based full text search library)
     * for title based lookup
     *
     * @param title full or partial title to search
     * @return
     */
    List<Post> getPostByTitle(String title);

    /**
     * Saves comment
     * @param postId post id to add comment
     * @param comment comment entity to add
     */
    void addComment(String postId, Comment comment);
}
