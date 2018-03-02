package com.busilinq.ui.mine;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.cart.INewlyAddedAddress;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.cart.NewlyAddedAddressPresenter;
import com.busilinq.ui.mine.domian.CityModel;
import com.busilinq.ui.mine.domian.DistrictModel;
import com.busilinq.ui.mine.domian.ProvinceModel;
import com.busilinq.widget.MLoadingDialog;
import com.busilinq.xsm.ulits.StringUtils;
import com.chenyx.libs.utils.Toasts;
import com.youth.picker.PickerView;
import com.youth.picker.entity.PickerData;
import com.youth.picker.listener.OnPickerClickListener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：新增收货地址
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class AddAddressActivity extends BaseMvpActivity<NewlyAddedAddressPresenter> implements INewlyAddedAddress {

    public static final int ADDRESS_REQUESTCODE = 2;

    /**
     * 返回
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 收货单位
     */
    @BindView(R.id.new_unit_et)
    EditText new_unit_et;

    /**
     * 联系人
     */
    @BindView(R.id.new_consignee_et)
    EditText new_consignee_et;

    /**
     * 联系方式
     */
    @BindView(R.id.new_tell_et)
    EditText new_tell_et;

    /**
     * 收货地址
     */
    @BindView(R.id.ssq)
    TextView mssq;

    /**
     * 收货地址
     */
    @BindView(R.id.new_detail_address)
    EditText new_detail_address;


    /**
     * 所有省
     */
    protected String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    protected String mCurrentProviceName;
    /**
     * 当前市的名称
     */
    protected String mCurrentCityName;
    /**
     * 当前区的名称
     */
    protected String mCurrentDistrictName = "";

    /**
     * 当前区的邮政编码
     */
    protected String mCurrentZipCode = "";

    private String isDefault; // 是否默认
    private String come; // 来源
    private String unit;
    private String consignee;
    private String tell;
    private String detailAddress;
    private int addressId;

    private PickerView pickerView;


    @Override
    protected NewlyAddedAddressPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new NewlyAddedAddressPresenter(this);
        }
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_newly_added_address);

        initProvinceDatas();
    }


    /**
     * 解析省市区的XML数据
     */

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }


    @Override
    protected void initUI() {
        mTitle.setText("收货地址");

        Bundle bundle;
        UserShopAddrEntity entity = null;
        come = getIntent().getStringExtra("come");
        isDefault = getIntent().getStringExtra("isDefault");
        if (come != null) {
            if (come.equals("edit")) {
                bundle = getIntent().getExtras();
                entity = bundle.getParcelable("editAddressInfo");
                addressId = entity.getAddrId();
                new_unit_et.setText(entity.getCompany());
                new_consignee_et.setText(entity.getName());
                new_tell_et.setText(entity.getCell());
                new_detail_address.setText(entity.getSpecificAddr());
            }
        }

        //选择器数据实体类封装
        PickerData data = new PickerData();
        //设置数据，有多少层级自己确定
        data.setFirstDatas(mProvinceDatas);
        data.setSecondDatas(mCitisDatasMap);
        data.setThirdDatas(mDistrictDatasMap);
        data.setFourthDatas(new HashMap<String, String[]>());
        //设置初始化默认显示的三级菜单(此方法可以选择传参数量1到4个)
//        data.setInitSelectText("河北省","石家庄市","平山县");
        //初始化选择器
        pickerView = new PickerView(this, data);
        mssq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示选择器
                pickerView.show(mssq);
            }
        });
        //选择器完成三级选择后点击回调
        pickerView.setOnPickerClickListener(new OnPickerClickListener() {
            //选择列表时触发的事件
            @Override
            public void OnPickerClick(PickerData pickerData) {
                //想获取单个选择项 PickerData内也有方法（弹出框手动关闭）
                mssq.setText(pickerData.getSelectText());
                pickerView.dismiss();//关闭选择器
            }

            //点击确定按钮触发的事件（自动关闭）
            @Override
            public void OnPickerConfirmClick(PickerData pickerData) {
                mssq.setText(pickerData.getSelectText());
            }
        });


    }

    @OnClick({R.id.tv_back, R.id.btn_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_save: // 保存按钮
                addAddressInfo();
                break;
        }


    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @Override
    public void addAddressSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void addAddressInfo() {
        unit = new_unit_et.getText().toString();
        consignee = new_consignee_et.getText().toString();
        tell = new_tell_et.getText().toString();
        detailAddress = mssq.getText().toString() + new_detail_address.getText().toString();
        UserShopAddrEntity entity = new UserShopAddrEntity();
        if (StringUtils.isEmpty(unit)) {
            Toasts.showShort(this, "请填写收货单位");
            return;
        }
        entity.setCompany(unit);
        if (StringUtils.isEmpty(consignee)) {
            Toasts.showShort(this, "请填写联系人");
            return;
        }
        entity.setName(consignee);
        if (StringUtils.isEmpty(tell)) {
            Toasts.showShort(this, "请填写联系电话");
            return;
        }
        entity.setCell(tell);
        if (StringUtils.isEmpty(detailAddress)) {
            Toasts.showShort(this, "请填写详细地址");
            return;
        }
        entity.setSpecificAddr(detailAddress);
        entity.setIsDefault(isDefault);

        if (come.equals("add")) {
            mPresenter.addAddress(entity);
        } else if (come.equals("edit")) {
            entity.setAddrId(addressId);
            mPresenter.editAddress(entity);
        }
    }
}
