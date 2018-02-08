package com.busilinq.contract.mine;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.UploadEntity;
import com.busilinq.data.entity.UserEntity;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：修改用户头像 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface IUpdateAvatarView extends IBaseMvpView {
    /**
     * 显示
     */
    void showUserInfo(UserEntity user);
    /**
     * 上传成功
     */
    void Success(String type, List<String> filePaths, List<UploadEntity> list);
}
