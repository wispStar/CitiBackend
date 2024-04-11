package com.bondsales.controller;

import com.bondsales.ResponseResult;
import com.bondsales.service.BondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class BondController {

    @Autowired
    private BondService bondService;

    @GetMapping("/list")
    public ResponseResult showAll() {
        return bondService.showAll();
    }

//    @GetMapping("/list")
//    public ResponseResult showAll(@RequestBody Bond bond) {
//        return bondService.showAll(bond);
//    }
}
