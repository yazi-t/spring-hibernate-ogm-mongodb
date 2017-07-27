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
 * Created by yasitha on 4/17/17.
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
