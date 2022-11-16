package net.ict.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor    //생성자 주입 시 필요
public class TodoController {

    private final TodoService todoService;   //insert 구동 메소드 보유

/*    @RequestMapping("/list")      // 최종경로 /todo/list
    public void list(Model model) {

        log.info("todo list..................");
        model.addAttribute("dtoList",todoService.getAll());   //model에는 'dtoList'의 이름으로 목록데이터가 담김

    }*/

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
        log.info("todo list..................");
        log.info(pageRequestDTO);
        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
        //model 에는 'dtoList' 이름으로 목록데이터 담겨있음
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)     //get방식
    public void register(){
        log.info("todo register....................");
    }


    // '/todo/register' 를 post방식으로 처리하는 메소드 파라미터로 TodoDTO 적용

    @PostMapping("/register")     //post방식
                              //@Valid : 검사대상 
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){   //자동 형변환 지원
        log.info("Post todo register.............");

        if(bindingResult.hasErrors()){    //예를 들어 과거 날짜로 하면 에러뜸 - @future 때문
            log.error("has errors.......");
            //DTO 검증
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());    //한번 수행하고 지워지는 값
            return "redirect:/todo/register";
        }

        log.info(todoDTO);
        todoService.register(todoDTO);     //DB에 insert
        return "redirect:/todo/list";
    }    //post방식으로 받으려는 객체는 DTO 형식으로 먼저 만들어두고 그 뒤에 받으면 됨

    @GetMapping({"/read","/modify"})
    public void read(Long tno, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto",todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){
        log.info("--------remove---------");
        log.info("tno : " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            log.info("has errors..........");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno",todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);
        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }







}
