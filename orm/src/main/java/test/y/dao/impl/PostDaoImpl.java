package test.y.dao.impl;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.y.dao.PostDao;
import test.y.model.Comment;
import test.y.model.Post;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by yasitha on 4/17/17.
 */
@Transactional
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Repository("postDao")
public class PostDaoImpl implements PostDao {

    @PersistenceContext
    private EntityManager em;

    TransactionManager tm;

    EntityManagerFactory emf;

    @PostConstruct
    private void init() {
        /*tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
        //accessing JBoss's Transaction can be done differently but this one works nicely

        //build the EntityManagerFactory as you would build in in Hibernate ORM
        emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");*/
    }

    @Override
    public void persist(Post post) {
        em.persist(post);
    }

    @Override
    public List<Post> getAllPosts() {
        String nativeQuery = "{ $query :{}}";
        Query query = em.createNativeQuery(nativeQuery, Post.class);

        @SuppressWarnings("unchecked")
        List<Post> posts = query.getResultList();
        return posts;
    }

    @Override
    public List<Post> getPostByTitle(String title) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Post.class).get();

        org.apache.lucene.search.Query fullTextQuery = qb
                .keyword()
                .onFields("title")
                .matching(title)
                .createQuery();

        Query query = fullTextEntityManager.createFullTextQuery(fullTextQuery, Post.class);

        @SuppressWarnings("unchecked")
        List<Post> posts = query.getResultList();
        return posts;
    }

    @Override
    public void addComment(String postId, Comment comment) {
        Query query = em.createQuery("FROM Post WHERE id = :id");

        query.setParameter("id", postId);

        Post post = (Post) query.getSingleResult();

        post.getComments().add(comment);

        em.merge(post);
    }
}
