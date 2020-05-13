package com.jojoldu.book.domain.posts;

import com.jojoldu.book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import  javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내의 모든 필드의 Getter 메소드 생성
@NoArgsConstructor // 기본 생성자 생성, public Posts(){} 와 같은 효과
@Entity //테이블과 링크될 클래스 임을 나타냄
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙 :: Identity 를 해야 자동증가 됨
    //웬만하면 PK는 Long타입의 자동증가 추천
    private Long id;

    @Column(length = 500, nullable = false) //해당 데이블의 컬럼 명시 , 굳이 선언 안해도 컬럼 으로 인식 쓰는 이유는 컬럼의 사이즈등 지정하기 위해
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성 , 생성자 상단에 선언 시 생성자에 포함된 플드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
