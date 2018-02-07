package com.busilinq.xsm.ulits;


import com.busilinq.xsm.data.entity.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by dingyi on 2016/11/24.
 */


public class XsmOrderComparator implements Comparator<Order>{
    @Override
    public int compare(Order o1, Order o2) {
        int iRet = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date bDate = dateFormat.parse(o1.getOrderDate());
            Date eDate = dateFormat.parse(o2.getOrderDate());
            if(bDate.before(eDate))
                iRet = 1;
            else
                iRet = -1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return iRet;
    }
}
