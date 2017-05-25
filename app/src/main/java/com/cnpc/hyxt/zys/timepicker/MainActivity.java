package com.cnpc.hyxt.zys.timepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cnpc.hyxt.zys.timepicker.entity.City;
import com.cnpc.hyxt.zys.timepicker.entity.County;
import com.cnpc.hyxt.zys.timepicker.entity.Province;
import com.cnpc.hyxt.zys.timepicker.listeners.OnLinkageListener;
import com.cnpc.hyxt.zys.timepicker.picker.AddressPicker;
import com.cnpc.hyxt.zys.timepicker.picker.DateTimePicker;
import com.cnpc.hyxt.zys.timepicker.util.ConvertUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Province> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAddressData();
        initTimePicker();
        initAddressPicker();
    }

    /*
     * 初始化位置信息
     *
     */
    private void initAddressData() {
        data = new ArrayList<>();
        //解析数据
        try{
            String json = ConvertUtils.toString(this.getAssets().open("city.json"));
            data.addAll(JSON.parseArray(json,Province.class));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 初始化位置选择器
     *
     */
    private void initAddressPicker() {
        Button button = (Button) findViewById(R.id.byn_address);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建位置筛选器
                AddressPicker addressPicker = new AddressPicker(MainActivity.this, data);
                //设置默认选中的省市县
                addressPicker.setSelectedItem("河北","石家庄","深泽");

                //设置滚轮滚动
                addressPicker.setWeightEnable(true);
                addressPicker.setWheelModeEnable(true);

                //设置地址选中的监听
                addressPicker.setOnLinkageListener(new OnLinkageListener() {
                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        Toast.makeText(MainActivity.this, province.getAreaName() + city.getAreaName() + county.getAreaName(), Toast.LENGTH_SHORT).show();
                    }
                });
                addressPicker.show();
            }
        });
    }

    /*
     * 初始化时间选择器
     *
     */
    private void initTimePicker() {
        Button button = (Button) findViewById(R.id.byn_time);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置选择器的展示样式 他有两个构造 可以设置只显示年月日和年月日时分  具体按照自己的业务条件来实现
                DateTimePicker timePicker = new DateTimePicker(MainActivity.this, DateTimePicker.YEAR_MONTH_DAY, DateTimePicker.HOUR_24);
                //设置开始的年月日
                timePicker.setDateRangeStart(1992, 2, 14);
                //设置结束的年月日
                timePicker.setDateRangeEnd(2017, 5, 25);
                //设置开始的时分秒
                timePicker.setTimeRangeStart(0, 0);
                //设置结束的时分秒
                timePicker.setTimeRangeEnd(11, 20);
                //设置年月十分的显示单位
                timePicker.setLabel("年", "月", "日", "时", "分");
                //设置默认选中的时间
                timePicker.setSelectedItem(2017, 5, 25, 11, 20);

                //设置滚轮滚动
                timePicker.setWeightEnable(true);
                timePicker.setWheelModeEnable(true);

                //设置选中的监听(会自动在头布局添加确认和删除的按钮 并且点击后dialog会自动消失)
                timePicker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        Toast.makeText(MainActivity.this, year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分", Toast.LENGTH_SHORT).show();
                    }
                });

                timePicker.show();

            }
        });
    }
}
