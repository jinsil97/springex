package net.ict.springex.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

   @Bean    //빈 => root-context.xml 에 등록
    public ModelMapper getMapper(){
       ModelMapper modelMapper = new ModelMapper();
       modelMapper.getConfiguration()
               .setFieldMatchingEnabled(true)
               .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
               .setMatchingStrategy(MatchingStrategies.STRICT);

       return modelMapper;
   }
}
