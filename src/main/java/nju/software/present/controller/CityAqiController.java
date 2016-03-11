package nju.software.present.controller;

import nju.software.present.controller.bo.CityAqi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lulei on 16/3/7.
 * controller to load data in order to present aqi of all cities in China
 */
@Controller
@RequestMapping("/present")
public class CityAqiController {
    @ResponseBody
    @RequestMapping(value = "/getAllCityAqis")
    public List<CityAqi> getCityAqis() {
        ArrayList<CityAqi> resultList = new ArrayList<>();

        CityAqi mockAqi = new CityAqi();
        mockAqi.setName("南京");
        mockAqi.setAQI("100");
        mockAqi.setCoordinates("[119.58,35.53]");
        mockAqi.setDate(new Date());
        mockAqi.setLevel("优");



        CityAqi mockAqi2 = new CityAqi();
        mockAqi2.setName("汕尾");
        mockAqi2.setAQI("39");
        mockAqi2.setCoordinates("[123.85, 42.29]");
        mockAqi2.setDate(new Date());
        mockAqi2.setLevel("良");

        resultList.add(mockAqi);
        resultList.add(mockAqi2);
        return resultList;
    }

    @RequestMapping(value = "/getSpecificCity")
    public ModelAndView getSpecificCity(String cityName){
        ModelAndView result = new ModelAndView();
        result.setViewName("city");
        result.getModel().put("cityName", cityName);
        return result;
    }
}
