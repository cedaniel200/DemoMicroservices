package com.cedaniel200.microservice.subtract.infrastructure.controller;

import com.cedaniel200.microservice.subtract.domain.Subtractor;
import com.cedaniel200.microservice.subtract.infrastructure.vo.Subtraction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtract")
public class SubtractController {

    private final Subtractor subtractor;

    public SubtractController(Subtractor subtractor) {
        this.subtractor = subtractor;
    }

    @GetMapping("/")
    @ResponseBody
    public Subtraction subtract(@RequestParam int... numbers) {
        int result = subtractor.subtract(numbers);
        return new Subtraction(numbers, result);
    }

}
