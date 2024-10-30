package com.toyproject.hello.dev.post.entity;

import com.toyproject.hello.dev.comment.entity.Comment;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    public int postId;

    @Column(nullable = false, length = 50)
    public String title;

    @Column(nullable = false, length = 300)
    public String content;

    @Column(nullable = false)
    public String author;

    @Column(name = "create_date")
    public Date createDate;

    @Column(name = "modified_date")
    public Date modifiedDate;

    @Column(name = "view_count")
    public int viewCount;

    @Column(length = 300)
    public String image;

    // status 추가해야함
    @Column(nullable = false)
    public String status;

    // 댓글과의 일대다 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Comment> comments;

    public void updateViewCount(Post post) {
        this.viewCount += 1;
    }

    // DTO 변환 메서드
    public static Post convertToEntity(PostDto postDto) {
        return Post.builder()
            .postId(postDto.getPostId())
            .title(postDto.getTitle())
            .content(postDto.getContent())
            .author(postDto.getAuthor())
            .createDate(postDto.getCreateDate())
            .modifiedDate(postDto.getModifiedDate())
            .viewCount(postDto.getViewCount())
            .image(postDto.getImage())
            .status(postDto.getStatus())
            .comments(postDto.getComments())
            .build();
    }
}
