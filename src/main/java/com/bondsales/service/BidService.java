package com.bondsales.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Bid;


/**
 * (Bid)表服务接口
 *
 * @author makejava
 * @since 2024-04-10 22:07:43
 */
public interface BidService extends IService<Bid> {

    ResponseResult create(String cusip);

    ResponseResult delete(String cusip, String username);
}
