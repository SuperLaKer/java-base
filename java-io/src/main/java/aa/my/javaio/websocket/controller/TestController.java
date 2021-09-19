package aa.my.javaio.websocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hi")
    public String sayHi(){
        System.out.println(TestController.class.getSimpleName()+"...");
        return "xxx";
    }

}
