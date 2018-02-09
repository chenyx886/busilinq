package com.busilinq.data.entity;

import java.io.Serializable;




/**
 * 订单表
 * 
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @String 2018-02-06 17:51:10
 */
public class OrderEntity extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	        private int orderId;
		//关联下单用户
	        private int userId;
		//冗余用户名
	        private String userName;
		//订单号
	        private String orderNumber;
		//下单时间
	        private String orderTime;
		//支付总金额
	        private double totalMoney;
		//支付金额
	        private double payMoney;
		//优惠金额
	        private double discountMoney;
		//订单状态 WAIT_PAY待支付 WAIT_VERIFY待审核 WAIT_CARGO待出库 WAIT_DELIVER待发货 WAIT_RECEIVE待接收 COMPLETE完成 REFUND退款
	        private String status;
		//发票开票标志0未开票 1已经开票
	        private int isInvoice;
		//订单有效时间:未付款3小时取消订单；已经付款9天未发货退单；已经发货15天自动完成订单
	        private String expString;
		//备注信息
	        private String remark;
	

	        /**
         * 设置：
         */
        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
        /**
         * 获取：
         */
        public int getOrderId() {
            return orderId;
        }
	


	


	


	


	        /**
         * 设置：关联下单用户
         */
        public void setUserId(int userId) {
            this.userId = userId;
        }
        /**
         * 获取：关联下单用户
         */
        public int getUserId() {
            return userId;
        }
	


	        /**
         * 设置：冗余用户名
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }
        /**
         * 获取：冗余用户名
         */
        public String getUserName() {
            return userName;
        }
	


	        /**
         * 设置：订单号
         */
        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }
        /**
         * 获取：订单号
         */
        public String getOrderNumber() {
            return orderNumber;
        }
	


	        /**
         * 设置：下单时间
         */
        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }
        /**
         * 获取：下单时间
         */
        public String getOrderTime() {
            return orderTime;
        }
	


	        /**
         * 设置：支付总金额
         */
        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }
        /**
         * 获取：支付总金额
         */
        public double getTotalMoney() {
            return totalMoney;
        }
	


	        /**
         * 设置：支付金额
         */
        public void setPayMoney(double payMoney) {
            this.payMoney = payMoney;
        }
        /**
         * 获取：支付金额
         */
        public double getPayMoney() {
            return payMoney;
        }
	


	        /**
         * 设置：优惠金额
         */
        public void setDiscountMoney(double discountMoney) {
            this.discountMoney = discountMoney;
        }
        /**
         * 获取：优惠金额
         */
        public double getDiscountMoney() {
            return discountMoney;
        }
	


	        /**
         * 设置：订单状态 WAIT_PAY待支付 WAIT_VERIFY待审核 WAIT_CARGO待出库 WAIT_DELIVER待发货 WAIT_RECEIVE待接收 COMPLETE完成 REFUND退款
         */
        public void setStatus(String status) {
            this.status = status;
        }
        /**
         * 获取：订单状态 WAIT_PAY待支付 WAIT_VERIFY待审核 WAIT_CARGO待出库 WAIT_DELIVER待发货 WAIT_RECEIVE待接收 COMPLETE完成 REFUND退款
         */
        public String getStatus() {
            return status;
        }
	


	        /**
         * 设置：发票开票标志0未开票 1已经开票
         */
        public void setIsInvoice(int isInvoice) {
            this.isInvoice = isInvoice;
        }
        /**
         * 获取：发票开票标志0未开票 1已经开票
         */
        public int getIsInvoice() {
            return isInvoice;
        }
	


	        /**
         * 设置：订单有效时间:未付款3小时取消订单；已经付款9天未发货退单；已经发货15天自动完成订单
         */
        public void setExpString(String expString) {
            this.expString = expString;
        }
        /**
         * 获取：订单有效时间:未付款3小时取消订单；已经付款9天未发货退单；已经发货15天自动完成订单
         */
        public String getExpString() {
            return expString;
        }
	


	        /**
         * 设置：备注信息
         */
        public void setRemark(String remark) {
            this.remark = remark;
        }
        /**
         * 获取：备注信息
         */
        public String getRemark() {
            return remark;
        }
	

}
