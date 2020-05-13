package com.jojoldu.book.web;
import com.jojoldu.book.service.posts.PostsService;
import com.jojoldu.book.web.dto.PostsResponseDto;
import com.jojoldu.book.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//원래는 @Autowired 로 하던걸 dto에 있는거 불러온다던가 서비스 불러온다던가
// 이런걸 아래 롬복의 RequiredArgsConstructor가 final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신생성해준것
@RequiredArgsConstructor
@RestController //컨트롤러 인거
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findBy(@PathVariable Long id){
        return postsService.findBy(id);
    }
}
