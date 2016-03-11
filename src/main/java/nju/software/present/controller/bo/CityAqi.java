package nju.software.present.controller.bo;

import java.util.Date;

/**
 * Created by lulei on 16/3/7.
 */
public class CityAqi {
    // 城市坐标
    private String coordinates;
    // 城市名
    private String name;
    // 获取的数据时间
    private Date date;
    // AQI指数
    private String AQI;
    // 空气质量级别
    private String level;
    // 首要污染物
    private String primary;
    // AQI指数顺序
    private int order;

    public String getAQI() {
        return AQI;
    }

    public void setAQI(String AQI) {
        this.AQI = AQI;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
}
