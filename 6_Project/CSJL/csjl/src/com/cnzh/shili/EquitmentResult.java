package com.cnzh.shili;

public class EquitmentResult {

	/**
     * status : 1
     * msg : 成功
     * data : {"equipment_Id":1,"equipmentName":"166007633","accessToken":null,"genTime":1521617598000,"daytimedatalist":null,"nightdatalist":null,"userlist":null,"genTimeString":"2018-03-21-15:33:18"}
     * page : null
     */

    private int status;
    private String msg;
    private DataBean data;
    private Object page;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public static class DataBean {
        /**
         * equipment_Id : 1
         * equipmentName : 166007633
         * accessToken : null
         * genTime : 1521617598000
         * daytimedatalist : null
         * nightdatalist : null
         * userlist : null
         * genTimeString : 2018-03-21-15:33:18
         */

        private int equipment_Id;
        private String equipmentName;
        private Object accessToken;
        private long genTime;
        private Object daytimedatalist;
        private Object nightdatalist;
        private Object userlist;
        private String genTimeString;

        public int getEquipment_Id() {
            return equipment_Id;
        }

        public void setEquipment_Id(int equipment_Id) {
            this.equipment_Id = equipment_Id;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public Object getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(Object accessToken) {
            this.accessToken = accessToken;
        }

        public long getGenTime() {
            return genTime;
        }

        public void setGenTime(long genTime) {
            this.genTime = genTime;
        }

        public Object getDaytimedatalist() {
            return daytimedatalist;
        }

        public void setDaytimedatalist(Object daytimedatalist) {
            this.daytimedatalist = daytimedatalist;
        }

        public Object getNightdatalist() {
            return nightdatalist;
        }

        public void setNightdatalist(Object nightdatalist) {
            this.nightdatalist = nightdatalist;
        }

        public Object getUserlist() {
            return userlist;
        }

        public void setUserlist(Object userlist) {
            this.userlist = userlist;
        }

        public String getGenTimeString() {
            return genTimeString;
        }

        public void setGenTimeString(String genTimeString) {
            this.genTimeString = genTimeString;
        }
    }
}
