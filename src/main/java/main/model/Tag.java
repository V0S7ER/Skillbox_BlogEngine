package main.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tags")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private Integer weight;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tag2post",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")})
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
    private List<Tag2Post> tag2PostList;
}
