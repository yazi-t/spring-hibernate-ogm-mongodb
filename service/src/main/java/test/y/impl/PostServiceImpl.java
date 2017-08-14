package test.y.impl;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.y.PostService;
import test.y.dao.PostDao;
import test.y.model.Comment;
import test.y.model.Post;

import java.util.List;

/**
 * <p>This application demonstrates usage of hibernate OGM MongoDB provider
 * with spring MVC. Application is a sample Blog web site which uses MongoDB
 * as persistence store. This demo uses standard JPA annotations and methods to
 * query data store.</p>
 *
 * <p>Service that handles blog post based business logic.</p>
 *
 * @author Yasitha Thilakaratne
 * Date: 04/25/2017
 *
 */
@Transactional
@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    public void create(Post post) {
        postDao.persist(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public List<Post> getPostByTitle(String title) {
        return postDao.getPostByTitle(title);
    }

    @Override
    public void addComment(String postId, Comment comment) {
        postDao.addComment(postId, comment);
    }
}
