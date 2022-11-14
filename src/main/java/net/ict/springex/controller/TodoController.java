package net.ict.springex.controller;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    @RequestMapping("/list")      // 최종경로 /todo/list
    public void list(Model model) {
        log.info("todo list..................");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)     //get방식
    public void register(){
        log.info("todo register....................");
    }


    // '/todo/register' 를 post방식으로 처리하는 메소드 파라미터로 TodoDTO 적용

    @PostMapping("/register")      //post방식
    public String registerPost(TodoDTO todoDTO, RedirectAttributes redirectAttributes){   //자동 형변환 지원
        log.info("Post todo register.............");
        log.info(todoDTO);
        return "redirect:/todo/list";
    }    //post방식으로 받으려는 객체는 DTO 형식으로 먼저 만들어두고 그 뒤에 받으면 됨



}
