/**  
* @Title: Item.java
* @Package com.taosearch.model
* @Description: TODO(用一句话描述该文件做什么)
* @author jiajia  
* @date 2017年3月13日 上午9:42:37
* @version V1.0  
*/
package com.taosearch.model;

/**
* @ClassName: Item
* @Description: 商品信息类
* @author jiajia
* @date 2017年3月13日 上午9:42:37
*
*/

public class Item {

	private String item_id;// 商品id
	private String item_no;// 商品编号
	private String item_url;// 商品URL
	private String item_name;// 商品名称
	private String item_md;// 商品卖点
	private String item_title;/// 商品title
	private String item_type;// 商品分类001 女装 002 男装 003 内衣 004 母婴 005 化妆品 006 居家 007 鞋包配饰 008 美食 009 文体车品 010 数码家电
	private String item_hdlx;// 活动类型001普通活动002淘抢购003聚划算
	private Double item_yjbl;// 佣金比例
	private String user_id;// 上传用户id
	private String username;// 用户名
	private String createtime;// 上传时间
	private String updatetime;// 更改时间
	private Double item_qhjg;// 券后价格
	private String state;
	// 状态'001':'待审核';'002':'待二审';'003':'推广中';'004':'已结束';'005':'待付款';
	// '006':'付款中';'007':'已付款';'112':'驳回';'667':'拒绝付款';'999':'拒绝';
	//008:审核中，009：即将结束
	private Double item_fwdj;// 服务单价
	private String coupon_id;// 优惠券id
	private String item_image;// 商品主图URL
	private String phone;// 联系qq
	private String qq;// 联系qq
	private String remark;// 备注
	private String item_image_backup;// 补充主图
	private String item_main_image;//
	private String item_website_type;// 商品网站类型 1淘宝 2天猫
	private String coupon_type_id;// 优惠券类型
	private String coupon_url;// 优惠券地址
	private String activity_start_time;// 活动开始时间
	private String coupon_start_time;// 优惠券有效期开始时间
	private String coupon_end_time;// 优惠券有效期结束时间
	private String item_jhlb;// 计划类别001 通用 002 定向 003 鹊桥
	private String item_jhlj;// 计划连接定向时候需要填写
	private int coupon_rest_num;// 优惠券剩余数量
	private int coupon_get_num;// 优惠券领取数量
	private int coupon_use_num;// 优惠券使用数量
	private boolean showClaim;// 显示认领
	private String item_ssje;// 实收金额
	private String item_zfje;// 支付金额
	private String item_sflx;// 收费类型 0线下结算 1团长结算
	private String item_generalize_main_image;// 推广主图
	private String real_image;// 买家实拍图
	private String real_image_text;// 实拍图文案
	private String friend_text;// 朋友圈文案
	private String head_id;// 团长ID

	public String getItem_zfje() {
		return item_zfje;
	}

	public void setItem_zfje(String item_zfje) {
		this.item_zfje = item_zfje;
	}

	public String getItem_ssje() {
		return item_ssje;
	}

	public void setItem_ssje(String item_ssje) {
		this.item_ssje = item_ssje;
	}

	public boolean isShowClaim() {
		return showClaim;
	}

	public void setShowClaim(boolean showClaim) {
		this.showClaim = showClaim;
	}

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

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_hdlx() {
		return item_hdlx;
	}

	public void setItem_hdlx(String item_hdlx) {
		this.item_hdlx = item_hdlx;
	}

	public Double getItem_yjbl() {
		return item_yjbl;
	}

	public void setItem_yjbl(Double item_yjbl) {
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

	public Double getItem_qhjg() {
		return item_qhjg;
	}

	public void setItem_qhjg(Double item_qhjg) {
		this.item_qhjg = item_qhjg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getItem_fwdj() {
		return item_fwdj;
	}

	public void setItem_fwdj(Double item_fwdj) {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getCoupon_type_id() {
		return coupon_type_id;
	}

	public void setCoupon_type_id(String coupon_type_id) {
		this.coupon_type_id = coupon_type_id;
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

	public String getItem_sflx() {
		return item_sflx;
	}

	public void setItem_sflx(String item_sflx) {
		this.item_sflx = item_sflx;
	}

	public int getCoupon_use_num() {
		return coupon_use_num;
	}

	public void setCoupon_use_num(int coupon_use_num) {
		this.coupon_use_num = coupon_use_num;
	}

	public String getItem_generalize_main_image() {
		return item_generalize_main_image;
	}

	public void setItem_generalize_main_image(String item_generalize_main_image) {
		this.item_generalize_main_image = item_generalize_main_image;
	}

	public String getReal_image() {
		return real_image;
	}

	public void setReal_image(String real_image) {
		this.real_image = real_image;
	}

	public String getReal_image_text() {
		return real_image_text;
	}

	public void setReal_image_text(String real_image_text) {
		this.real_image_text = real_image_text;
	}

	public String getFriend_text() {
		return friend_text;
	}

	public void setFriend_text(String friend_text) {
		this.friend_text = friend_text;
	}

	public String getHead_id() {
		return head_id;
	}

	public void setHead_id(String head_id) {
		this.head_id = head_id;
	}
}
