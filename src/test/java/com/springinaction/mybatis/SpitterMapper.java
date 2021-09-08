package com.springinaction.mybatis;

import com.springinaction.common.Spitter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zjjfly on 2017/3/8.
 */
@Transactional
@Component("spitterMap")
public interface SpitterMapper {
    @Select("SELECT * FROM Spitter Where id= #{id}")
    Spitter getSpitter(@Param("id") Long id);

    @Select("SELECT * FROM Spitter")
    List<Spitter> getAllSpitter();
}
