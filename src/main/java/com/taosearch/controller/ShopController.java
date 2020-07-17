/**
 * @Title: OrderController.java
 * @Package com.taosearch.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiajia
 * @date 2017年3月13日 下午2:21:07
 * @version V1.0
 */
package com.taosearch.controller;

import com.taosearch.model.*;
import com.taosearch.service.ShopService;
import com.taosearch.util.MD5;
import com.taosearch.util.MD5Encoder;
import com.taosearch.util.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping("/search")
    @ResponseBody
    public List<Shop> search(Long shopId, String shopName, HttpServletRequest request) {
        ShopQuery shopQuery = new ShopQuery();
        shopQuery.setPage(1);
        shopQuery.setRows(20);
        if (shopId != null && shopId > 0) {
            shopQuery.setShop_id(shopId);
        }
        if (StringUtils.isNotBlank(shopName)) {
            shopQuery.setName(shopName);
        }
        SimpleAuthorization sa = (SimpleAuthorization) request.getSession().getAttribute("userAuthorization");
        if (!sa.getAuthorization().equals("3")) {
            shopQuery.setUser_id(sa.getUser_id());
        }
        return shopService.getShops(shopQuery);
    }

    @RequestMapping("/getShops")
    @ResponseBody
    public ResultForPage<Shop> getShops(ShopQuery shopQuery, HttpServletRequest request) {
        SimpleAuthorization sa = (SimpleAuthorization) request.getSession().getAttribute("userAuthorization");
        if (!sa.getAuthorization().equals("3")) {
            shopQuery.setUser_id(sa.getUser_id());
        }
        ResultForPage<Shop> result = new ResultForPage<Shop>();
        List<Shop> list = shopService.getShops(shopQuery);
        int count = shopService.count(shopQuery);
        int totalpage = ((count - 1) / shopQuery.getRows()) + 1;
        result.setPage(shopQuery.getPage());
        result.setList(list);
        result.setRows(shopQuery.getRows());
        result.setTotals(count);
        result.setTotalpage(totalpage);
        return result;
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("shop", shopService.detail(id));
        result.put("shopUsers", shopService.getShopUsers(id));
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public MyResult<Long> save(Shop shop, HttpServletRequest request) {
        SimpleAuthorization sa = (SimpleAuthorization) request.getSession().getAttribute("userAuthorization");
        shop.setUser_id(sa.getUser_id());
        MyResult<Long> result = new MyResult<>();
        result.setCode(1);
        result.setMessage("保存成功");
        if (StringUtils.isBlank(shop.getName())) {
            result.setCode(2);
            result.setMessage("店铺名称不可以为空");
        }
        result.setData(shopService.save(shop));
        return result;
    }

    @RequestMapping("/user/save")
    @ResponseBody
    public MyResult<Long> userSave(ShopUser shopUser) {
        MyResult<Long> result = new MyResult<>();
        result.setCode(1);
        result.setMessage("保存成功");
        if (shopUser.getShop_id() > 0) {
            result.setData(shopService.saveShopUser(shopUser));
        } else {
            result.setCode(2);
            result.setMessage("请先保存店铺信息");
        }
        return result;
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public boolean userDelete(Long id) {
        shopService.deleteUser(id);
        return true;
    }
}
