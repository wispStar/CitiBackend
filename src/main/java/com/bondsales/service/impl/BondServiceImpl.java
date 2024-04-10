package com.bondsales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Bond;
import com.bondsales.enums.AppHttpCodeEnum;
import com.bondsales.exception.SystemException;
import com.bondsales.mapper.BondMapper;
import com.bondsales.service.BondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bondService")
public class BondServiceImpl extends ServiceImpl<BondMapper, Bond> implements BondService {

    @Autowired
    private BondMapper bondMapper;
    @Override
    public ResponseResult showAll(Bond bond){
        // 使用 bondMapper 的 selectList 方法获取所有的 Bond 对象
        List<Bond> bonds = bondMapper.selectList(null);
        if (bonds == null) {
            throw new SystemException(AppHttpCodeEnum.BOND_NOT_FOUND);
        }

        // 创建一个 ResponseResult 对象，将查询到的 Bond 对象列表作为数据设置到这个对象中
        ResponseResult result = ResponseResult.okResult(bonds);

        // 返回创建的 ResponseResult 对象
        return result;
    }
}
