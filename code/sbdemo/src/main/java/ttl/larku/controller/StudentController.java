package ttl.larku.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sbdemo/student")
public class StudentController {

    @GetMapping
    public String doIt() {
        return "Will this really work?????";
    }
}
