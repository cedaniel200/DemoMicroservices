package com.cedaniel200.microservices.add.infrastructure.controller;

import com.cedaniel200.microservices.add.domain.Adder;
import com.cedaniel200.microservices.add.infrastructure.vo.Sum;
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
    public Sum add(@RequestParam int... numbers) {
        int result = adder.add(numbers);
        return new Sum(numbers, result);
    }
}
