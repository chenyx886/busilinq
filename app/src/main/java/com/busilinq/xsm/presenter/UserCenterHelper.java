package com.busilinq.xsm.presenter;

import android.content.Context;

import com.busilinq.xsm.data.entity.Employee;
import com.busilinq.xsm.data.entity.UnMsgEvent;
import com.busilinq.xsm.data.usercenter.UserEntity;
import com.busilinq.xsm.ulits.ACache;
import com.busilinq.xsm.ulits.CommonUtils;


/**
 * Created by dingyi on 2017/1/10.
 */

public class UserCenterHelper {

    private ACache mACache;
    private UserEntity mUser;
    private Employee mEmployee;
    private UnMsgEvent mUnMsgEvent;

    private static UserCenterHelper mInstance;

    private UserCenterHelper(Context context) {
        this.mACache = ACache.get(context);
    }

    public static UserCenterHelper getInstance(Context context) {
        if (null == mInstance)
            mInstance = new UserCenterHelper(context);
        return mInstance;
    }

    public UserEntity getUser() {
        return CommonUtils.isNotEmpty(mUser)?mUser:(UserEntity)mACache.getAsObject(UserEntity.class.getSimpleName());
    }

    public Employee getEmployee() {
        return CommonUtils.isNotEmpty(mEmployee)?mEmployee:(Employee)mACache.getAsObject(Employee.class.getSimpleName());
    }

    public UnMsgEvent getUnMsgEvent(){
        if(mUnMsgEvent == null)
            mUnMsgEvent = (UnMsgEvent) mACache.getAsObject(UnMsgEvent.class.getSimpleName());
        return mUnMsgEvent;
    }
    public void clearUnMsgEvent(){
        mUnMsgEvent = null;
    }

    public void saveUnMsgEvent(UnMsgEvent unMsgEvent){
        this.mUnMsgEvent = unMsgEvent;
        mACache.put(UnMsgEvent.class.getSimpleName(),mUnMsgEvent);
    }

    public void saveUser(UserEntity user) {
        this.mUser = user;
        mACache.put(UserEntity.class.getSimpleName(), mUser);
    }

    public void saveEmployee(Employee employee) {
        this.mEmployee = employee;
        mACache.put(Employee.class.getSimpleName(), mEmployee);
    }

    public void clearEmployee() {
        this.mEmployee = null;
        mACache.remove(Employee.class.getSimpleName());
    }


    public boolean isLogin() {
        if (null == mUser) {
            mUser = (UserEntity) mACache.getAsObject(UserEntity.class.getSimpleName());
            if (null == mUser) {
                mACache.clear();
                return false;
            }
        } else {
            return true;
        }
        return true;
    }

    public boolean isSavedEmployee() {
        if (null == mEmployee) {
            mEmployee = (Employee) mACache.getAsObject(Employee.class.getSimpleName());
            if (null == mEmployee) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }


    public void clear() {
        mUser = null;
        mEmployee = null;
    }


    public void saveType(int type){
        mACache.put("type",type);
    }
    public Object getType()
    {
        return   mACache.getAsObject("type");
    }

}

