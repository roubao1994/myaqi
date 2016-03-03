package nju.software.present.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lulei on 16/3/2.
 */
@Controller
public class Test {
    @RequestMapping("/")
    public ModelAndView sayHello(){
        ModelAndView result = new ModelAndView();
        result.setViewName("index");
        result.getModel().put("name", "lulei");
        return result;
    }
}
