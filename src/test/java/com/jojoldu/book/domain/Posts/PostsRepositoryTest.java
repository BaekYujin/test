package com.jojoldu.book.domain.Posts;

import com.jojoldu.book.domain.posts.Posts;
import com.jojoldu.book.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴, 여기서는 SpringRunner라는 스프링 실행자 사용
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired //스프링이 관리하는 빈을 주입받는다.
    PostsRepository postsRepository;

    @After //JUnit에서 단위 테스트가 끝날 때마다 수행, 보통 배포전 전체 테스트 시 테스트간 데이터 침범을 막기위해 사용
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트게시글";
        String content = "테스트본문";

        //위의 제목이랑 내용 저장 후 전체 리스트 불러오고 불러왔을때 위의 내용이 같게 잘 들어갔는지 테스트

        postsRepository.save(Posts.builder()  //save : 테이블 posts에 insert/update 쿼리 실행 , id값이 있다면 update, 없으면 insert
                            .title(title)
                            .content(content)
                            .author("byujin66@gmail.com")
                            .build()
                            );
        //when
        List<Posts> postsList = postsRepository.findAll(); // findAll : 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
