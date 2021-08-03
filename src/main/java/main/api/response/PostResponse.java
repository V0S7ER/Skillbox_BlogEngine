package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.Post;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {
    private int count;

    private List<Post> posts;
}
