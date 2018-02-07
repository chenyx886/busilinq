package com.busilinq.xsm.ulits;



import com.busilinq.xsm.data.api.UserCenterApi;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.data.usercenter.UserEntity;

import java.util.Map;

import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 后台更新数据到服务器
 * Created by dingyi on 2016/12/4.
 */

public class UserCenterServiceManager {
    private UserEntity mUser;
    private UserCenterApi mUserCenterApi;
    private static UserCenterServiceManager mInstance;

    private UserCenterServiceManager() {
        mUserCenterApi = UserCenterApi.newInstance(HttpMethod.instance.getNewClient(0xF0));
    }

    public static UserCenterServiceManager getInstance(){
        if(null == mInstance){
            mInstance = new UserCenterServiceManager();
        }
        return mInstance;
    }

    public void setUser(UserEntity entity) {
        this.mUser = entity;
    }

//    /**
//     * 同步设备信息，第三方推送标识到服务器
//     */
//    public void report() {
//        if (null == mUser)
//            return;
//        EquipmentEntity entity = new EquipmentEntity();
//        entity.setCell(mUser.getCell());
//        entity.setMemberId(mUser.getMemberId());
//        entity.setEquipNo(TeminalDevice.getUUid());
//        entity.setEquipOS(TeminalDevice.getPhoneInfo());
//        entity.setJpushCode(JPushInterface.getRegistrationID(BaseApplication.context()));
//        Logger.e(entity.toString());
//        mUserCenterApi.report(entity)
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Action1<HttpEntity<Object>>() {
//                    @Override
//                    public void call(HttpEntity<Object> httpEntity) {
//                        if (httpEntity.isSuccess()) {
//                            UserCenterHelper.getInstance(BaseApplication.context()).saveJPush("1");
//                        } else {
//                            UserCenterHelper.getInstance(BaseApplication.context()).saveJPush("0");
//                        }
//                    }
//                }, mThrowableAction);
//    }
//
//    /**
//     * 更新位置信息到服务器
//     */
//    public void saveLocation() {
//        if (null == mUser)
//            return;
//        Device device = TeminalDevice.getDevice();
//        if (null == mUser.getArea() || (null != mUser.getArea() && !mUser.getArea().contains(device.getLocation().getCity()))) {
//            Map<String, String> map = new HashMap<>();
//            map.put("cell", mUser.getCell());
//            map.put("area", device.getLocation().getCity());
//            map.put("address", device.getLocation().getAddress());
//            mUser.setArea(device.getLocation().getCity());
//            mUser.setAddress(device.getLocation().getAddress());
//            saveUserInfo(map);
//        }
//    }

    /**
     * 更新用户信息到服务器
     */
    public void saveUserInfo(Map<String, String> map) {
        mUserCenterApi.update(map)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<HttpEntity<Object>>() {
                    @Override
                    public void call(HttpEntity<Object> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            //UserCenterHelper.getInstance(BaseApplication.context()).saveUser(mUser);
                        }
                    }
                }, mThrowableAction);
    }

    private Action1 mAction1 = new Action1<HttpEntity<Object>>() {
        @Override
        public void call(HttpEntity<Object> httpEntity) {
            if (httpEntity.isSuccess()) {

            }
        }
    };

    private Action1 mThrowableAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            Logger.e(throwable.getMessage());
        }
    };
}
