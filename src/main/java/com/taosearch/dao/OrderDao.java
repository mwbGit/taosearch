/**  
* @Title: OrderDao.java
* @Package com.taosearch.dao
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月13日 下午2:21:51
* @version V1.0  
*/
package com.taosearch.dao;

import java.util.List;
import java.util.Map;

import com.taosearch.model.Coupon;
import com.taosearch.model.FinancialStatements;
import com.taosearch.model.Item;
import com.taosearch.model.ItemAttachment;
import com.taosearch.model.ItemAuditLog;
import com.taosearch.model.ItemInfo;
import com.taosearch.model.ItemType;
import com.taosearch.model.NavStateCount;
import com.taosearch.model.SimpleAuthorization;

/**
* @ClassName: OrderDao
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月13日 下午2:21:51
*
*/
public interface OrderDao {
	List<ItemType> getItemTypes();

	int saveItemInfo(Item item);

	int saveCouponInfo(Item item);

	int getItemUrlCount(String item_url);

	List<Item> getItemListForPage(Map<String, Object> map);

	int getItemCount(Map<String, Object> map);

	ItemInfo getitemInfoById(String item_id);

	int updateItemState(ItemAuditLog auditLog);

	int saveAuditLog(ItemAuditLog auditLog);

	int updateCoupon(Coupon coupon);

	void saveItemAttachemt(ItemAttachment attachment);

	int updateItemzfje(Map<String, Object> map);

	int updateItemssje(Map<String, Object> map);

	List<NavStateCount> getnavstateinfo(SimpleAuthorization sa);

	int updateItemAndAddLog(Item item);

	List<FinancialStatements> getFinancialStatementsForPage(Map<String, Object> map);

	List<FinancialStatements> getFinancialStatementsForExcel(Map<String, Object> map);

	int getFinancialStatementsCount(Map<String, Object> map);
}
