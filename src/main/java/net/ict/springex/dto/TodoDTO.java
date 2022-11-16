package net.ict.springex.dto;

//객체 자료형은 파라미터로 처리하기 위해서는 객체로 생성되고 setxxxx()를 이용해서 처리

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    @NotEmpty
    private String title;

    private boolean finished;

    @NotEmpty
    private String writer;

    @Future   //todo는 미래시점이므로
    private LocalDate dueDate;
}
