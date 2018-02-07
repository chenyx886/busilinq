//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busilinq.xsm.ulits;

import java.util.Collection;
import java.util.Map;

public class CommonUtils {
    public CommonUtils() {
    }

    public static boolean isEmpty(Object obj) {
        return obj == null ? true : (obj instanceof String && obj.toString().trim().equals("") ? true : (obj instanceof Number && ((Number) obj).doubleValue() < 0.0D ? true : (obj instanceof Collection && ((Collection) obj).isEmpty() ? true : (obj instanceof Map && ((Map) obj).isEmpty() ? true : obj instanceof Object[] && ((Object[]) ((Object[]) obj)).length == 0))));
    }

    public static boolean isEmpty(Object... obj) {
        boolean res = false;
        Object[] var2 = obj;
        int var3 = obj.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Object o = var2[var4];
            if (isEmpty(o)) {
                res = true;
                break;
            }
        }

        return res;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }


    /**
     * 订单支付状态
     *
     * @param payState
     * @return
     */
    public static String AShowShort(int payState) {
        String str = "";
        switch (payState) {
            case 1:
                str = "未付款";
                break;
            case 2:
                str = "已付款";
                break;
        }
        return str;

    }
}
