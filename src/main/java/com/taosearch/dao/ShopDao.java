/**  
* @Title: OrderDao.java
* @Package com.taosearch.dao
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月13日 下午2:21:51
* @version V1.0  
*/
package com.taosearch.dao;

import com.taosearch.model.*;

import java.util.List;
import java.util.Map;


public interface ShopDao {
	List<Shop> getShops(ShopQuery query);

	int getCount(ShopQuery query);

	Long save(Shop shop);

	Long update(Shop shop);

	Shop getShopById(Long id);

	List<ShopUser> getShopUsers(long shop_id);

	List<Long> gethopIdsFormShopUsers(ShopQuery query);

	Long saveShopUser(ShopUser shopUser);

	void deleteShopUser(long id);
}
