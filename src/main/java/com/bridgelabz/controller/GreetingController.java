package com.bridgelabz.controller;

import com.bridgelabz.model.Greeting;
import com.bridgelabz.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingController {
    @Autowired
    GreetingService greetingService;

    @PostMapping("/addgreeting")
    public String addGreeting(@RequestBody  Greeting g)
    {
        return greetingService.addGreeting(g)+" greeting added successfully";
    }
    @GetMapping("/getgreeting/{id}")
    public String getGreeting(@PathVariable("id") int id)
    {
        return  greetingService.getGreeting(id);
    }
    @PutMapping("/update/{id}")
    public String updateGreeting(@PathVariable("id") int id,@RequestBody Greeting g)
    {
        return greetingService.updateGreeting(id,g)+"Updated successfully";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteGreeting(@PathVariable("id") int id)
    {
        return greetingService.deleteGreeting(id)+"deleted successfully";
    }
    @GetMapping("/getAll")
    public List<Greeting> getAll()
    {
        return  greetingService.getAllGreeting();
    }
    @GetMapping("/greetings/search")
    public List<Greeting> searchGreetings(@RequestParam("name") String name) {
        return greetingService.searchGreeting(name);
    }


    @GetMapping("/greetings/user/{name}")
    public List<Greeting> getUserGreetings(@PathVariable("name") String name) {
        return greetingService.getUserGreetings(name);
    }
}
