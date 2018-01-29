package tech.text.search.elasticsearch.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by datum on 29/1/18.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
