package net.ict.springex.domain;

// MyBatis와 스프링을 이용한 영속처리 4단계
/* 1. VO 선언
*  2. Mapper 인터페이스 개발
*  3. XML 이용하여 SQL 작성
*  4. 테스트 코드 개발
* */

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {

    private Long tno;
    private String title;
    private boolean finished;
    private String writer;
    private LocalDate dueDate;

}
