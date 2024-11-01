package com.toyproject.hello.dev;

import com.toyproject.hello.dev.post.dto.PostDto;
import com.toyproject.hello.dev.post.entity.Post;
import com.toyproject.hello.dev.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor // lombok 제공 : final 붙은 속성 생성자 자동으로 만들어준다.
public class HomeController {

    @Autowired
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {
       List<PostDto> posts = postService.getPostList();
       model.addAttribute("posts" , posts);
        return "index";
    }
}
