package com.cnpc.hyxt.zys.timepicker.listeners;

import com.cnpc.hyxt.zys.timepicker.entity.City;
import com.cnpc.hyxt.zys.timepicker.entity.County;
import com.cnpc.hyxt.zys.timepicker.entity.Province;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province province, City city, County county);
}
