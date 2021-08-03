package main.service;

import main.api.response.TagResponse;
import main.model.Tag;
import main.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagService {

    private TagService() {

    }

    public static TagResponse getTagResponse(String query, TagRepository tagRepository) {
        Map<Tag, Integer> tag2count = new HashMap<>();
        Iterable<Tag> tagIterable = tagRepository.findAll();
        int maxWeight = -1;
        int summaryCount = 0;
        for(Tag tag : tagIterable) {
            tag2count.put(tag, tag.getPosts().size());
            Integer tagWeight = tag.getWeight();
            if (tagWeight == null) {
                continue;
            }
            if (tagWeight > maxWeight) {
                maxWeight = tagWeight;
            };
            summaryCount++;
        }

        List<Tag> tags = new ArrayList<>();
        for (Tag tag : tagIterable) {
            tag.setWeight(tag2count.get(tag) / summaryCount * (1 / Math.abs(maxWeight)));
            if (tag.getName().contains(query) || query.isBlank()) {
                tags.add(tag);
            }
        }
        return new TagResponse(tags);
    }
}
