/**  
* @Title: OrderService.java
* @Package com.taosearch.service
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月13日 下午2:22:29
* @version V1.0  
*/
package com.taosearch.service;

import com.taosearch.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface ShopService {
	List<Shop> getShops(ShopQuery query);

	int count(ShopQuery query);

	Shop detail(Long id);

	Long save(Shop shop);

	List<ShopUser> getShopUsers(long shopId);

	Long saveShopUser(ShopUser shopUser);

	void deleteUser(long id);

	void updateStatistic(String id);

}
