package main.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "moderation_status", nullable = false, columnDefinition = "enum ('NEW', 'ACCEPTED', 'DECLINED')")
    private ModerationStatus moderationStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "moderator_id", nullable = false)
    private User moderator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Date time;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String text;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostVote> postVoteList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostComment> postCommentList;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "votedPosts")
    private List<User> votedUsers;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "commentedPosts")
    private List<User> commentedUsers;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "posts")
    private List<Tag> tags;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Tag2Post> tag2PostList;

    @JsonGetter
    @JsonProperty("voted_count")
    public int getVotedCount() {
        return votedUsers.size();
    }

    @JsonGetter
    @JsonProperty("comment_count")
    public int getCommentCount() {
        return votedUsers.size();
    }
}
