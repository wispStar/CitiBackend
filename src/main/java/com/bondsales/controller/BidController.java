package com.bondsales.controller;

import com.bondsales.ResponseResult;
import com.bondsales.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping("/create/{cusip}")
    public ResponseResult create(@PathVariable("cusip") String cusip) {
        return bidService.create(cusip);
    }

    @DeleteMapping("/delete/{cusip}")
    public ResponseResult delete(@PathVariable("cusip") String cusip, @RequestParam String username) {
        return bidService.delete(cusip, username);
    }
}
