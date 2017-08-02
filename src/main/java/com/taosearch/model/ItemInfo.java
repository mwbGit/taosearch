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

/**
* @ClassName: ItemInfo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jiajia
* @date 2017年3月22日 下午4:21:57
*
*/

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
	private String item_main_image;// 补充主图
	private String item_website_type;// 商品网站类型 1淘宝 2天猫
	private String coupon_url;// 优惠券地址
	private String activity_start_time;// 活动开始时间
	private String coupon_start_time;// 优惠券有效期开始时间
	private String coupon_end_time;// 优惠券有效期结束时间
	private String item_jhlb;// 计划类别001 通用 002 定向 003 鹊桥
	private String item_jhlj;// 计划连接定向时候需要填写
	private String coupon_type_id;// 优惠券类型1普通2阿里妈妈
	private int coupon_rest_num;// 优惠券剩余数量
	private int coupon_get_num;// 优惠券领取数量
	private int coupon_use_num;// 优惠券使用数量
	private double item_zfje;//支付金额 客户为这个商品支付的总额
	private double item_ssje;//实收金额
	private String item_zfje_state;
	private List<ItemAttachment> attachments;// 商品附件
	private List<ItemAuditLog> logs;// 商品审核记录
	private String item_merge;

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public String getItem_url() {
		return item_url;
	}

	public void setItem_url(String item_url) {
		this.item_url = item_url;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_md() {
		return item_md;
	}

	public void setItem_md(String item_md) {
		this.item_md = item_md;
	}

	public String getItem_title() {
		return item_title;
	}

	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}

	public String getItem_type() {
		return item_type;
	}

	public String getCoupon_type_id() {
		return coupon_type_id;
	}

	public void setCoupon_type_id(String coupon_type_id) {
		this.coupon_type_id = coupon_type_id;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_hdlx() {
		return item_hdlx;
	}

	public void setItem_hdlx(String item_hdlx) {
		this.item_hdlx = item_hdlx;
	}

	public double getItem_yjbl() {
		return item_yjbl;
	}

	public void setItem_yjbl(double item_yjbl) {
		this.item_yjbl = item_yjbl;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public double getItem_qhjg() {
		return item_qhjg;
	}

	public void setItem_qhjg(double item_qhjg) {
		this.item_qhjg = item_qhjg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getItem_fwdj() {
		return item_fwdj;
	}

	public void setItem_fwdj(double item_fwdj) {
		this.item_fwdj = item_fwdj;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getItem_image() {
		return item_image;
	}

	public void setItem_image(String item_image) {
		this.item_image = item_image;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItem_image_backup() {
		return item_image_backup;
	}

	public void setItem_image_backup(String item_image_backup) {
		this.item_image_backup = item_image_backup;
	}

	public String getItem_main_image() {
		return item_main_image;
	}

	public void setItem_main_image(String item_main_image) {
		this.item_main_image = item_main_image;
	}

	public String getItem_website_type() {
		return item_website_type;
	}

	public void setItem_website_type(String item_website_type) {
		this.item_website_type = item_website_type;
	}

	public String getCoupon_url() {
		return coupon_url;
	}

	public void setCoupon_url(String coupon_url) {
		this.coupon_url = coupon_url;
	}

	public String getActivity_start_time() {
		return activity_start_time;
	}

	public void setActivity_start_time(String activity_start_time) {
		this.activity_start_time = activity_start_time;
	}

	public String getCoupon_start_time() {
		return coupon_start_time;
	}

	public void setCoupon_start_time(String coupon_start_time) {
		this.coupon_start_time = coupon_start_time;
	}

	public String getCoupon_end_time() {
		return coupon_end_time;
	}

	public void setCoupon_end_time(String coupon_end_time) {
		this.coupon_end_time = coupon_end_time;
	}

	public String getItem_jhlb() {
		return item_jhlb;
	}

	public void setItem_jhlb(String item_jhlb) {
		this.item_jhlb = item_jhlb;
	}

	public String getItem_jhlj() {
		return item_jhlj;
	}

	public void setItem_jhlj(String item_jhlj) {
		this.item_jhlj = item_jhlj;
	}

	public int getCoupon_rest_num() {
		return coupon_rest_num;
	}

	public void setCoupon_rest_num(int coupon_rest_num) {
		this.coupon_rest_num = coupon_rest_num;
	}

	public int getCoupon_get_num() {
		return coupon_get_num;
	}

	public void setCoupon_get_num(int coupon_get_num) {
		this.coupon_get_num = coupon_get_num;
	}

	public int getCoupon_use_num() {
		return coupon_use_num;
	}

	public void setCoupon_use_num(int coupon_use_num) {
		this.coupon_use_num = coupon_use_num;
	}

	public double getItem_zfje() {
		return item_zfje;
	}

	public void setItem_zfje(double item_zfje) {
		this.item_zfje = item_zfje;
	}

	public double getItem_ssje() {
		return item_ssje;
	}

	public void setItem_ssje(double item_ssje) {
		this.item_ssje = item_ssje;
	}

	public String getItem_zfje_state() {
		return item_zfje_state;
	}

	public void setItem_zfje_state(String item_zfje_state) {
		this.item_zfje_state = item_zfje_state;
	}

	public List<ItemAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ItemAttachment> attachments) {
		this.attachments = attachments;
	}

	public List<ItemAuditLog> getLogs() {
		return logs;
	}

	public void setLogs(List<ItemAuditLog> logs) {
		this.logs = logs;
	}

	public String getItem_merge() {
		return item_merge;
	}

	public void setItem_merge(String item_merge) {
		this.item_merge = item_merge;
	}
}
