/**
 * @Title: OrderService.java
 * @Package com.taosearch.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiajia
 * @date 2017年3月13日 下午2:22:29
 * @version V1.0
 */
package com.taosearch.service.impl;


import com.taosearch.dao.OrderDao;
import com.taosearch.dao.ShopDao;
import com.taosearch.model.*;
import com.taosearch.service.ShopService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("ShopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Shop detail(Long id) {
        return shopDao.getShopById(id);
    }

    @Override
    public Long save(Shop shop) {
        if (shop.getId() > 0) {
            shopDao.update(shop);
            return shop.getId();
        }
        shopDao.save(shop);
        return shop.getId();
    }

    @Override
    public List<Shop> getShops(ShopQuery query) {
        if (StringUtils.isNotBlank(query.getPhone())
                || StringUtils.isNotBlank(query.getQq())
                || StringUtils.isNotBlank(query.getWechat())) {
            List<Long> shopIds = shopDao.gethopIdsFormShopUsers(query);
            if (CollectionUtils.isEmpty(shopIds)) {
                shopIds.add(-1L);
            }
            query.setShop_ids(shopIds);
        }
        if (StringUtils.isNotBlank(query.getItem_no())) {
            List<Long> shopIds = orderDao.getItemListByItemNo(query.getItem_no());
            if (CollectionUtils.isNotEmpty(query.getShop_ids())) {
                query.getShop_ids().retainAll(shopIds);
            } else {
                query.setShop_ids(shopIds);
            }
            if (CollectionUtils.isEmpty(query.getShop_ids())) {
                query.setShop_ids(Collections.singletonList(-1L));
            }
        }
        return shopDao.getShops(query);
    }

    @Override
    public int count(ShopQuery query) {
        return shopDao.getCount(query);
    }

    @Override
    public List<ShopUser> getShopUsers(long shopId) {
        return shopDao.getShopUsers(shopId);
    }

    @Override
    public Long saveShopUser(ShopUser shopUser) {
        if (shopUser.getId() > 0) {
            shopDao.deleteShopUser(shopUser.getId());
        }
        shopDao.saveShopUser(shopUser);
        return shopUser.getId();
    }

    @Override
    public void deleteUser(long id) {
        shopDao.deleteShopUser(id);
    }

    @Override
    public void updateStatistic(String id) {
        ItemInfo itemInfo = orderDao.getitemInfoById(id);
        if (itemInfo == null || itemInfo.getShop_id() == null) {
            return;
        }
        Shop shop = shopDao.getShopById(itemInfo.getShop_id());
        if (shop == null) {
            return;
        }
        List<Item> list = orderDao.getItemListByShopId(itemInfo.getShop_id());
        Set<String> itemSet = new HashSet<>(list.size());
        double item_qhjg_total = 0;
        double item_ssje_total = 0;
        for (Item item : list) {
            itemSet.add(item.getItem_no());
            if (item.getItem_qhjg() != null) {
                item_qhjg_total += item.getItem_qhjg();
            }
            if (item.getItem_ssje() != null) {
                item_ssje_total += Double.parseDouble(item.getItem_ssje());
            }
        }
        shop.setReal_item_num(list.size());
        shop.setItem_num(itemSet.size());
        shop.setPrice(new  BigDecimal(item_qhjg_total / list.size()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
        shop.setSettlement(new  BigDecimal(item_ssje_total / list.size()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
        shop.setTotal_settlement(item_ssje_total);
        shopDao.update(shop);
    }
}
