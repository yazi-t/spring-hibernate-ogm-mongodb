package test.y.controller;

import javafx.geometry.Pos;
import org.hibernate.search.exception.EmptyQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.y.PostService;
import test.y.model.Comment;
import test.y.model.Post;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>This application demonstrates usage of hibernate OGM MongoDB provider
 * with spring MVC. Application is a sample Blog web site which uses MongoDB
 * as persistence store. This demo uses standard JPA annotations and methods to
 * query data store.</p>
 *
 * <p>Controller that serves all blog web page related requests.</p>
 *
 * @author Yasitha Thilakaratne
 * Date: 04/25/2017
 *
 */
@Controller
public class SampleController extends ControllerBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private PostService postService;

    @Value("${upload.dir}")
    private String uploadDir;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap modelMap) {
        try {
            List<Post> posts = postService.getAllPosts();
            modelMap.put("posts", posts);
            modelMap.put("uploadDir", "uploadDir");

            return "home";
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred ", e);
            return INTERNAL_ERROR;
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String getAddPage() {
        try {
            return "addPost";
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred ", e);
            return INTERNAL_ERROR;
        }
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute Post post,
                           @RequestParam(value = "image", required = false) MultipartFile file,
                           HttpSession httpSession) {
        try {
            if (file != null && !file.isEmpty() && (file.getContentType()).startsWith("image")) {
                byte[] bytes = file.getBytes();

                File dir = new File(uploadDir + File.separator + "hib-ogm");
                if (!dir.exists()) {
                    if (dir.mkdirs()) {
                        LOGGER.info("Directory created");
                    } else {
                        LOGGER.error("Directory was not found and error while  trying to create.");
                    }
                }

                String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'), file.getOriginalFilename().length());
                String fileName = httpSession.getId() + "_" + System.currentTimeMillis() + extension;
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(bytes);
                }
                post.setImgUrl(fileName);
            }

            LOGGER.debug("Post object received title: {}, description: {}", post.getTitle(), post.getDescription());

            postService.create(post);
            return "redirect:/";
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred ", e);
            return INTERNAL_ERROR;
        }
    }

    @RequestMapping(value = "save-comment", method = RequestMethod.POST)
    public String saveComment(@RequestParam("postId") String postId,
                              @RequestParam("email") String email,
                              @RequestParam("comment") String commentText) {
        try {
            Comment comment = new Comment(email, new Date(), commentText);

            postService.addComment(postId, comment);

            return "redirect:/";
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred ", e);
            return INTERNAL_ERROR;
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("phrase") String phrase, ModelMap modelMap) {
        try {
            List<Post> posts = postService.getPostByTitle(phrase);

            LOGGER.debug("****** {} of posts found for phrase: {}", posts.size(), phrase);
            posts.forEach(p -> LOGGER.debug("++++++ post found id: {}, title: {}", p.getId(), p.getTitle()));

            modelMap.addAttribute("posts", posts);
            modelMap.put("uploadDir", "uploadDir");
            return "search";
        } catch (EmptyQueryException e) {
            modelMap.addAttribute("posts", Collections.emptyList());
            return "search";
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred ", e);
            return INTERNAL_ERROR;
        }
    }
}
