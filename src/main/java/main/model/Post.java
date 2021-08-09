package main.model;

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

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostVote> postVoteList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostComment> postCommentList;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "votedPosts")
    private List<User> votedUsers;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "commentedPosts")
    private List<User> commentedUsers;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "posts")
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Tag2Post> tag2PostList;

    @Enumerated(EnumType.STRING)
    public ModerationStatus getModerationStatus() {
        return moderationStatus;
    }
}
