/**  
* @Title: ItemInfo.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月22日 下午4:21:57
* @version V1.0  
*/
package com.taosearch.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
* @ClassName: ItemInfo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月22日 下午4:21:57
*
*/
@Getter
@Setter
public class ItemInfo {
	private String item_id;// 商品id
	private String item_no;// 商品编号
	private String item_url;// 商品URL
	private String item_name;// 商品名称
	private String item_md;// 商品卖点
	private String item_title;/// 商品title
	private String item_type;// 商品分类001 女装 002 男装 003 内衣 004 母婴 005 化妆品 006 居家 007 鞋包配饰 008 美食 009 文体车品 010 数码家电
	private String item_hdlx;// 活动类型001普通活动002淘抢购003聚划算
	private double item_yjbl;// 佣金比例
	private String user_id;// 上传用户id
	private String username;// 用户名
	private String createtime;// 上传时间
	private String updatetime;// 更改时间
	private double item_qhjg;// 券后价格
	private String state;// 状态'001':'待审核';'002':'待二审';'003':'推广中';'004':'已结束';'005':'待付款';'006':'付款中';'007':'已付款';'112':'驳回';'667':'拒绝付款';'999':'拒绝';
	private double item_fwdj;// 服务单价
	private String coupon_id;// 优惠券id
	private String item_image;// 商品主图URL
	private String qq;// 联系qq
	private String remark;// 备注
	private String item_image_backup;// 补充主图
	private String item_website_type;// 商品网站类型 1淘宝 2天猫
	private String coupon_url;// 优惠券地址
	private String activity_start_time;// 活动开始时间
	private String coupon_start_time;// 优惠券有效期开始时间
	private String coupon_end_time;// 优惠券有效期结束时间
	private String item_jhlb;// 计划类别001 通用 002 定向 003 鹊桥
	private String item_jhlj;// 计划连接定向时候需要填写
	private int coupon_rest_num;// 优惠券剩余数量
	private int coupon_get_num;// 优惠券领取数量
	private int coupon_use_num;// 优惠券使用数量
	private double item_zfje;//支付金额 客户为这个商品支付的总额
	private double item_ssje;//实收金额
	private String item_zfje_state;
	private List<ItemAttachment> attachments;// 商品附件
	private List<ItemAuditLog> logs;// 商品审核记录
}
