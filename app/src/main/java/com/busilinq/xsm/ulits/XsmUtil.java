package com.busilinq.xsm.ulits;


import com.busilinq.xsm.data.entity.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dingyi on 2016/11/20.
 */

public class XsmUtil {
    public static boolean checkOrderingTime(String bTime, String eTime) {

        boolean bRet = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        try {
            Date bDate = dateFormat.parse(bTime);
            Date eDate = dateFormat.parse(eTime);
            Date curDate = new Date(System.currentTimeMillis());

            if(curDate.after(bDate) && curDate.before(eDate)){
                bRet = true;
            }

        } catch (ParseException e) {
            bRet = false;
            e.printStackTrace();
        }
        return bRet;
    }

    public static int checkOrderDate(String date){
        int iRet = -1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date orderDate = dateFormat.parse(date);
            Date curDate = new Date(System.currentTimeMillis());

            if(curDate.after(orderDate)){
                iRet = -1;
            }else if(curDate.before(orderDate)){
                iRet = 1;
            }else{
                iRet = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return iRet;
    }


    /**
     * 检查订单是否处于提交状态
     * @param order
     * @return
     */
    public static boolean isAddStateOrder(Order order){
        boolean bRet = false;
        if (null == order) {
            return bRet;
        }
        String status = order.getStatus().substring(0, 1);
        String pmtStatus = order.getPmtStatus();
        if("1".equals(pmtStatus)){//订单是否已经付款
            bRet = false;
        }
        else if("2".equals(pmtStatus)){
            bRet = false;
        }else if("2".equals(status) || "1".equals(status)){//如果未付款，订单是否处于提交或者新建状态
            bRet = true;
        }else{
            bRet = false;
        }
        return bRet;
    }
}
