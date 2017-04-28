/**  
* @Title: OrderService.java
* @Package com.taosearch.service
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月13日 下午2:22:29
* @version V1.0  
*/
package com.taosearch.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.taosearch.model.Coupon;
import com.taosearch.model.FinancialStatements;
import com.taosearch.model.Item;
import com.taosearch.model.ItemAuditLog;
import com.taosearch.model.ItemInfo;
import com.taosearch.model.ItemType;
import com.taosearch.model.MyResult;
import com.taosearch.model.NavStateCount;
import com.taosearch.model.SimpleAuthorization;

/**
* @ClassName: OrderService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月13日 下午2:22:29
*
*/
public interface OrderService {
	List<ItemType> getItemTypes();

	List<NavStateCount> getnavstateinfo(SimpleAuthorization sa);

	MyResult<String> saveItemInfo(Item item);

	int getItemUrlCount(String item_url);

	List<Item> getItemListForPage(Map<String, Object> map);

	int getItemCount(Map<String, Object> map);

	ItemInfo getitemInfoById(String item_id);

	MyResult<String> saveauditLog(HttpServletRequest request, ItemAuditLog auditLog, Coupon coupon);

	int updateCoupon(Coupon coupon);

	MyResult<ItemInfo> saveItemAttachemt(ItemAuditLog auditLog, Double item_zfje, HttpServletRequest request);

	MyResult<String> saveItemAttachemt1(ItemAuditLog auditLog, Double item_ssje, HttpServletRequest request);

	MyResult<String> updateItemAndAddLog(ItemAuditLog auditLog, Item item);

	List<FinancialStatements> getFinancialStatementsForPage(Map<String, Object> map);

	List<FinancialStatements> getFinancialStatementsForExcel(Map<String, Object> map);

	int getFinancialStatementsCount(Map<String, Object> map);
}
