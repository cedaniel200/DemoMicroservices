package com.cedaniel200.microservice.subtract.controller;

import com.cedaniel200.microservice.subtract.domain.Subtractor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtract")
public class SubtractController {

    private Subtractor subtractor;

    public SubtractController(Subtractor subtractor) {
        this.subtractor = subtractor;
    }

    @GetMapping("/")
    @ResponseBody
    public int subtract(@RequestParam int... numbers) {
        return subtractor.subtract(numbers);
    }

}
