package com.busilinq.xsm.ulits;

import android.content.Context;


import com.busilinq.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * Created by dingyi on 2016/11/20.
 */

public class StringParse {
    /**
     * 获取订单状态
     *
     * @param
     */

    public static String getOrderStatus(Context mContext, String status) {
        String sRet = null;
        int state = 0;
        try {
            state = Integer.parseInt(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case 10:
                sRet = mContext.getResources().getString(R.string.order_status_new);
                break;
            case 20:
                sRet = mContext.getResources().getString(R.string.order_status_add);
                break;
            case 24:
                sRet = mContext.getResources().getString(
                        R.string.order_status_add_failed);
                break;
            case 30:
                sRet = mContext.getResources().getString(
                        R.string.order_status_check);
                break;
            case 40:
                sRet = mContext.getResources().getString(
                        R.string.order_status_confirm);
                break;
            case 50:
                sRet = mContext.getResources().getString(
                        R.string.order_status_delivery);
                break;
            case 60:
                sRet = mContext.getResources().getString(
                        R.string.order_status_finish);
                break;
            case 90:
                sRet = mContext.getResources()
                        .getString(R.string.order_status_stop);
                break;
            default:
                sRet = mContext.getResources().getString(R.string.other);
                break;
        }
        return sRet;
    }


    /**
     * 获取订单支付状态
     *
     * @param
     */
    public static String getOrderPayStatus(Context mContext, String Str) {
        String sRet = null;
        int state = 0;
        try {
            state = Integer.parseInt(Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case 0:
                sRet = mContext.getResources().getString(R.string.pay_status_unpayed);
                break;
            case 1:
                sRet = mContext.getResources().getString(R.string.pay_status_payed);
                break;
            case 2:
                sRet = mContext.getResources().getString(R.string.pay_status_paying);
                break;
        }
        return sRet;
    }

    /**
     * 获取订单类型
     *
     * @param
     */
    public static String getOrderType(Context mContext, String Str) {
        String sRet = null;
        int state = 0;
        try {
            state = Integer.parseInt(Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case 10:
                sRet = mContext.getResources().getString(R.string.order_type_by_phone);
                break;
            case 20:
                sRet = mContext.getResources().getString(R.string.order_type_distribution);
                break;
            case 31:
                sRet = mContext.getResources().getString(R.string.order_type_by_internet);
                break;
            case 32:
                sRet = mContext.getResources().getString(R.string.order_type_by_tv);
                break;
            case 33:
                sRet = mContext.getResources().getString(R.string.order_type_mobile_phone);
                break;
            case 34:
                sRet = mContext.getResources().getString(R.string.order_type_by_terminal);
                break;
            default:
                sRet = mContext.getResources().getString(R.string.order_type_by_other);
                break;
        }
        return sRet;
    }


    /**
     * 获取卷烟经营类型
     *
     * @param
     */
    public static String getBrdType(Context mContext, String Str) {
        String sRet = null;
        int state = 0;
        try {
            state = Integer.parseInt(Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case 1:
                sRet = mContext.getResources().getString(R.string.brdtype_new);
                break;
            case 2:
                sRet = mContext.getResources().getString(R.string.brdtype_tight);
                break;
            case 3:
                sRet = mContext.getResources().getString(R.string.brdtype_nomal);
                break;
            case 9:
                sRet = mContext.getResources().getString(R.string.other);
                break;
        }
        return sRet;
    }

    /**
     * 获取卷烟经营类型
     *
     * @param
     */
    public static String getCustStatus(Context mContext, String Str) {
        String sRet = null;
        int state = 0;
        try {
            state = Integer.parseInt(Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case 01:
                sRet = mContext.getResources().getString(R.string.cust_status_new);
                break;
            case 02:
                sRet = mContext.getResources().getString(R.string.cust_status_effective);
                break;
            case 03:
                sRet = mContext.getResources().getString(R.string.cust_status_pause);
                break;
            case 04:
                sRet = mContext.getResources().getString(R.string.cust_status_invalid);
                break;
        }
        return sRet;
    }


    /**
     * 布尔类型转换
     *
     * @param
     */
    public static String getBoolean(Context mContext, String Str) {
        String sRet = null;
        int state = 0;
        try {
            state = Integer.parseInt(Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case 0:
                sRet = mContext.getResources().getString(R.string.cgt_boolean_no);
                break;
            case 1:
                sRet = mContext.getResources().getString(R.string.cgt_boolean_yes);
                break;
        }
        return sRet;
    }

    /**
     * 获取日期
     *
     * @param
     */
    public static String getDate(Context mContext, String dateStr, String type) {
        String sRet = null;
        String year = dateStr.substring(0, 4);
        String month = dateStr.substring(4, 6);
        String day = dateStr.substring(6, 8);
        if(null != type && type.equals(".")){
            sRet = year + type + month+ type + day;
        }else{
            sRet = year + mContext.getResources().getString(R.string.year)
                    + month + mContext.getResources().getString(R.string.month)
                    + day + mContext.getResources().getString(R.string.day);
        }
        return sRet;
    }

    public static String parseEPayOrderType(String type){
        String parse = "";
        switch (type){
            case "CZ":
                parse = "充值";
                break;
            case "TX":
                parse = "提现";
                break;
        }
        return parse;
    }

    public static String parseEPayOrderState(String state){
        String parse = "";
        switch (state){
            case "S":
                parse = "成功";
                break;
            case "F":
                parse = "失败";
                break;
            case "W":
                parse = "等待";
                break;
            case "I":
                parse = "处理中";
                break;
        }
        return parse;
    }

    /**
     * 格式化金额
     */
    public static String formatMoney(double money){
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("##,##0.00");
        return format.format(money);
    }

    public static String formatMoney(double money, String pattern){
        DecimalFormat format = new DecimalFormat();
        format.applyPattern(pattern);
        return format.format(money);
    }

    public static String formatMoney(String money){
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("##,##0.00");
        return format.format(Double.parseDouble(money));
    }

    public static String formatMoney(String money, String pattern){
        DecimalFormat format = new DecimalFormat();
        format.applyPattern(pattern);
        return format.format(Double.parseDouble(money));
    }

    /**
     *
     * 功能描述：去除字符串首部为"0"字符

     * @param str 传入需要转换的字符串
     * @return 转换后的字符串
     */
    public static String removeZero(String str){
        char  ch;
        String result = "";
        if(str != null && str.trim().length()>0 && !str.trim().equalsIgnoreCase("null")){
            try{
                for(int i=0;i<str.length();i++){
                    ch = str.charAt(i);
                    if(ch != '0'){
                        result = str.substring(i);
                        break;
                    }
                }
            }catch(Exception e){
                result = "";
            }
        }else{
            result = "";
        }
        return result;

    }

    /**
     *
     * 功能描述：金额字符串转换：单位分转成单元

     * @param o 传入需要转换的金额字符串
     * @return 转换后的金额字符串
     */
    public static String fenToYuan(Object o) {
        if(o == null)
            return "0.00";
        String s = o.toString();
        int len = -1;
        StringBuilder sb = new StringBuilder();
        if (s != null && s.trim().length()>0 && !s.equalsIgnoreCase("null")){
            s = removeZero(s);
            if (s != null && s.trim().length()>0 && !s.equalsIgnoreCase("null")){
                len = s.length();
                int tmp = s.indexOf("-");
                if(tmp>=0){
                    if(len==2){
                        sb.append("-0.0").append(s.substring(1));
                    }else if(len==3){
                        sb.append("-0.").append(s.substring(1));
                    }else{
                        sb.append(s.substring(0, len-2)).append(".").append(s.substring(len-2));
                    }
                }else{
                    if(len==1){
                        sb.append("0.0").append(s);
                    }else if(len==2){
                        sb.append("0.").append(s);
                    }else{
                        sb.append(s.substring(0, len-2)).append(".").append(s.substring(len-2));
                    }
                }
            }else{
                sb.append("0.00");
            }
        }else{
            sb.append("0.00");
        }
        return sb.toString();
    }
    /**
     *
     * 功能描述：金额字符串转换：单位元转成单分

     * @param o 传入需要转换的金额字符串
     * @return 转换后的金额字符串
     */
    public static String yuanToFen(Object o) {
        if(o == null)
            return "0";
        String s = o.toString();
        int posIndex = -1;
        String str = "";
        StringBuilder sb = new StringBuilder();
        if (s != null && s.trim().length()>0 && !s.equalsIgnoreCase("null")){
            posIndex = s.indexOf(".");
            if(posIndex>0){
                int len = s.length();
                if(len == posIndex+1){
                    str = s.substring(0,posIndex);
                    if(str == "0"){
                        str = "";
                    }
                    sb.append(str).append("00");
                }else if(len == posIndex+2){
                    str = s.substring(0,posIndex);
                    if(str == "0"){
                        str = "";
                    }
                    sb.append(str).append(s.substring(posIndex+1,posIndex+2)).append("0");
                }else if(len == posIndex+3){
                    str = s.substring(0,posIndex);
                    if(str == "0"){
                        str = "";
                    }
                    sb.append(str).append(s.substring(posIndex+1,posIndex+3));
                }else{
                    str = s.substring(0,posIndex);
                    if(str == "0"){
                        str = "";
                    }
                    sb.append(str).append(s.substring(posIndex+1,posIndex+3));
                }
            }else{
                sb.append(s).append("00");
            }
        }else{
            sb.append("0");
        }
        str = removeZero(sb.toString());
        if(str != null && str.trim().length()>0 && !str.trim().equalsIgnoreCase("null")){
            return str;
        }else{
            return "0";
        }
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            //"MD5加密出现错误"
            e.printStackTrace();
        }
        return null;
    }
}
