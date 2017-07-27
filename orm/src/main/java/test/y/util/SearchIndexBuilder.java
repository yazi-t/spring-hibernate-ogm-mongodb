package test.y.util;

import org.hibernate.CacheMode;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Component;
import test.y.model.Post;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * <p>
 * Hibernate OGM
 * <p/>
 *
 * Created by yasitha on 4/21/17.
 */
@Component
public class SearchIndexBuilder {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        buildIndex(Post.class);
    }

    public void buildIndex(Class... clz) {
        consoleLog("Index building started");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {
            fullTextEntityManager.createIndexer(clz).startAndWait();

            //Asynchronous way
            //fullTextEntityManager.createIndexer(clz).start();
        } catch (Exception e) {
            consoleLog("ATTENTION!!! Index building was interrupted." + e.toString());
        }
        consoleLog("Index building ended");
    }

    private static void consoleLog(String msg) {
        for (int i = 0; i < 10; i++)
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("---------------->" + msg);
        for (int i = 0; i < 10; i++)
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
