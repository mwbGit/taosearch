/**
 * @Title: OrderServiceImpl.java
 * @Package com.taosearch.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiajia
 * @date 2017年3月13日 下午2:22:49
 * @version V1.0
 */
package com.taosearch.service.impl;

import com.taosearch.dao.OrderDao;
import com.taosearch.dao.ShopDao;
import com.taosearch.model.*;
import com.taosearch.service.OrderService;
import com.taosearch.service.ShopService;
import com.taosearch.util.ConfigUtils;
import com.taosearch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jiajia
 * @date 2017年3月13日 下午2:22:49
 *
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao OrderDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ShopService shopService;

    @Override
    public List<ItemType> getItemTypes() {
        return OrderDao.getItemTypes();
    }

    @Override
    public List<SysUser> getItemUsers(String item_no) {
        return OrderDao.getItemUsers(item_no);
    }

    @Override
    public MyResult<String> saveItemInfo(Item item) {
        MyResult<String> result = new MyResult<String>();

        int num1 = OrderDao.saveCouponInfo(item);
        if (num1 <= 0) {
            result.setCode(9999);
            result.setMessage("优惠券信息入库未成功");
            return result;
        }
        int num2 = OrderDao.saveItemInfo(item);
        if (num2 <= 0) {
            result.setCode(9999);
            result.setMessage("商品信息入库未成功");
            return result;
        }
        result.setCode(1);
        result.setMessage("数据提交成功");
        shopService.updateStatistic(item.getItem_id());
        return result;
    }

    @Override
    public int getItemUrlCount(String item_url) {
        return OrderDao.getItemUrlCount(item_url);
    }

    @Override
    public List<Item> getItemListForPage(Map<String, Object> map) {
        return OrderDao.getItemListForPage(map);
    }

    @Override
    public int getItemCount(Map<String, Object> map) {
        return OrderDao.getItemCount(map);
    }

    @Override
    public ItemInfo getitemInfoById(String item_id) {
        ItemInfo itemInfo = OrderDao.getitemInfoById(item_id);
        if (itemInfo != null && itemInfo.getShop_id() != null) {
            Shop shop = shopDao.getShopById(itemInfo.getShop_id());
            if (shop != null) {
                itemInfo.setShop_name(shop.getName());
            }
        }
        return itemInfo;
    }

    @Override
    public MyResult<String> saveauditLog(HttpServletRequest request, ItemAuditLog auditLog, Coupon coupon) {
        MyResult<String> result = new MyResult<String>();
        SysUser LoginUser = (SysUser) request.getSession().getAttribute("loginUser");
        auditLog.setAuditor(LoginUser.getUsername());
        if (checkCoupon(coupon)) {
            OrderDao.updateCoupon(coupon);
        }
        int i = OrderDao.updateItemState(auditLog);
        if (i > 0) {
            int n = OrderDao.saveAuditLog(auditLog);
            if (n > 0) {
                result.setCode(1);
                result.setMessage("操作成功");
            } else {
                result.setCode(998);
                result.setMessage("日志保存失败");
            }
        } else {
            result.setCode(999);
            result.setMessage("状态更改失败");
        }

        return result;
    }

    @Override
    public int updateCoupon(Coupon coupon) {
        return OrderDao.updateCoupon(coupon);
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

    @Override
    public String upload(HttpServletRequest request) {
        String img = null;
        String configPath = ConfigUtils.getProp("res_path");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            MultipartFile file = multiRequest.getFile("file");// 获取多个input
            if (file != null && !file.isEmpty()) {
                // 取得当前上传文件的文件名称
                String myFileName = file.getOriginalFilename();
                // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if (myFileName.trim() != "") {
                    System.out.println(myFileName);
                    // 重命名上传后的文件名
                    int idx = myFileName.indexOf(".");
                    String subName = Util.getUUID();
                    String extName = myFileName.substring(idx, myFileName.length());
                    String fileName = Util.dateToStr(new Date(), "yyyyMMddHHmmssSSS") + subName + extName.toLowerCase();
                    // 定义上传路径
                    String path = configPath + "taosearch" + java.io.File.separator + fileName;
                    File localFile = new File(path);
                    if (!localFile.exists()) {
                        localFile.mkdirs();
                    }
                    try {
                        file.transferTo(localFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String url = "http://" + request.getServerName() //服务器地址
                            + ":" + request.getServerPort()         //端口号
                            + "/" + request.getContextPath();
                    img = url + "/img/download?dir=taosearch&img=" + fileName;
                }
            }

        }
        return img;
    }

    @Override
    public MyResult<ItemInfo> saveItemAttachemt(ItemAuditLog auditLog, ItemInfo item, HttpServletRequest request) {
        MyResult<ItemInfo> result = new MyResult<ItemInfo>();
        SysUser user = (SysUser) request.getSession().getAttribute("loginUser");
        // 上传文件的保存路径
        String configPath = ConfigUtils.getProp("res_path");
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();// 获取多个input
            while (iter.hasNext()) {
                List<MultipartFile> files = multiRequest.getFiles(iter.next());// 获取每个input中所有的上传文件名
                // 取得上传文件
                for (MultipartFile file : files) {
                    if (file != null) {
                        // 取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            System.out.println(myFileName);
                            // 重命名上传后的文件名
                            int idx = myFileName.indexOf(".");
                            String subName = Util.getUUID();
                            String extName = myFileName.substring(idx, myFileName.length());
                            String fileName = Util.dateToStr(new Date(), "yyyyMMddHHmmssSSS") + subName + extName.toLowerCase();
                            // 定义上传路径
                            String path = configPath + "taosearch" + java.io.File.separator + fileName;
                            File localFile = new File(path);
                            if (!localFile.exists()) {
                                localFile.mkdirs();
                            }
                            try {
                                file.transferTo(localFile);
                                ItemAttachment attachment = new ItemAttachment();
                                attachment.setItem_id(auditLog.getItem_id());
                                attachment.setAttachment_value("dir=taosearch&img=" + fileName);
                                attachment.setAttachment_user_name(user.getUsername());
                                OrderDao.saveItemAttachemt(attachment);
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                                result.setCode(8888);
                                result.setMessage("网络异常，请重试");
                                return result;
                            } catch (IOException e) {
                                e.printStackTrace();
                                result.setCode(9999);
                                result.setMessage("文件存储失败异常，请重试");
                                return result;
                            }
                        }
                    }

                }
            }

        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("item_id", item.getItem_id());
        map.put("item_zfje", item.getItem_zfje());
        map.put("item_ssje", item.getItem_ssje());
        map.put("after_audit_status", "006");
        map.put("item_merge", item.getItem_merge());
        map.put("audit_remarks", auditLog.getAudit_remarks());
        int i = OrderDao.updateItemzfje(map);
        shopService.updateStatistic(item.getItem_id());
        if (i > 0) {
            auditLog.setItem_id(item.getItem_id());
            auditLog.setBefore_audit_status("005");
            auditLog.setAfter_audit_status("006");
            auditLog.setAuditor(user.getUsername());
            int n = OrderDao.saveAuditLog(auditLog);
            if (n > 0) {
                item = OrderDao.getitemInfoById(item.getItem_id());

                Coupon coupon = new Coupon();
                coupon.setCoupon_id(item.getCoupon_id());
                coupon.setCoupon_get_num(item.getCoupon_get_num());
                Double useNum = item.getItem_zfje() / item.getItem_qhjg();
                coupon.setCoupon_use_num(useNum.intValue());

                OrderDao.updateCoupon(coupon);

                result.setData(item);
                result.setCode(1);
                result.setMessage("操作成功");
            } else {
                result.setCode(998);
                result.setMessage("日志保存失败");
            }
        } else {
            result.setCode(4444);
            result.setMessage("支付金额保存失败");
        }
        return result;
    }

    @Override
    public MyResult<String> saveItemAttachemt1(ItemAuditLog auditLog, Double item_ssje, HttpServletRequest request) {
        MyResult<String> result = new MyResult<String>();
        SysUser user = (SysUser) request.getSession().getAttribute("loginUser");
        // 上传文件的保存路径
        String configPath = ConfigUtils.getProp("res_path");
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();// 获取多个input
            while (iter.hasNext()) {
                List<MultipartFile> files = multiRequest.getFiles(iter.next());// 获取每个input中所有的上传文件名
                // 取得上传文件
                for (MultipartFile file : files) {
                    if (file != null) {
                        // 取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            System.out.println(myFileName);
                            // 重命名上传后的文件名
                            int idx = myFileName.indexOf(".");
                            String subName = Util.getUUID();
                            String extName = myFileName.substring(idx, myFileName.length());
                            String fileName = Util.dateToStr(new Date(), "yyyyMMddHHmmssSSS") + subName + extName.toLowerCase();
                            // 定义上传路径
                            String path = configPath + "taosearch" + java.io.File.separator + fileName;
                            File localFile = new File(path);
                            if (!localFile.exists()) {
                                localFile.mkdirs();
                            }
                            try {
                                file.transferTo(localFile);
                                ItemAttachment attachment = new ItemAttachment();
                                attachment.setItem_id(auditLog.getItem_id());
                                attachment.setAttachment_value("dir=taosearch&img=" + fileName);
                                attachment.setAttachment_user_name(user.getUsername());
                                OrderDao.saveItemAttachemt(attachment);
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                                result.setCode(8888);
                                result.setMessage("网络异常，请重试");
                                return result;
                            } catch (IOException e) {
                                e.printStackTrace();
                                result.setCode(9999);
                                result.setMessage("文件存储失败异常，请重试");
                                return result;
                            }
                        }
                    }

                }
            }

        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("item_id", auditLog.getItem_id());
        map.put("item_ssje", item_ssje);
        map.put("after_audit_status", auditLog.getAfter_audit_status());
        map.put("audit_remarks", auditLog.getAudit_remarks());
        int i = OrderDao.updateItemssje(map);
        shopService.updateStatistic( auditLog.getItem_id());
        if (i > 0) {
            auditLog.setAuditor(user.getUsername());
            int n = OrderDao.saveAuditLog(auditLog);
            if (n > 0) {
                result.setCode(1);
                result.setMessage("操作成功");
            } else {
                result.setCode(998);
                result.setMessage("日志保存失败");
            }
        } else {
            result.setCode(4444);
            result.setMessage("支付金额保存失败");
        }
        return result;
    }

    @Override
    public List<NavStateCount> getnavstateinfo(SimpleAuthorization sa) {
        List<NavStateCount> stateCounts = OrderDao.getnavstateinfo(sa);
        List<NavStateCount> resoult = new ArrayList<>();
        Map<String, NavStateCount> map = new HashMap<>();

        for (NavStateCount stateCount : stateCounts) {
            map.put(stateCount.getState(), stateCount);
        }

        resoult.add(map.get("000"));
        resoult.add(map.get("001"));
        resoult.add(map.get("002"));
        resoult.add(map.get("003"));
        resoult.add(map.get("009"));
        resoult.add(map.get("004"));
        resoult.add(map.get("005"));
        resoult.add(map.get("006"));
        resoult.add(map.get("007"));
        resoult.add(map.get("112"));
        resoult.add(map.get("667"));
        resoult.add(map.get("999"));

        return resoult;
    }

    @Override
    public void updateItem(Item item) {
        OrderDao.updateItemAndAddLog(item);
    }

    @Override
    public MyResult<String> updateItemAndAddLog(ItemAuditLog auditLog, Item item) {
        MyResult<String> result = new MyResult<String>();
        int i = OrderDao.updateItemAndAddLog(item);
        if (i > 0) {
            int n = OrderDao.saveAuditLog(auditLog);
            if (n > 0) {
                result.setCode(1);
                result.setMessage("操作成功");
            } else {
                result.setCode(998);
                result.setMessage("日志保存失败");
            }
        } else {
            result.setCode(999);
            result.setMessage("商品更改失败");
        }

        return result;
    }

    @Override
    public List<FinancialStatements> getFinancialStatementsForPage(Map<String, Object> map) {
        return OrderDao.getFinancialStatementsForPage(map);
    }

    @Override
    public List<FinancialStatements> getFinancialStatementsForExcel(Map<String, Object> map) {
        return OrderDao.getFinancialStatementsForExcel(map);
    }

    @Override
    public int getFinancialStatementsCount(Map<String, Object> map) {
        return OrderDao.getFinancialStatementsCount(map);
    }

}
