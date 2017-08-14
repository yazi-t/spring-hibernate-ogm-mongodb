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
 * <p>This application demonstrates usage of hibernate OGM MongoDB provider
 * with spring MVC. Application is a sample Blog web site which uses MongoDB
 * as persistence store. This demo uses standard JPA annotations and methods to
 * query data store.</p>
 *
 * <p>This class rebuilds full text indexes of test.y.model.Post model in the
 * time of application starting up.</p>
 *
 * @author Yasitha Thilakaratne
 * Date: 04/25/2017
 *
 */
@Component
public class SearchIndexBuilder {

    /**
     * Set this variable true to rebuild indexes asynchronously.
     * Otherwise method call will wait until index rebuild to be finished
     * to return.
     */
    private static final boolean BUILD_ASYNCHRONOUSLY = false;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        buildIndex(Post.class);
    }

    /**
     * @param clz list of model classes to rebuild indexes.
     */
    public void buildIndex(Class... clz) {
        consoleLog("Index building started");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {
            if (BUILD_ASYNCHRONOUSLY) {
                fullTextEntityManager.createIndexer(clz).start();
            } else {
                fullTextEntityManager.createIndexer(clz).startAndWait();
            }
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
