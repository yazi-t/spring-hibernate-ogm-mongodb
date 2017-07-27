package test.y;

import org.springframework.stereotype.Service;
import test.y.model.Comment;
import test.y.model.Post;

import java.util.List;

/**
 * Created by yasitha on 4/17/17.
 */
public interface PostService {

    void create(Post post);

    List<Post> getAllPosts();

    List<Post> getPostByTitle(String title);

    void addComment(String postId, Comment comment);
}
