package test.y.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import test.y.PostService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *
 * Created by yasitha on 5/15/17.
 */
@Controller
public class RestController extends ControllerBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PostDTO> json() {
        try {
            List<PostDTO> serializableList = new ArrayList<>();
            postService.getAllPosts().forEach(p ->
                    serializableList.add(new PostDTO(p.getId(), p.getTitle(), p.getDescription(), p.getImgUrl())));
            return serializableList;
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred ", e);
            return Collections.emptyList();
        }
    }

    public static class PostDTO {
        public String id;
        public String title;
        public String description;
        public String imgUrl;

        public PostDTO(String id, String title, String description, String imgUrl) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.imgUrl = imgUrl;
        }
    }
}
