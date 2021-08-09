package main.service;

import main.model.Post;
import main.model.api.response.PostResponse;
import main.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostResponse getPosts(Integer offset, Integer limit, String mode) {
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
        switch (PostMode.valueOf(mode.trim())) {
            case BEST:
                comparator = Comparator.comparing(o -> o.getVotedUsers().size());
                comparator = comparator.reversed();
                break;
            case EARLY:
                comparator = Comparator.comparing(Post::getTime);
                break;
            case POPULAR:
                comparator = Comparator.comparing(o -> o.getCommentedUsers().size());
                comparator = comparator.reversed();
                break;
            case RECENT:
                comparator = Comparator.comparing(Post::getTime);
                comparator = comparator.reversed();
                break;
            default:
                comparator = (o1, o2) -> 0;
                break;
        }
        postList.sort(comparator);
        return new PostResponse(count, postList);
    }
}