package com.toyproject.hello.dev.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    /* @RequestParma과 @PathVariable 차이
     * @RequestParam : URL에서 클라이언트가 전송하는 파라미터를 받아오는 어노테이션
     * @PathVariable : URL에서 경로 변수를 받아오는 어노테이션
     * 
     * @RequestParam
     *      - 쿼리 파라미터를 통해 추가적인 정보를 전달할 때
     *      - 주로 GET 요청에서 사용
     *      - 예시 : /api/items?id=123&name=John
     * 
     * @PathVariable
     *      - URL 경로에서 변수를 추출하는 데 사용
     *      - 주로 RESTful API에서 사용
     *      - 예시 : /api/items/123
     */

    @Autowired
    private PostService postService;

    // Model 객체는 컨트롤러와 뷰 사이에서 데이터를 전달하는 데 사용된다.
    // Post 게시판 All List 호출
    @GetMapping("/")
    public String index(Model model) {
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    // 상세페이지 호출
    // 상세페이지
    @GetMapping("/post/detail/{postId}")
    public String detail(@PathVariable("postId") int postId, Model model) {
        PostDto post = postService.detailPostById(postId);
        model.addAttribute("post", post);
        return "post/detail";
    }

    // 수정
    // @ModelAttribute("post") : 모델 객체에 자동으로 추가
    @PostMapping("/post/edit/{postId}")
    public String edit(@PathVariable("postId") int postId, @ModelAttribute("post") PostDto postDto, Model model) {
        
        try {
            // postId 설정
            postDto.setPostId(postId);

            PostDto post = postService.editPostById(postDto);
    
            return "redirect:/post/detail" + postId;
        } catch (EntityNotFoundException e) {
            return "error/404";
        } catch (Exception e) {
            return "error/500";
        }
    }

    // 삭제
    @PostMapping("/post/delete/{postId}")
    public String delete(@PathVariable("postId") int postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }


    // 작성
    @PostMapping("/post/write")
    public String write(PostDto postDto) {
        postService.writePost(postDto);
        return "redirect:/";
    }



    @GetMapping("/test")
    public String test() {
        System.out.println("test...");
        return "index";
    }

}
