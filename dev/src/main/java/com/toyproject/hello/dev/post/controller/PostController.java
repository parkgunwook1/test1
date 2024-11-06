package com.toyproject.hello.dev.post.controller;

import com.toyproject.hello.dev.post.dto.PostDto;
import com.toyproject.hello.dev.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {

    /* TODO LIST
    *
    * - 게시글 이미지
    * - ui 깨지는 거 수정, ui 편집
    * - 상세보기 -> 수정, 삭제
    * - 헤더, 푸터
    * - 게시글에 이미지
    * - 로그백 설정
    * */

    // 

    @Autowired
    private PostService postService;

    // 상세페이지
    @GetMapping("post/detail/{postId}")
    public String detailPost(@PathVariable("postId") int postId, Model model) throws IllegalAccessException {

        PostDto post = postService.detailPostById(postId);
        model.addAttribute("post",post);
        return "post/detail";
    }

    // 게시글 수정
    @PostMapping("/post/edit/{postId}")
    public String editPost(@PathVariable("postId") int postId,
                           @ModelAttribute("post") PostDto postDto, Model model) throws IllegalAccessException {

        postDto.setPostId(postId);

        PostDto post = postService.editPostById(postDto);
        return "redirect:/post/detail" + postId;
    }

    // 게시글 삭제
    @PostMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable("postId") int postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }

    // 게시글 작성
    @PostMapping("/post/write")
    public String write(
            @RequestParam("image") MultipartFile image, // 파일로 받기
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        // 게시글 작성은 하나의 트랜잭션으로 처리되어야함.
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);
        // login 기능 구현하면 세션스코프에 담아서 getUserName 으로 받아오기
        postDto.setAuthor("test");
        
        PostDto post = postService.writePost(postDto, image);

        // 글쓰기 후 상세보기 페이지로 리다이렉트
        return "redirect:/post/detail/" + post.getPostId();
    }
}


/* @RequestParam 과 @PathVariable 차이
 *
 * @RequestParam : URL 에서 클라이언트가 전송하는 파라미터를 받아오는 어노테이션
 * @PathVariable : URL 에서 경로 변수를 받아오는 어노테이션
 *
 * @RequestParam
 *       - 쿼리 파라미터를 통해 추가적인 정보를 전달할 때
 *       - 주로 GET 요청에서 사용
 *       - 예시 : /api/items?id=123&name=John
 *
 * @PathVariable
 *       - URL 경로에서 변수를 추출하는 데 사용
 *       - 주로 RESTFUL API 에서 사용
 *       - 예시 : /api/items/123
 *
 * Model 객체는 컨트롤러와 뷰 사이에서 데이터를 전달하는 데 사용된다.
 * */
