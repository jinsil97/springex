package net.ict.springex.dto;

/*
 목록 데이터를 위한 DTO
 : 목록 화면에서 필요한 데이터를 하나의 DTO 객체로 만들어서 사용
   => 재사용 가능
 - TodoDTO의 목록
 - 전체 데이터의 수
 - 페이지 번호의 처리를 위한 데이터들 (시작 페이지 번호/ 끝 페이지 번호)  */

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {   //<E>재너릭 -> 다른 종류의 객체를 이용하여 PageResponseDTO를 구성할 수 있음
                                    // ex) 회원 정보 계시판이나 공지사항도 페이징 처리가 필요하므로 공통처리를 위해 사용
    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;

    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;

    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        //마지막 페이지 (end)를 구하는 계산식 end는 현재의 페이지 번호를 기준으로 계산
        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;

        //마지막 페이지를 먼저 계산하는 이유 : 시작 페이지 계산 용이
        this.start = this.end - 9;

        //시작 페이지를 구성한 후 만지막 페이지는 다시 전체개수(total)을 고려하여
        //만약 10개씩 size를 보여주는 경우라면 전체 개수(total)을 구하여 last를 구해야함
        //ex) total=75 -> last = 75/10.0 = 7.5 => 8
        int last = (int)(Math.ceil((total/(double)size)));

        this.end = end > last ? last: end;

        //이전페이지(prev) 존재 여부 -> 시작 페이지가 1이 아니라면 무조건 true
        this.prev = this.start > 1;

        //다음페이지(next)는 end와 size를 곱한 값보다 total이 더 많은지 체크하여 판단단
       this.next = total > this.end * this.size;
    }
}
