/**  
* @Title: OrderController.java
* @Package com.taosearch.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月13日 下午2:21:07
* @version V1.0  
*/
package com.taosearch.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taosearch.service.dataoke.api.IDaoLaoKeService;
import com.taosearch.service.dataoke.api.ProductMO;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taosearch.model.Coupon;
import com.taosearch.model.FinancialStatements;
import com.taosearch.model.Item;
import com.taosearch.model.ItemAuditLog;
import com.taosearch.model.ItemInfo;
import com.taosearch.model.ItemType;
import com.taosearch.model.MyResult;
import com.taosearch.model.NavStateCount;
import com.taosearch.model.QueryFinancialVo;
import com.taosearch.model.QuerySPLBVo;
import com.taosearch.model.ResultForPage;
import com.taosearch.model.SimpleAuthorization;
import com.taosearch.model.SysUser;
import com.taosearch.service.OrderService;
import com.taosearch.util.DownloadUtil;
import com.taosearch.util.POIUtil;
import com.taosearch.util.Util;

/**
* @ClassName: OrderController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月13日 下午2:21:07
*
*/
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	private OrderService OrderService;

	@Autowired
	private IDaoLaoKeService daoLaoKeService;

	@RequestMapping("/getDataoke")
	@ResponseBody
	public Item claim(String item_url) {
		Item item = new Item();
		item.setItem_no(item_url);
		if (StringUtils.isBlank(item_url)){
			item.setShowClaim(true);
			return item;
		}

		ProductMO mo = daoLaoKeService.getProductMO(item_url);
		if (mo != null) {
			if (mo.getDiscountPrice() != null) {
				item.setItem_qhjg(mo.getDiscountPrice().doubleValue());
			}
			if (mo.getGeneral() != null) {
				item.setItem_yjbl(mo.getGeneral().doubleValue());
			} else if (mo.getMagpie() != null) {
				item.setItem_yjbl(mo.getMagpie().doubleValue());
			}
			item.setCoupon_get_num(mo.getCouponReceiveNumber());
			item.setCoupon_rest_num(mo.getCouponSurplusNumber());
			item.setCoupon_end_time(mo.getCouponEndTime());
			item.setItem_name(mo.getName());
		}else {
			item.setShowClaim(true);
		}

		return item;
	}
	@RequestMapping("/cancelAudit")
	@ResponseBody
	public void cancelAudit(String id) {
		Item item = new Item();
		item.setItem_id(id);
		item.setState("001");

		OrderService.updateItem(item);
	}
	@RequestMapping("/getItemTypes")
	@ResponseBody
	public List<ItemType> getItemTypes() {
		return OrderService.getItemTypes();
	}

	@RequestMapping("/getnavstateinfo")
	@ResponseBody
	public List<NavStateCount> getnavstateinfo(HttpServletRequest request) {
		SimpleAuthorization sa = (SimpleAuthorization) request.getSession().getAttribute("userAuthorization");
		return OrderService.getnavstateinfo(sa);
	}

	@RequestMapping("/getItemInfo")
	@ResponseBody
	public MyResult<Item> getItemInfo(String item_url, String status) {
		MyResult<Item> result = new MyResult<Item>();
		Item item = new Item();
		if (item_url == null || "".equals(item_url)) {
			result.setCode(9999);
			result.setMessage("请输入商品地址");
			return result;
		}

		int count = OrderService.getItemUrlCount(item_url);
		if (!"112".equals(status) && count > 0) {
			result.setCode(2222);
			result.setMessage("此商品已有人提交过");
			return result;
		}
		try {
			URL u = new URL(item_url);
			String host = u.getHost();
			Connection conn = Util.getConnection(item_url, null);
			Document document = conn.get();
			if (host.matches(".*tmall.*")) {
				Element e = document.getElementById("J_DetailMeta");
				Element h = e.select("h1").get(0);
				item.setItem_name(h.text());
				item.setItem_no(Util.URLRequest(item_url).get("id"));
				Element img = document.getElementById("J_ImgBooth");
				String img_url = img.attr("src").split(".jpg_")[0] + ".jpg_100x100q90.jpg";
				item.setItem_image(img_url);
				String title = document.title();
				item.setItem_title(title);
				item.setItem_website_type("2");
			} else if (host.matches(".*taobao.*")) {
				Element e = document.getElementById("J_Title");
				Element h = e.select("h3").get(0);
				item.setItem_name(h.text());
				item.setItem_no(Util.URLRequest(item_url).get("id"));
				Element img = document.getElementById("J_ImgBooth");
				String img_url = img.attr("src").split(".jpg_")[0] + ".jpg_100x100.jpg";
				item.setItem_image(img_url);
				String title = document.title();
				item.setItem_title(title);
				item.setItem_website_type("1");
			} else {
				result.setCode(9998);
				result.setMessage("请输入正确的网址");
				return result;
			}
		} catch (MalformedURLException e) {
			result.setCode(9998);
			result.setMessage("请输入正确的网址");
			e.printStackTrace();
			return result;
		} catch (IOException e) {
			result.setCode(8888);
			result.setMessage("网络异常");
			e.printStackTrace();
			return result;
		}

		result.setCode(1);
		result.setMessage("请求成功");
		result.setData(item);
		return result;
	}

	@RequestMapping("/submitItemInfo")
	@ResponseBody
	public MyResult<String> saveItemInfo(HttpServletRequest request, Item item) {
		MyResult<String> result = new MyResult<String>();

		if (item == null) {
			result.setCode(7777);
			result.setMessage("数据传输异常未收到值");
			return result;
		}
		String coupon_id = Util.getUUID();
		item.setItem_id(Util.getUUID());
		SysUser user = (SysUser) request.getSession().getAttribute("loginUser");
		item.setUser_id(user.getUser_id());
		item.setState("001");
		item.setCoupon_id(coupon_id);
		result = OrderService.saveItemInfo(item);
		return result;
	}

	@RequestMapping("/updateItemInfo")
	@ResponseBody
	public MyResult<String> updateItemInfo(HttpServletRequest request, Item item, String before_audit_status) {
		MyResult<String> result = new MyResult<String>();

		if (item == null) {
			result.setCode(7777);
			result.setMessage("数据传输异常未收到值");
			return result;
		}

		ItemInfo info = OrderService.getitemInfoById(item.getItem_id());
		SysUser user = (SysUser) request.getSession().getAttribute("loginUser");

		if (info == null || !info.getState().equals("112")) {
			String coupon_id = Util.getUUID();
			item.setItem_id(Util.getUUID());
			item.setUser_id(user.getUser_id());
			item.setState("001");
			item.setCoupon_id(coupon_id);
			result = OrderService.saveItemInfo(item);

			return result;
		}

		ItemAuditLog auditLog = new ItemAuditLog();
		auditLog.setAfter_audit_status("001");
		auditLog.setBefore_audit_status(before_audit_status);
		auditLog.setItem_id(item.getItem_id());
		auditLog.setAuditor(user.getUsername());
		auditLog.setAudit_remarks("更改后再次提交");
		item.setUser_id(user.getUser_id());
		item.setState("001");

		Coupon coupon = new Coupon();
		coupon.setCoupon_id(info.getCoupon_id());
		coupon.setActivity_start_time(item.getActivity_start_time());
		coupon.setCoupon_start_time(item.getCoupon_start_time());
		coupon.setCoupon_end_time(item.getCoupon_end_time());
		coupon.setCoupon_url(item.getCoupon_url());

		OrderService.updateCoupon(coupon);

		result = OrderService.updateItemAndAddLog(auditLog, item);

		return result;
	}

	@RequestMapping("/getItemInfoById")
	@ResponseBody
	public MyResult<ItemInfo> getItemInfoById(HttpServletRequest request, String item_id) {
		MyResult<ItemInfo> result = new MyResult<ItemInfo>();
		ItemInfo data = OrderService.getitemInfoById(item_id);
		result.setCode(1);
		result.setMessage("成功获取数据");
		result.setData(data);
		return result;
	}

	@RequestMapping("/saveauditLog")
	@ResponseBody
	public MyResult<String> saveauditLog(HttpServletRequest request, ItemAuditLog auditLog, Coupon coupon) {
		MyResult<String> result = OrderService.saveauditLog(request, auditLog, coupon);
		return result;
	}

	@RequestMapping(value = "/uploadpayInfo")
	@ResponseBody
	public MyResult<ItemInfo> uploadpayInfo(ItemAuditLog auditLog, Double item_zfje, HttpServletRequest request) {
		if (item_zfje == null) {
			MyResult<ItemInfo> result = new MyResult<ItemInfo>();
			result.setCode(6666);
			result.setMessage("请输入支付金额");
			return result;
		}
		MyResult<ItemInfo> result = OrderService.saveItemAttachemt(auditLog, item_zfje, request);
		return result;
	}

	@RequestMapping(value = "/uploadpayingInfo")
	@ResponseBody
	public MyResult<String> uploadpayingInfo(ItemAuditLog auditLog, Double item_ssje, HttpServletRequest request) {
		if ("007".equals(auditLog.getAfter_audit_status()) && item_ssje == null) {
			MyResult<String> result = new MyResult<String>();
			result.setCode(6666);
			result.setMessage("请输入实收金额");
			return result;
		}

		MyResult<String> result = OrderService.saveItemAttachemt1(auditLog, item_ssje, request);
		return result;
	}

	@RequestMapping("/updateCoupon")
	@ResponseBody
	public MyResult<String> updateCoupon(Coupon coupon) {
		MyResult<String> result = new MyResult<String>();
		if (!checkCoupon(coupon)) {
			result.setCode(777);
			result.setMessage("请输入要更改的数据");
			return result;
		}
		int i = OrderService.updateCoupon(coupon);
		if (i > 0) {
			result.setCode(1);
			result.setMessage("操作成功");
		} else {
			result.setCode(999);
			result.setMessage("操作失败");
		}
		return result;
	}

	private boolean checkCoupon(Coupon coupon) {
		if (coupon == null) {
			return false;
		}
		if ((coupon.getCoupon_end_time() == null || "".equals(coupon.getCoupon_end_time())) && coupon.getCoupon_rest_num() == null && coupon.getCoupon_get_num() == null && coupon.getCoupon_use_num() == null) {
			return false;
		}
		return true;
	}

	@RequestMapping("/queryItemlistForPage")
	@ResponseBody
	public ResultForPage<Item> queryItemlistForPage(HttpServletRequest request, QuerySPLBVo vo, String statePage, Integer page, Integer rows) {
		if ("undefined".equals(statePage)){
			statePage =null;
		}
		if (StringUtils.isBlank(vo.getActivity_begin_time())){
			vo.setActivity_begin_time(null);
		}
		if (StringUtils.isBlank(vo.getActivity_end_time())){
			vo.setActivity_end_time(null);
		}

		ResultForPage<Item> result = new ResultForPage<Item>();
		int pagestart = (page - 1) * rows;
		SimpleAuthorization sa = (SimpleAuthorization) request.getSession().getAttribute("userAuthorization");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagestart", pagestart);
		map.put("rows", rows);
		map.put("state", statePage);
		map.put("vo", vo);
		if (vo.isHz()){
			sa = new SimpleAuthorization();
			sa.setAuthorization("3");
		}
		map.put("sa", sa);

		List<Item> list = OrderService.getItemListForPage(map);

		int count = OrderService.getItemCount(map);
		int totalpage = ((count - 1) / rows) + 1;
		result.setPage(page);
		result.setList(list);
		result.setRows(rows);
		result.setTotals(count);
		result.setTotalpage(totalpage);
		return result;
	}

	@RequestMapping("/queryFinancialStatementsForPage")
	@ResponseBody
	public ResultForPage<FinancialStatements> queryFinancialStatementsForPage(HttpServletRequest request, QueryFinancialVo vo, Integer page, Integer rows) {
		String order = "";
		if (StringUtils.isNotBlank(vo.getSsje())) {
			order += "ssje " + vo.getSsje();
		} else if (StringUtils.isNotBlank(vo.getDaynum())) {
			order += "daynum " + vo.getDaynum();
		} else {
			order += "totle " + (StringUtils.isBlank(vo.getTotle()) ? "desc" : vo.getTotle());
		}
		vo.setOrder(order);

		ResultForPage<FinancialStatements> result = new ResultForPage<FinancialStatements>();
		int pagestart = (page - 1) * rows;
		SimpleAuthorization sa = (SimpleAuthorization) request.getSession().getAttribute("userAuthorization");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagestart", pagestart);
		map.put("rows", rows);
		map.put("vo", vo);
		map.put("sa", sa);

		List<FinancialStatements> list = OrderService.getFinancialStatementsForPage(map);
		int count = OrderService.getFinancialStatementsCount(map);
		int totalpage = ((count - 1) / rows) + 1;
		result.setPage(page);
		result.setList(list);
		result.setRows(rows);
		result.setTotals(count);
		result.setTotalpage(totalpage);

		FinancialStatements financial = new FinancialStatements();
//		financial.setJjl("0");
//		financial.setSsje("0");
//		financial.setYsje("0");
//		financial.setKdj("0");
		financial.setUsername("合计");
		for (FinancialStatements statements : list) {
			financial.setTotle(financial.getTotle() + statements.getTotle());
			financial.setDscnum(financial.getDscnum() + statements.getDscnum());
			financial.setJjjsnum(financial.getJjjsnum() + statements.getJjjsnum());
			financial.setDesnum(financial.getDesnum() + statements.getDesnum());
			financial.setTgznum(financial.getTgznum() + statements.getTgznum());
			financial.setYjsnum(financial.getYjsnum() + statements.getYjsnum());
			financial.setDfknum(financial.getDfknum() + statements.getDfknum());
			financial.setFkznum(financial.getFkznum() + statements.getFkznum());
			financial.setYfknum(financial.getYfknum() + statements.getYfknum());
			financial.setBhnum(financial.getBhnum() + statements.getBhnum());
			financial.setJjfknum(financial.getJjfknum() + statements.getJjfknum());
			financial.setJjnum(financial.getJjnum() + statements.getJjnum());
			financial.setDaynum(financial.getDaynum() + statements.getDaynum());
			financial.setJjl(financial.getJjl()+statements.getJjl());
			financial.setSsje(financial.getSsje()+statements.getSsje());
			financial.setYsje(financial.getYsje() + statements.getYsje());
			financial.setKdj(financial.getKdj()+statements.getYfknum());
		}
		result.getList().add(financial);

		return result;
	}

	@RequestMapping("/queryFinancialStatementsForExcel")
	@ResponseBody
	public void queryFinancialStatementsForExcel(HttpServletRequest request, QueryFinancialVo vo, HttpServletResponse response) {
		SimpleAuthorization sa = (SimpleAuthorization) request.getSession().getAttribute("userAuthorization");
		Map<String, Object> map = new HashMap<String, Object>();
		String order = "";
		if (StringUtils.isNotBlank(vo.getSsje())) {
			order += "ssje " + vo.getSsje();
		} else if (StringUtils.isNotBlank(vo.getDaynum())) {
			order += "daynum " + vo.getDaynum();
		} else {
			order += "totle " + (StringUtils.isBlank(vo.getTotle()) ? "desc" : vo.getTotle());
		}
		vo.setOrder(order);
		map.put("sa", sa);
		map.put("vo", vo);
		List<FinancialStatements> list = OrderService.getFinancialStatementsForExcel(map);
		FinancialStatements financial = new FinancialStatements();

		financial.setUsername("合计");
		for (FinancialStatements statements : list) {
			financial.setTotle(financial.getTotle() + statements.getTotle());
			financial.setDscnum(financial.getDscnum() + statements.getDscnum());
			financial.setJjjsnum(financial.getJjjsnum() + statements.getJjjsnum());
			financial.setDesnum(financial.getDesnum() + statements.getDesnum());
			financial.setTgznum(financial.getTgznum() + statements.getTgznum());
			financial.setYjsnum(financial.getYjsnum() + statements.getYjsnum());
			financial.setDfknum(financial.getDfknum() + statements.getDfknum());
			financial.setFkznum(financial.getFkznum() + statements.getFkznum());
			financial.setYfknum(financial.getYfknum() + statements.getYfknum());
			financial.setBhnum(financial.getBhnum() + statements.getBhnum());
			financial.setJjfknum(financial.getJjfknum() + statements.getJjfknum());
			financial.setJjnum(financial.getJjnum() + statements.getJjnum());
			financial.setDaynum(financial.getDaynum() + statements.getDaynum());

			financial.setJjl(financial.getJjl()+statements.getJjl());
			financial.setSsje(financial.getSsje()+statements.getSsje());
			financial.setYsje(financial.getYsje()+statements.getYsje());
			financial.setKdj(financial.getKdj()+statements.getYfknum());
		}
		list.add(financial);

		// 数据写入Excel
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("财务报表");
		// 通用样式
		XSSFCellStyle style = POIUtil.crateCellStyle(wb);
		// 第一行
		XSSFRow firstRow = sheet.createRow(0);
		XSSFCell c1 = firstRow.createCell(0);
		c1.setCellValue("序号");
		c1.setCellStyle(style);
		XSSFCell c2 = firstRow.createCell(1);
		c2.setCellValue("业务员");
		c2.setCellStyle(style);
		XSSFCell c3 = firstRow.createCell(2);
		c3.setCellValue("提报数量");
		c3.setCellStyle(style);
		XSSFCell c4 = firstRow.createCell(3);
		c4.setCellValue("有效单");
		c4.setCellStyle(style);
		XSSFCell c5 = firstRow.createCell(4);
		c5.setCellValue("驳回");
		c5.setCellStyle(style);
		XSSFCell c6 = firstRow.createCell(5);
		c6.setCellValue("拒绝率");
		c6.setCellStyle(style);
		XSSFCell c7 = firstRow.createCell(6);
		c7.setCellValue("拒绝");
		c7.setCellStyle(style);
		XSSFCell c8 = firstRow.createCell(7);
		c8.setCellValue("待二审");
		c8.setCellStyle(style);
		XSSFCell c9 = firstRow.createCell(8);
		c9.setCellValue("推广中");
		c9.setCellStyle(style);
		XSSFCell c10 = firstRow.createCell(9);
		c10.setCellValue("已结束");
		c10.setCellStyle(style);
		XSSFCell c11 = firstRow.createCell(10);
		c11.setCellValue("待付款");
		c11.setCellStyle(style);
		XSSFCell c12 = firstRow.createCell(11);
		c12.setCellValue("付款中");
		c12.setCellStyle(style);
		XSSFCell c13 = firstRow.createCell(12);
		c13.setCellValue("拒绝付款");
		c13.setCellStyle(style);
		XSSFCell c14 = firstRow.createCell(13);
		c14.setCellValue("已付款");
		c14.setCellStyle(style);
		XSSFCell c15 = firstRow.createCell(14);
		c15.setCellValue("客单价");
		c15.setCellStyle(style);
		XSSFCell c16 = firstRow.createCell(15);
		c16.setCellValue("实收金额");
		c16.setCellStyle(style);
		XSSFCell c17 = firstRow.createCell(16);
		c17.setCellValue("应收金额");
		c17.setCellStyle(style);
		// 写入数据
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = 0; i < list.size(); i++) {
			FinancialStatements tr = list.get(i);
			row = sheet.createRow(i + 1);
			cell = row.createCell(0);
			cell.setCellValue(i + 1);
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(tr.getUsername());
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(tr.getTotle());
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(tr.getDaynum());
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue(tr.getBhnum());
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(tr.getJjl()+ "");
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue(tr.getJjnum());
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue(tr.getDesnum());
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue(tr.getTgznum());
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue(tr.getYjsnum());
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue(tr.getDfknum());
			cell.setCellStyle(style);
			cell = row.createCell(11);
			cell.setCellValue(tr.getFkznum());
			cell.setCellStyle(style);
			cell = row.createCell(12);
			cell.setCellValue(tr.getJjfknum());
			cell.setCellStyle(style);
			cell = row.createCell(13);
			cell.setCellValue(tr.getYfknum());
			cell.setCellStyle(style);
			cell = row.createCell(14);
			cell.setCellValue(tr.getKdj());
			cell.setCellStyle(style);
			cell = row.createCell(15);
			cell.setCellValue(tr.getSsje());
			cell.setCellStyle(style);
			cell = row.createCell(16);
			cell.setCellValue(tr.getYsje());
			cell.setCellStyle(style);
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 生成流对象
		try {
			wb.write(byteArrayOutputStream);// 将excel写入流
			// 工具类，封装弹出下载框：
			String outFile = "财务报表.xlsx";
			DownloadUtil down = new DownloadUtil();
			down.download(byteArrayOutputStream, request, response, outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
