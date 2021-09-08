package com.springinaction.ch16;

import com.springinaction.common.AbstractTest;
import com.springinaction.common.Spittle;
import com.springinaction.common.Users;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by zjjfly on 2017/2/27.
 */
public class RestTest extends AbstractTest{
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }

    @Test
    public void getObject() throws Exception {
        Users forObject = restTemplate.getForObject("https://api.douban.com/v2/user?q={name}", Users.class,"zjjblue");
        System.out.println(forObject);
    }

    @Test
    public void getEntity() throws Exception {
        ResponseEntity<Users> forEntity = restTemplate.getForEntity("https://api.douban.com/v2/user?q={name}", Users.class, "zjjblue");
        assert (forEntity.getStatusCode() == HttpStatus.OK);//获取状态码
        assert (new MediaType(MediaType.APPLICATION_JSON, Charset.defaultCharset()).equals(forEntity.getHeaders().getContentType()));
        //get和getFirst方法，更通用的HTTP头信息访问方法
        System.out.println(forEntity.getHeaders().get("Cache-Control"));//get方法，传入key，返回一个value的String列表
        System.out.println(forEntity.getHeaders().getFirst("Cache-Control"));//getFirst方法，传入key，返回一个String
        System.out.println(forEntity.getBody());
    }

    @Test
    public void post() throws Exception {
        Spittle cccc = new Spittle("cccc", new Date());
        //postForObject和postForEntity和geFortObject、getForEntity类似,不再赘述，而postForLocation是post方法特有的
        URI uri = restTemplate.postForLocation("http://localhost:8080/spittle", cccc, Spittle.class);
        System.out.println(uri);
    }

    @Test
    public void exchange() throws Exception {
        //如果需要设置请求头信息，使用exchange方法
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Object> spittleHttpEntity = new HttpEntity<>(map);
        ResponseEntity<Spittle> exchange = restTemplate.exchange("http://localhost:8080/spittle/{id}", HttpMethod.GET, spittleHttpEntity, Spittle.class, 12);
        System.out.println(exchange.getBody());
    }
}
