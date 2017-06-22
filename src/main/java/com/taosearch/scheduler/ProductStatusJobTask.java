package com.taosearch.scheduler;

import com.taosearch.dao.OrderDao;
import com.taosearch.model.Coupon;
import com.taosearch.model.Item;
import com.taosearch.model.QuerySPLBVo;
import com.taosearch.model.SimpleAuthorization;
import com.taosearch.service.dataoke.DaoLaoKeService;
import com.taosearch.service.dataoke.api.IDaoLaoKeService;
import com.taosearch.service.dataoke.api.ProductMO;
import com.taosearch.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MengWeiBo on 2017-04-01
 */

public class ProductStatusJobTask {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private IDaoLaoKeService daoLaoKeService;

    public void run() throws Exception {
        Map<String, Object>  map = new HashMap<>();
        map.put("pagestart", 0);
        map.put("rows", 100000);
        map.put("sa", new SimpleAuthorization());
        map.put("vo", new QuerySPLBVo());

        Date now = new Date();

//        //即将结束 --》结束
        map.put("state", "009");
        List<Item> end = orderDao.getItemListForPage(map);
        if (CollectionUtils.isNotEmpty(end)) {
            for (Item item : end) {
                Date endTime = DateTimeUtility.parseYYYYMMDDHHMMSS(item.getCoupon_end_time());
                if (endTime == null)
                    continue;
                endTime = DateTimeUtility.addDays(endTime,1);
                if (now.after(endTime)) {
                    item.setState("004");
                    orderDao.updateItemAndAddLog(item);
                }
            }
        }
//
//        //即将结束
        map.put("state", "003");
        List<Item> end1 = orderDao.getItemListForPage(map);
        if (CollectionUtils.isNotEmpty(end1)) {
            for (Item item : end1) {
                Date endTime = DateTimeUtility.parseYYYYMMDDHHMMSS(item.getCoupon_end_time());
                if (endTime == null)
                    continue;
                endTime = DateTimeUtility.addDays(endTime,1);
                int hour = DateTimeUtility.minuteBetween(now, endTime) / 60;
                if (hour <= 24) {
                    item.setState("009");
                    orderDao.updateItemAndAddLog(item);
                }
            }
        }

          //待二审 -- 》推广中
        map.put("state", "002");
        List<Item> list = orderDao.getItemListForPage(map);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Item item : list) {
                Date update = DateTimeUtility.parseYYYYMMDDHHMMSS(item.getUpdatetime());
                if (update == null)
                    continue;
                int hour = DateTimeUtility.minuteBetween(update, now) / 60;
                if (hour >= 24) {
                    item.setState("003");
                    orderDao.updateItemAndAddLog(item);
                }
            }
        }

        //驳回 --拒绝
        map.put("state", "112");
        List<Item> list2 = orderDao.getItemListForPage(map);
        if (CollectionUtils.isNotEmpty(list2)) {
            for (Item item : list2) {
                Date update = DateTimeUtility.parseYYYYMMDDHHMMSS(item.getUpdatetime());
                if (update == null)
                    continue;
                int hour = DateTimeUtility.minuteBetween(update, now) / 60;
                if (hour >= 48) {
                    item.setState("999");
                    orderDao.updateItemAndAddLog(item);
                }
            }
        }

        //推广中 更新优惠券
        map.put("state", "003");
        List<Item> list3 = orderDao.getItemListForPage(map);
        if (CollectionUtils.isNotEmpty(list3)) {
            int count = 1;
            for (Item item : list3) {
                Date startTime = DateTimeUtility.parseYYYYMMDDHHMMSS(item.getCoupon_start_time());
                if(startTime.after(now)){
                    continue;
                }
                ProductMO mo = daoLaoKeService.getProductMO(item.getItem_no());
                if (count % 50 == 0) {
                    Thread.sleep(60000);
                    mo = daoLaoKeService.getProductMO(item.getItem_no());
                }

                if (mo != null) {
                    Coupon coupon = new Coupon();
                    coupon.setCoupon_id(item.getCoupon_id());
                    coupon.setCoupon_get_num(mo.getCouponReceiveNumber());
                    coupon.setCoupon_rest_num(mo.getCouponSurplusNumber());

                    orderDao.updateCoupon(coupon);
                }
                count ++;
            }
        }
    }
}
