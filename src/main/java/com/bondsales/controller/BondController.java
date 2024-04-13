package com.bondsales.controller;

import com.bondsales.ResponseResult;
import com.bondsales.entity.Bond;
import com.bondsales.service.BondService;
import com.bondsales.vo.UserNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class BondController {

    @Autowired
    // 使用 @Autowired 注解将 BondService 注入到 BondController 中
    private BondService bondService;

    @PostMapping("/list")
    public ResponseResult showAll(@RequestBody UserNameVo userNameVo) {
        return bondService.showAll(userNameVo);
    }

    @PostMapping("/add")
    public ResponseResult addProduct(@RequestBody Bond bond) {
        return bondService.addProduct(bond);
    }
}
