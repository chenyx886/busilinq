package com.busilinq.data.entity;

import java.io.Serializable;



/**
 * 其他服务地址表-临时
 * 
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-07 11:21:34
 */
public class TServiceAccountEntity extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	        private int accountId;
		//
		//
		//
		//服务名称
	        private String name;
		//服务地址
	        private String url;
		//是否启用
	        private int isEnable;
		//备注信息
	        private String remark;
	

	        /**
         * 设置：
         */
        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }
        /**
         * 获取：
         */
        public int getAccountId() {
            return accountId;
        }
	


	


	


	


	        /**
         * 设置：服务名称
         */
        public void setName(String name) {
            this.name = name;
        }
        /**
         * 获取：服务名称
         */
        public String getName() {
            return name;
        }
	


	        /**
         * 设置：服务地址
         */
        public void setUrl(String url) {
            this.url = url;
        }
        /**
         * 获取：服务地址
         */
        public String getUrl() {
            return url;
        }
	


	        /**
         * 设置：是否启用
         */
        public void setIsEnable(int isEnable) {
            this.isEnable = isEnable;
        }
        /**
         * 获取：是否启用
         */
        public int getIsEnable() {
            return isEnable;
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
