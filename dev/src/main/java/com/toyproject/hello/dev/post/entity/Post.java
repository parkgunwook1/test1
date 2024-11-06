package com.toyproject.hello.dev.post.entity;

import com.toyproject.hello.dev.comment.entity.Comment;
import com.toyproject.hello.dev.post.dto.PostDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@Builder
public class Post {

    public Post() {

    }

    // Table 수정해야함 Image 따로 테이블 생성했음, Image 테이블 컬러명 수정
    public Post(int postId, String title, String content, String author, Date createDate, Date modifiedDate, int viewCount, String status) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.viewCount = viewCount;
        this.status = status;
    }

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

    @Column(name = "status")
    public String status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    public List<Image> images;


    // 댓글과의 일대다 관계
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    public List<Comment> comments;

    // DTO 변환 메서드
    public static Post ToPostEntity(PostDto postDto) {
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
//                .comments(postDto.getComments())
                .build();
    }
}
