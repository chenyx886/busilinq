package com.busilinq.ulits;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：yuzhenxing
 * Create Time：2018/2/8 16:04
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class OrderStringParse {
    public static String parseOrderState(String status) {
        String parse = "";
        switch (status) {
            case "WAIT_PAY":
                parse = "待支付";
                break;
            case "WAIT_VERIFY":
                parse = "待出库";
                break;
            case "WAIT_DELIVER":
                parse = "待发货";
                break;
            case "WAIT_RECEIVE":
                parse = "待接收";
                break;
            case "COMPLETE":
                parse = "完成";
                break;
            case "REFUND":
                parse = "退款";
                break;
        }
        return parse;
    }
}
