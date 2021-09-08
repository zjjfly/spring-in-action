package com.springinaction.web;

import com.springinaction.common.Spittle;
import com.springinaction.common.SpittleNotFoundException;
import com.springinaction.data.JpaSpittleRepository;
import com.springinaction.data.SpittleRepository;
import com.springinaction.service.SpittleFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by zjjfly on 2016/12/28.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    Logger logger = LoggerFactory.getLogger(SpittleController.class);

    private SpittleRepository repository;

    @Autowired
    private JpaSpittleRepository jpaSpittleRepository;

    @Autowired
    SpittleFeedService spittleFeedService;

    private final static String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";

    @Autowired
    public SpittleController(@Qualifier("jSpittleRepository") SpittleRepository repository) {
        this.repository = repository;
    }

//    @RequestMapping(method = GET)
//    public String spittles(Model model) {
//        model.addAttribute(repository.findSpittles(Long.MAX_VALUE, 20));
//        return "spittles";
//    }

    @ModelAttribute
    public Spittle add() {
        return new Spittle();
    }

    @RequestMapping(method = GET)
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max, @RequestParam(value = "count", defaultValue = "30") int count) {
        //使用jpa自动生成和自定义混合方式
        jpaSpittleRepository.badSpittle().forEach(System.out::println);
//        return jpaSpittleRepository.findAll(new PageRequest(0, count)).getContent();
        return repository.findSpittles(max, count);
    }

    @RequestMapping(method = POST)
    public String spittles(Spittle spittle, Model model) {
        logger.info("add a spittle,its message is:" + spittle.getMessage());
        spittle.setTime(new Date());
        Spittle save = repository.save(spittle);
        spittleFeedService.broadcastSpittle(save);
//        jpaSpittleRepository.save(spittle);
        return "redirect:/spittles";
    }

    @RequestMapping(value = "/{spittleId}", method = GET)
    public String spittle(@PathVariable long spittleId, Model model) {
        logger.info("get a spittle");
        Spittle spittle = repository.findOne(spittleId);
//        Spittle spittle = jpaSpittleRepository.findOne(spittkeId);
        if (spittle == null) {
            throw new SpittleNotFoundException(spittleId);
        }
        model.addAttribute(spittle);
        return "spittle";
    }

}
