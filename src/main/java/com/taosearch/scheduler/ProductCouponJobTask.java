package com.taosearch.scheduler;

import com.taosearch.dao.OrderDao;
import com.taosearch.model.Coupon;
import com.taosearch.model.Item;
import com.taosearch.model.QuerySPLBVo;
import com.taosearch.model.SimpleAuthorization;
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

public class ProductCouponJobTask {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private IDaoLaoKeService daoLaoKeService;

    private static String[] appKeys = {"8jdrk90okh"};

    private static long millis = 60000;

    public void run() throws Exception {
        Map<String, Object>  map = new HashMap<>();
        map.put("pagestart", 0);
        map.put("rows", 100000);
        map.put("sa", new SimpleAuthorization());
        map.put("vo", new QuerySPLBVo());
        //推广中 更新优惠券
        map.put("state", "003");

        Date now = new Date();
        List<Item> list3 = orderDao.getItemListForPage(map);
        if (CollectionUtils.isNotEmpty(list3)) {
            int count = 1;
            int index = 1;
            String appKey = appKeys[0];
            for (Item item : list3) {
                Date startTime = DateTimeUtility.parseYYYYMMDDHHMMSS(item.getCoupon_start_time());
                if(startTime.after(now)){
                    continue;
                }
                if (count % 50 == 0) {
                    if (appKeys.length > index) {
                        appKey = appKeys[index++];
                    } else {
                        Thread.sleep(millis);
                    }
                }

                ProductMO mo = daoLaoKeService.getProductMO(item.getItem_no(), appKey);

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
