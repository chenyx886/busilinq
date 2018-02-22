package com.busilinq.data.entity;

/**
 * 订单表
 *
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @String 2018-02-06 17:51:10
 */
public class OrderEntity extends BaseEntity {

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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(int isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getExpString() {
        return expString;
    }

    public void setExpString(String expString) {
        this.expString = expString;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
