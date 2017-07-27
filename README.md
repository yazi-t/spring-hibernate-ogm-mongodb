# spring-hibernate-ogm-mongodb
Hibernate OGM mongodb project with Spring MVC integration

This sample project (blog) has been developed with hibernate OGM Mongodb provider and Spring MVC framework.
It uses hibernate search for full text search.

## Installing
1. Build project using mvn clean install.
2. Run inside Java EE or servlet container.

## Deployment
* JDK Version: Java 8 is required as mandetory for hibernate OGM version used.
* Server: Apache Tomcat has been used for testing
* Build tool: Maven

## Authors
* Yasitha Thilakaratne (Initial Author)

## Screens
### Blog UI Post
![Blog UI](/screens/screen01_ui.png?raw=true "Blog UI")

### Blog UI View Comments
![Blog UI](/screens/screen02_ui.png?raw=true "Blog UI")

### Mongo CLI
![Mongo CLI](/screens/screen03_mongo.png?raw=true "Mongo CLI")

## Code
### Entity
```
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
    //getters and setters
    }
```
## persistance.xml
```
<?xml version="1.0"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">

    <persistence-unit name="ogm-jpa-tutorial" transaction-type="RESOURCE_LOCAL">
        <!-- Use Hibernate OGM provider: configuration will be transparent -->
        <provider>org.hibernate.ogm.jpa.HibernateOgmPersistence</provider>

        <class>test.y.model.Post</class>

        <properties>
            <property name="hibernate.ogm.datastore.provider" value="mongodb" />
            <!--<property name="hibernate.transaction.jta.platform" value="org.hibernate.service.jta.platform.internal.JBossStandAloneJtaPlatform" />-->
            <property name="hibernate.ogm.datastore.create_database" value="true"/>
            <property name="hibernate.ogm.datastore.database" value="blog_db"/>
            <property name="hibernate.search.default.directory_provider" value="filesystem"/>

            <property name="hibernate.search.default.indexBase" value="/home/yasitha/lucene/indexes"/>
        </properties>
    </persistence-unit>
</persistence>
```
### spring-config.xml
```
    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalEntityManagerFactoryBean">
        <property name="persistenceUnitName" value="ogm-jpa-tutorial" />
    </bean>

    <bean class="org.springframework.orm.jpa.JpaTransactionManager" id="transactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory" />
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager"/>

    <bean class="org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor" />
```
