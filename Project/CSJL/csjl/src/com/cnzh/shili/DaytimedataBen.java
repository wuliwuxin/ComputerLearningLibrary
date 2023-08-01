package com.cnzh.shili;

import java.util.List;

public class DaytimedataBen {


    /**
     * status : 1
     * msg : 成功
     * data : [{"daytimeData_Id":1,"equipment_Id":2,"imageName":null,"indoorTemperature":26,"bodyTemperature":28.3,"acquisitionTime":null,"genTime":1525617969000,"equipment":null,"genTimeString":"2018-05-06-22:46:09"},{"daytimeData_Id":2,"equipment_Id":2,"imageName":null,"indoorTemperature":28,"bodyTemperature":27.2,"acquisitionTime":null,"genTime":1526115328000,"equipment":null,"genTimeString":"2018-05-12-16:55:28"},{"daytimeData_Id":3,"equipment_Id":2,"imageName":null,"indoorTemperature":56,"bodyTemperature":78,"acquisitionTime":null,"genTime":1526116329000,"equipment":null,"genTimeString":"2018-05-12-17:12:09"},{"daytimeData_Id":4,"equipment_Id":2,"imageName":null,"indoorTemperature":56,"bodyTemperature":78,"acquisitionTime":null,"genTime":1526116592000,"equipment":null,"genTimeString":"2018-05-12-17:16:32"},{"daytimeData_Id":5,"equipment_Id":2,"imageName":null,"indoorTemperature":56,"bodyTemperature":78,"acquisitionTime":null,"genTime":1526116606000,"equipment":null,"genTimeString":"2018-05-12-17:16:46"},{"daytimeData_Id":6,"equipment_Id":2,"imageName":null,"indoorTemperature":56,"bodyTemperature":78,"acquisitionTime":null,"genTime":1526116990000,"equipment":null,"genTimeString":"2018-05-12-17:23:10"},{"daytimeData_Id":36,"equipment_Id":2,"imageName":null,"indoorTemperature":47,"bodyTemperature":88,"acquisitionTime":null,"genTime":1526176283000,"equipment":null,"genTimeString":"2018-05-13-09:51:23"}]
     * page : null
     */

    private int status;
    private String msg;
    private Object page;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * daytimeData_Id : 1
         * equipment_Id : 2
         * imageName : null
         * indoorTemperature : 26
         * bodyTemperature : 28.3
         * acquisitionTime : null
         * genTime : 1525617969000
         * equipment : null
         * genTimeString : 2018-05-06-22:46:09
         */

        private int daytimeData_Id;
        private int equipment_Id;
        private Object imageName;
        private int indoorTemperature;
        private double bodyTemperature;
        private Object acquisitionTime;
        private long genTime;
        private Object equipment;
        private String genTimeString;

        public int getDaytimeData_Id() {
            return daytimeData_Id;
        }

        public void setDaytimeData_Id(int daytimeData_Id) {
            this.daytimeData_Id = daytimeData_Id;
        }

        public int getEquipment_Id() {
            return equipment_Id;
        }

        public void setEquipment_Id(int equipment_Id) {
            this.equipment_Id = equipment_Id;
        }

        public Object getImageName() {
            return imageName;
        }

        public void setImageName(Object imageName) {
            this.imageName = imageName;
        }

        public int getIndoorTemperature() {
            return indoorTemperature;
        }

        public void setIndoorTemperature(int indoorTemperature) {
            this.indoorTemperature = indoorTemperature;
        }

        public double getBodyTemperature() {
            return bodyTemperature;
        }

        public void setBodyTemperature(double bodyTemperature) {
            this.bodyTemperature = bodyTemperature;
        }

        public Object getAcquisitionTime() {
            return acquisitionTime;
        }

        public void setAcquisitionTime(Object acquisitionTime) {
            this.acquisitionTime = acquisitionTime;
        }

        public long getGenTime() {
            return genTime;
        }

        public void setGenTime(long genTime) {
            this.genTime = genTime;
        }

        public Object getEquipment() {
            return equipment;
        }

        public void setEquipment(Object equipment) {
            this.equipment = equipment;
        }

        public String getGenTimeString() {
            return genTimeString;
        }

        public void setGenTimeString(String genTimeString) {
            this.genTimeString = genTimeString;
        }
    }
}
