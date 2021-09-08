package com.springinaction.web;

import com.springinaction.common.Spitter;
import com.springinaction.data.JpaSpitterRepository;
import com.springinaction.data.SpitterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by zjjfly on 2016/12/30.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    private RedisOperations<String,Spitter> redisOperations;

    @Autowired
    private JpaSpitterRepository jpaSpitterRepository;

    public static final Logger logger= LoggerFactory.getLogger(SpitterController.class);

    @Autowired
    public SpitterController(@Qualifier("jSpitterRepository") SpitterRepository spitterRepository, PasswordEncoder passwordEncoder){
        this.spitterRepository=spitterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegisterForm(Model model) {
        model.addAttribute(new Spitter());
        return "register";
    }

    public SpitterController(){}

    //在Servlet3.0容器中，可以使用Part替代MultipartFile，但不推荐
    //RedirectAttributes是Model的一个子接口，可以添加flash属性
    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(@RequestPart MultipartFile profilePicture, @Valid Spitter spitter, Errors errors,RedirectAttributes model) throws IOException {
        logger.info("upload an image:"+profilePicture.getOriginalFilename()+",size:"+profilePicture.getSize());
        if(profilePicture.getSize()>0) {
            profilePicture.transferTo(new File("/Users/zjjfly/Desktop/" +profilePicture.getName()+"."+profilePicture.getContentType()));
        }
        if(errors.hasErrors()){
            return "register";
        }
        spitter.setPassword(passwordEncoder.encode(spitter.getPassword()));
//        spitterRepository.save(spitter);
        jpaSpitterRepository.save(spitter);
        redisOperations.opsForValue().set(spitter.getUsername(),spitter);
        model.addAttribute("username",spitter.getUsername());
        model.addFlashAttribute("spitter",spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        if(!model.containsAttribute("spitter")) {
            Spitter spitter = redisOperations.opsForValue().get(username);
            if (spitter ==null) {
//            model.addAttribute(spitterRepository.findByUsername(username));
                model.addAttribute(jpaSpitterRepository.findByUsername(username));
            }else {
                model.addAttribute(spitter);
            }
        }
//        jpaSpitterRepository.findAllGmailSpitter().stream().forEach(System.out::println);
        return "profile";
    }
}
