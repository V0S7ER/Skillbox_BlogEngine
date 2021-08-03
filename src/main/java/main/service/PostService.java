package main.service;

import main.api.response.PostResponse;
import main.model.Post;
import main.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class PostService {

    private final static String POST_MODE_POPULAR = "popular";
    private final static String POST_MODE_BEST = "best";
    private final static String POST_MODE_EARLY = "early";

    private PostService() {

    }

    public static PostResponse getPosts(Integer offset, Integer limit, String mode, PostRepository postRepository) {
        int startOffset = 0;
        int startLimit = 0;
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 10 : limit;

        List<Post> postList = new ArrayList<>();
        Iterator<Post> postIterable = postRepository.findAll().iterator();
        while (postIterable.hasNext()) {
            if (startOffset < offset) {
                startOffset++;
                postIterable.next();
            } else if (startLimit < limit) {
                startLimit++;
                postList.add(postIterable.next());
            }
        }
        int count = 0;
        for (Post post : postRepository.findAll()) {
            count++;
        }
        Comparator<Post> comparator;
        switch (mode) {
            case POST_MODE_BEST:
                comparator = Comparator.comparing(o -> o.getVotedUsers().size());
                comparator = comparator.reversed();
                break;
            case POST_MODE_EARLY:
                comparator = Comparator.comparing(Post::getTime);
                break;
            case POST_MODE_POPULAR:
                comparator = Comparator.comparing(o -> o.getCommentedUsers().size());
                comparator = comparator.reversed();
                break;
            default:
                comparator = Comparator.comparing(Post::getTime);
                comparator = comparator.reversed();
                break;
        }
        postList.sort(comparator);
        return new PostResponse(count, postList);
    }
}