package main.service;

import main.model.Tag;
import main.model.api.response.TagResponse;
import main.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public TagResponse getTagResponse(String query) {
        Map<Tag, Integer> tag2count = new HashMap<>();
        Iterable<Tag> tagIterable = tagRepository.findAll();
        int maxWeight = -1;
        int summaryCount = 0;
        for(Tag tag : tagIterable) {
            tag2count.put(tag, tag.getPosts().size());
            Integer tagWeight = tag.getWeight();
            summaryCount++;
            if (tagWeight == null) {
                continue;
            }
            if (tagWeight > maxWeight) {
                maxWeight = tagWeight;
            }
        }

        List<Tag> tags = new ArrayList<>();
        for (Tag tag : tagIterable) {
            int weight = tag2count.get(tag) / summaryCount / Math.abs(maxWeight);
            tag.setWeight(weight);
            if (tag.getName().contains(query) || query.isBlank()) {
                tags.add(tag);
            }
        }
        return new TagResponse(tags);
    }
}
