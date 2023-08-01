package com.cnzh.shili;

public class Picture {
	 /**
     * data : {"picUrl":"https://wuhancloudpictest.ys7.com:8083/HIK_1468308471_AF28AE8DD5A0e83e_FA163E0CE33A150000797?isEncrypted=0&isCloudStored=0&x=1280&session=hik%24shipin7%231%23USK%23"}
     * code : 200
     * msg : 操作成功!
     */

    private ImageDataBean data;
    private String code;
    private String msg;

    public ImageDataBean getData() {
        return data;
    }

    public void setData(ImageDataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ImageDataBean {
        /**
         * picUrl : https://wuhancloudpictest.ys7.com:8083/HIK_1468308471_AF28AE8DD5A0e83e_FA163E0CE33A150000797?isEncrypted=0&isCloudStored=0&x=1280&session=hik%24shipin7%231%23USK%23
         */

        private String picUrl;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }

}
