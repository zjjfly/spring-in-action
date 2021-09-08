package com.springinaction.data;

import com.springinaction.common.DuplicateSpittleException;
import com.springinaction.common.Spittle;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Created by zjjfly on 2016/12/28.
 */
@Transactional
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    //unless只能阻止将对象放入缓存，但在方法调用的时候还是会去缓存中查找；而condition会把缓存功能禁用，包括存入和查找
    @Cacheable(value = "spittleCache", unless = "#result.message.contains('NoCache')", condition = "#id >= 10")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SPITTER"})
//    @PostAuthorize("returnObject.spitter.username == principal.username")
    Spittle findOne(long id);

    @CachePut(value = "spittleCache", key = "#result.id")
    @Secured({"ROLE_ADMIN","ROLE_SPITTER"})
//    @PreAuthorize("(hasAnyRole('ROLE_SPITTER') and #spittle.message.length() <= 140) or hasRole('ROLE_PREMIUM')")
    @PreAuthorize("hasPermission(#spittle,'add')")//使用自定义的许可计数器，效果和上面两行代码一样
    Spittle save(Spittle spittle) throws DuplicateSpittleException;

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SPITTER')")
//    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.spitter.username == principal.username")
//    principal是另一个Spring Security内置的对象，它代表了当前认证用户的主要信息(包括用户名)
//    filterObject对象引用的是这个方法所返回的`List`中的某个元素
//    List<Spittle> getOffensiveSpittles();

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SPITTER')")
//    @PreFilter("hasRole('ROLE_ADMIN') or filterObject.spitter.username == principal.username"
//    filterObject对象引用的是这个方法传入的`List`中的某个元素
//    void deleteSpittles(List<Spittle> spittles);

}
