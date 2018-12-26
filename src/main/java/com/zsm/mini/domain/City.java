package com.zsm.mini.domain;

public class City {
    private String fid;

    private Integer seq;

    private String city;

    private String province;

    private String provincejc;

    private String cityjc;

    private String citycarjc;

    private String provinceid;

    private String longitude;

    private String latitude;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getProvincejc() {
        return provincejc;
    }

    public void setProvincejc(String provincejc) {
        this.provincejc = provincejc == null ? null : provincejc.trim();
    }

    public String getCityjc() {
        return cityjc;
    }

    public void setCityjc(String cityjc) {
        this.cityjc = cityjc == null ? null : cityjc.trim();
    }

    public String getCitycarjc() {
        return citycarjc;
    }

    public void setCitycarjc(String citycarjc) {
        this.citycarjc = citycarjc == null ? null : citycarjc.trim();
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }
}