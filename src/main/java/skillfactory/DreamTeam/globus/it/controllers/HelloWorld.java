package skillfactory.DreamTeam.globus.it.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/init")
public class HelloWorld {

    @GetMapping()
    public String helloWorld() {
        return "Hello World!";
    }
}
