package cn.nj.storm.common.bean;

import java.io.Serializable;

public class Gps implements Serializable{

    /**坐标纬度*/
    private Double lat;

    /**坐标经度*/
    private Double lng;

    public Gps() {
    }

    public Gps(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String toString(){
        return this.getLat()+","+this.getLng();
    }
}
