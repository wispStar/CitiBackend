package com.bondsales.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Bond;

public interface BondService extends IService<Bond> {
    ResponseResult showAll();
}
