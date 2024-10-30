
@Service
public class PostService {

    /* Optional
     *      - 객체가 null인지 아닌지 확인할 때 사용
     *      - null 체크를 간단하게 처리할 수 있도록 도와주는 클래스
     * 
     * orElseThrow()    
     *      - 객체가 null인 경우 예외를 발생시키는 메소드
     */

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 스트림 사용 방식과 메소드
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .filter(post -> "y".equals(post.getStatus()))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 메소드 사용 방식
    public List<PostDto> getAllPosts2() {
        List<Post> posts = postRepository.findAll();
        
        List<PostDto> postDatas = new ArrayList<>();
        List<Post> filteredPosts = new ArrayList<>();
       
        for (Post post : posts) {
            if ("y".equals(post.getStatus())) {
                filteredPosts.add(post);
            }
        }
        posts = filteredPosts;

        for (Post post : posts) {
            postDatas.add(convertToDto(post));
        }
        return postDatas;
    }

    // 기초 방식
    public PostDto getAllPosts3(Post post) {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDatas = new ArrayList<>();
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : posts) {
            if ("y".equals(post.getStatus())) {
                filteredPosts.add(post);
            }
        }
        posts = filteredPosts;

        for (Post post : posts) {
            PostDto dto = new PostDto();
            dto.setPostId(post.getPostId());
            dto.setTitle(post.getTitle());
            dto.setContent(post.getContent());
            dto.setAuthor(post.getAuthor());
            dto.setCreateDate(post.getCreateDate());
            dto.setModifiedDate(post.getModifiedDate());
            dto.setViewCount(post.getViewCount());
            dto.setImage(post.getImage());
            dto.setComments(post.getComments());

            postDatas.add(dto);
        }
        return postDatas;
    }

    // 상세페이지
    public PostDto detailPostById(int postId) {

        // 1. 게시글 ID 확인
        // 프론트쪽에서 id를 정확히 보내준다고해도 예외처리를 해주는 것이 좋음.
        if(postId == null) {
            throw new IllegalArgumentException("게시글 ID가 필요합니다.");
        }

        // 2. 게시글 조회 + 예외처리
        // EntityNotFoundException -> 조회하는 경우 데이터가 없을 때 예외처리
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        
        // 3. 삭제된 게시글 체크 -> 삭제가 잘 되었다고 해도 예외처리 해주는 것이 명확성이 좋음
        if("N".equals(post.getStatus())) {
            throw new IllegalArgumentException("삭제된 게시글입니다.");
        }

        updateViewCount(post);

        return PostDto.convertToDto(post);
    }

    // 상세페이지 들어오면 조회수 증가
    public void updateViewCount(Post post) {
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);
    }

    //수정
    public PostDto editPostById(int postId) {

        if(postId == null) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId);
        }

        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. id=" + postId));
        return convertToDto(post);
    }

    // 삭제
    public void deletePostById(int postId) {

        // 1. 게시글 존재 여부 확인
        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + postId));

        // 2. 권한 체크
        // loginUserId == post.author과 같으면 삭제 가능해야함
        if(!post.getAuthor().equals(loginUserId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        // 3. 게시글 삭제
        post.setStatus("N");
        post.setModifiedDate(LocalDateTime.now());
        postRepository.save(post);
        
        return postId;
    }

    // 작성
    public void writePost(PostDto postDto) {
        int writeResult =postRepository.save(postDto.toEntity());

        if(writeResult == 0) {
            throw new IllegalArgumentException("게시글 작성이 실패하였습니다.");
        }
        return writeResult;
    }

    
}
