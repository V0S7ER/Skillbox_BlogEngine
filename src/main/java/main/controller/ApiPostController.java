package main.controller;

import main.api.response.PostResponse;
import main.repository.PostRepository;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiPostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/post")
    private synchronized ResponseEntity<PostResponse> post(Integer offset, Integer limit, String mode) {
        return new ResponseEntity<>(PostService.getPosts(offset, limit, mode, postRepository), HttpStatus.OK);
    }
}
