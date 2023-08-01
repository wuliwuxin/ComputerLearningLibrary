package com.cnzh.shili;

import java.util.List;

public class AtBen {

    /**
     * status : 1
     * msg : 成功
     * data : {"accessToken":"at.bk9u0l4j1wacl5761we1i43q297w87y6-4n54gjiwn2-1grv6g1-gtcj0iuzp","genTime":1526278949000,"at_id":1,"genTimeString":"2018-05-14-14:22:29"}
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
         * accessToken : at.bk9u0l4j1wacl5761we1i43q297w87y6-4n54gjiwn2-1grv6g1-gtcj0iuzp
         * genTime : 1526278949000
         * at_id : 1
         * genTimeString : 2018-05-14-14:22:29
         */

        private String accessToken;
        private long genTime;
        private int at_id;
        private String genTimeString;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getGenTime() {
            return genTime;
        }

        public void setGenTime(long genTime) {
            this.genTime = genTime;
        }

        public int getAt_id() {
            return at_id;
        }

        public void setAt_id(int at_id) {
            this.at_id = at_id;
        }

        public String getGenTimeString() {
            return genTimeString;
        }

        public void setGenTimeString(String genTimeString) {
            this.genTimeString = genTimeString;
        }
    }
}
