package com.hy.dynamicdatasource.service.impl;

import com.hy.dynamicdatasource.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/21.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @Transactional()
    public List<Map<String, Object>> findHzhsOrders() {
        return null;
    }

}
