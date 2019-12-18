package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Order;

public interface OrderMapper extends BaseMapper<Order>{
	
	//查询订单信息
	Order findOrderById(String id);

}
