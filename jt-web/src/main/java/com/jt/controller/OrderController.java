package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocalUtil;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Reference(check = false)
	private DubboCartService cartService;
	@Reference(check = false)
	private DubboOrderService orderService;
	/**
	 * 跳转到订单确认页面
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		//前提是拦截器必须生效 必须编辑配置文件
		Long userId = UserThreadLocalUtil.get().getId();
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		model.addAttribute("carts", cartList);
		return "order-cart";
	}
	
	/**
	 * 根据页面传参,实现订单的入库操作. 3张表
	 * 之后将订单编号orderId返回.
	 * 为了查询订单信息提供数据的支持
	 * @param order
	 * @return
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order) {
		
		String orderId = orderService.saveOrder(order);
		if(StringUtils.isEmpty(orderId)) {
			return SysResult.fail();
		}
		return SysResult.success(orderId);
	}
	
	@RequestMapping("/success")
	public String findOrderById(String id,Model model) {
		
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "success";
	}
	
	
	
	
	
	
	
	/**
	 * <input type="text" name="参数名称" value="AAAAA"/>
	 * @param request
	 * @param response
	 */
	/*
	 * public void aaa(Order order) {
	 * 
	 * //XXXX XXX = request.getParameter("参数名称!!!"); }
	 */
	
	
	
	
	
}
