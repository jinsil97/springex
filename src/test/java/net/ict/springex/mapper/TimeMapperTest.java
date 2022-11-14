package net.ict.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")


public class TimeMapperTest {

    @Autowired(required = false)   //null값이어도 익셉션 발생하지 않도록
    private TimeMapper timeMapper;

    @Autowired(required = false)
    private TimeMapper2 timeMapper2;

    @Test
    public void testgetTime(){
        log.info(timeMapper.getTime());
    }

    @Test
    public void testgetNow(){
        log.info(timeMapper2.getNow());
    }

}
