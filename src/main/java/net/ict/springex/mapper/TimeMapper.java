package net.ict.springex.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    @Select("select now()")   //Mapper Interface -> root-context.xml 에 등록
    String getTime();
}
