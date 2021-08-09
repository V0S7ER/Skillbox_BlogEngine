package main.model.api.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.Tag;

import java.util.List;

@AllArgsConstructor
@Data
public class TagResponse {

    private List<Tag> tags;
}
