package com.busilinq.data.entity;

import java.io.Serializable;



/**
 * 订单收货地址
 * 
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-06 17:51:10
 */
public class OrderAddressEntity extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	        private int addrId;
		//
		//
		//
		//关联订单ID
	        private int orderId;
		//收货人姓名
	        private String name;
		//收货人电话
	        private String cell;
		//收货人地址
	        private String address;
	

	        /**
         * 设置：
         */
        public void setAddrId(int addrId) {
            this.addrId = addrId;
        }
        /**
         * 获取：
         */
        public int getAddrId() {
            return addrId;
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
         * 设置：收货人姓名
         */
        public void setName(String name) {
            this.name = name;
        }
        /**
         * 获取：收货人姓名
         */
        public String getName() {
            return name;
        }
	


	        /**
         * 设置：收货人电话
         */
        public void setCell(String cell) {
            this.cell = cell;
        }
        /**
         * 获取：收货人电话
         */
        public String getCell() {
            return cell;
        }
	


	        /**
         * 设置：收货人地址
         */
        public void setAddress(String address) {
            this.address = address;
        }
        /**
         * 获取：收货人地址
         */
        public String getAddress() {
            return address;
        }
	

}
