

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {

    /* Entity와 DTO
     *      - Entity : 데이터베이스와 매핑되는 클래스
     *      - DTO : 계층간 데이터 교환을 위한 객체
     *      - 주요 차이점
     *          - DTO는 화면에 표시되는 데이터만 포함
     *          - Entity는 데이터베이스와 직접 매핑되는 객체
     * 
     * Entity를 DTO로 변환하는 메서드와 DTO를 Entity로 변환하는 메서드가 필요하다.
     * 
     * Entity -> DTO 변환 메서드
     *      - Entity 객체의 데이터를 읽어서 DTO 객체에 채워 넣는다.
     *      - 보통 데이터 전달 시 사용하는 DTO는 프레젠테이션 계층과 밀접하게 연관되므로, 필요한 필드만 선택해 DTO에 저장한다.
     * 
     * DTO -> Entity 변환 메서드
     *      - 요청으로 전달받은 DTO 데이터를 바탕으로 새로운 Entity 객체를 생성하거나, 기존 Entity 객체를 수정한다.
     *      - 서비스 계층에서 처리한 후, 이 Entity 객체는 데이터베이스에 저장하거나 다른 비즈니스 로직에 활용한다.
     * 
    */
    
    public int postId;
    public String title;
    public String content;

    public String author;

    public Date createDate;

    public Date modifiedDate;

    public int viewCount;
    public String image;

    public String status;

    public List<Comment> comments;

    private static PostDto convertToDto(Post post) {
        return PostDto.builder()
            .postId(post.getPostId())
            .title(post.getTitle())
            .content(post.getContent())
            .author(post.getAuthor())
            .createDate(post.getCreateDate())
            .modifiedDate(post.getModifiedDate())
            .viewCount(post.getViewCount())
            .image(post.getImage())
            .status(post.getStatus())
            .comments(post.getComments())
            .build();
    }

}
