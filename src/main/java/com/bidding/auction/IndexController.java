/**
 * class that diaplays the index page when project is launched
 */
package com.bidding.auction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//specifies class represents a controller
public class IndexController {
    @GetMapping(path="")//get mapping for index class
    public ModelAndView home(){
        ModelAndView mav=new ModelAndView("index");//model to diplay the index file in static folder
        return mav;//render index file
    }
}
