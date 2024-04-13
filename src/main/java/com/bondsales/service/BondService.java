package com.bondsales.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Bond;
import com.bondsales.vo.UserNameVo;

public interface BondService extends IService<Bond> {
    ResponseResult showAll(UserNameVo userNameVo);

    ResponseResult addProduct(Bond bond);

    boolean bondExist(String cusip);
}
