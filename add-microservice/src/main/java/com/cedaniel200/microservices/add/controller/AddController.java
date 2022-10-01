package com.cedaniel200.microservices.add.controller;

import com.cedaniel200.microservices.add.domain.Adder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add")
public class AddController {

    private Adder adder;

    public AddController(Adder adder) {
        this.adder = adder;
    }

    @GetMapping("/")
    @ResponseBody
    public int add(@RequestParam int... numbers) {
        return adder.add(numbers);
    }
}
