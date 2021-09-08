package com.springinaction.web;

import com.springinaction.common.Error;
import com.springinaction.common.Spittle;
import com.springinaction.common.SpittleNotFoundException;
import com.springinaction.data.JpaSpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Created by zjjfly on 2017/2/26.
 */
@RestController
@RequestMapping("/spittle")
public class SpittleAPIController {

    private final JpaSpittleRepository jpaSpittleRepository;

    private final static String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";

    @Autowired
    public SpittleAPIController(JpaSpittleRepository jpaSpittleRepository) {
        this.jpaSpittleRepository = jpaSpittleRepository;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max, @RequestParam(value = "count", defaultValue = "30") int count) {
        return jpaSpittleRepository.findAll(new PageRequest(0, count)).getContent();
    }

    //使用ResponseEntity作为返回值，它包含了@ResponseBody的语义，所以不用加这个注解了
    //这个处理方法的缺点是，没有返回体，只有状态码
//    @RequestMapping(value = "/{spittleId}", method = GET)
//    public ResponseEntity<Spittle> spittle(@PathVariable long spittleId) {
//        Spittle spittle = jpaSpittleRepository.findOne(spittleId);
//        HttpStatus status = null != spittle ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//        return new ResponseEntity<Spittle>(spittle, status);
//    }

    //使用自定义错误类型，这样就有返回体了
    //这个方法的缺点是太复杂，而且使用<?>这样泛型也不合适
//    @RequestMapping(value = "/{spittleId}", method = GET)
//    public ResponseEntity<?> spittle(@PathVariable long spittleId) {
//        Spittle spittle = jpaSpittleRepository.findOne(spittleId);
//        if (spittle == null) {
//            return new ResponseEntity<Error>(new Error(4,"Spittle["+spittleId+"] not found!"),HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
//    }

    //使用控制器中的错误处理器处理错误，所以可以不使用ResponseEntity，直接使用Spittle作为返回值
    @GetMapping(value = "/{spittleId}")
    public Spittle spittle(@PathVariable long spittleId) {
        Spittle spittle = jpaSpittleRepository.findOne(spittleId);
        if (spittle == null) {
            throw new SpittleNotFoundException(spittleId);
        }
        return spittle;
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error spittleNotFound(SpittleNotFoundException e) {
        long spittleId = e.getSpittleId();
        return new Error(4, "Spittle[" + spittleId + "] Not Found!");
    }

    //有一种情况是必须用ResponseEntity的，那就是需要在返回的Header中加东西
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Spittle> spittles(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
        spittle.setTime(new Date());
        Spittle save = jpaSpittleRepository.save(spittle);
        HttpHeaders httpHeaders = new HttpHeaders();
        //使用这样URI生成方法不好，因为把host的ip和端口硬编码进去了,所以使用UriComponentsBuilder更加优雅
//        URI uri = URI.create("http://localhost:8080/spittle/" + save.getId());
        URI uri = ucb.path("/spittle/")
                .path(String.valueOf(save.getId()))
                .build().toUri();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<Spittle>(save, httpHeaders, HttpStatus.CREATED);
    }
}
