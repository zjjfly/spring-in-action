package com.springinaction.data;

import com.springinaction.common.Spittle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zjjfly on 2017/2/11.
 */
public interface JpaSpittleRepository extends JpaRepository<Spittle,Long>,SpittleWepper{

}
