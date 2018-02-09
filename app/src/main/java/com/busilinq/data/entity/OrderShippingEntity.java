package com.busilinq.data.entity;

import java.io.Serializable;



/**
 * 订单支付和配送方式
 * 
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-06 17:51:10
 */
public class OrderShippingEntity extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	        private int shippingId;
		//
		//
		//
		//关联订单ID
	        private int orderId;
		//配送方式
	        private String shippingType;
		//配送状态
	        private String shippingStatus;
		//支付方式 支付方式:0(默认,代表未支付),1(支付宝扫码),11(支付宝反扫),2(微信扫码),22(微信反扫),3(苹果支付),4(银联),其他暂略
	        private String payType;
		//支付状态
	        private String payStatus;
		//支付通道0线上支付
	        private String payChannel;
	

	        /**
         * 设置：
         */
        public void setShippingId(int shippingId) {
            this.shippingId = shippingId;
        }
        /**
         * 获取：
         */
        public int getShippingId() {
            return shippingId;
        }
	


	


	


	


	        /**
         * 设置：关联订单ID
         */
        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
        /**
         * 获取：关联订单ID
         */
        public int getOrderId() {
            return orderId;
        }
	


	        /**
         * 设置：配送方式
         */
        public void setShippingType(String shippingType) {
            this.shippingType = shippingType;
        }
        /**
         * 获取：配送方式
         */
        public String getShippingType() {
            return shippingType;
        }
	


	        /**
         * 设置：配送状态
         */
        public void setShippingStatus(String shippingStatus) {
            this.shippingStatus = shippingStatus;
        }
        /**
         * 获取：配送状态
         */
        public String getShippingStatus() {
            return shippingStatus;
        }
	


	        /**
         * 设置：支付方式 支付方式:0(默认,代表未支付),1(支付宝扫码),11(支付宝反扫),2(微信扫码),22(微信反扫),3(苹果支付),4(银联),其他暂略
         */
        public void setPayType(String payType) {
            this.payType = payType;
        }
        /**
         * 获取：支付方式 支付方式:0(默认,代表未支付),1(支付宝扫码),11(支付宝反扫),2(微信扫码),22(微信反扫),3(苹果支付),4(银联),其他暂略
         */
        public String getPayType() {
            return payType;
        }
	


	        /**
         * 设置：支付状态
         */
        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }
        /**
         * 获取：支付状态
         */
        public String getPayStatus() {
            return payStatus;
        }
	


	        /**
         * 设置：支付通道0线上支付
         */
        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }
        /**
         * 获取：支付通道0线上支付
         */
        public String getPayChannel() {
            return payChannel;
        }
	

}
