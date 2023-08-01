package com.cnzh.shili;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CitckingQuite {
	/**
     * images : [{"classifiers":[{"classifier_id":"DefaultCustomModel_91236382","name":"Default Custom Model","classes":[{"class":"没踢被子","score":0.903}]}],"source_url":"https://picabstract-preview-ftn.weiyun.com:8443/ftn_pic_abs_v2/53ef7c5f7d129687370cfbc17801a8dcf2470dee6e40a18e8a58c64216e339c80b7dd84b527c6078ac8db214d8b5d0f8?pictype=scale&from=30113&version=2.0.0.2&uin=1468667058&fname=TIM图片20180323114546.jpg&size=1024","resolved_url":"https://picabstract-preview-ftn.weiyun.com:8443/ftn_pic_abs_v2/53ef7c5f7d129687370cfbc17801a8dcf2470dee6e40a18e8a58c64216e339c80b7dd84b527c6078ac8db214d8b5d0f8?pictype=scale&from=30113&version=2.0.0.2&uin=1468667058&fname=TIM%E5%9B%BE%E7%89%8720180323114546.jpg&size=1024"}]
     * images_processed : 1
     * custom_classes : 3
     */

    private int images_processed;
    private int custom_classes;
    private List<ImagesBean> images;

    public int getImages_processed() {
        return images_processed;
    }

    public void setImages_processed(int images_processed) {
        this.images_processed = images_processed;
    }

    public int getCustom_classes() {
        return custom_classes;
    }

    public void setCustom_classes(int custom_classes) {
        this.custom_classes = custom_classes;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * classifiers : [{"classifier_id":"DefaultCustomModel_91236382","name":"Default Custom Model","classes":[{"class":"没踢被子","score":0.903}]}]
         * source_url : https://picabstract-preview-ftn.weiyun.com:8443/ftn_pic_abs_v2/53ef7c5f7d129687370cfbc17801a8dcf2470dee6e40a18e8a58c64216e339c80b7dd84b527c6078ac8db214d8b5d0f8?pictype=scale&from=30113&version=2.0.0.2&uin=1468667058&fname=TIM图片20180323114546.jpg&size=1024
         * resolved_url : https://picabstract-preview-ftn.weiyun.com:8443/ftn_pic_abs_v2/53ef7c5f7d129687370cfbc17801a8dcf2470dee6e40a18e8a58c64216e339c80b7dd84b527c6078ac8db214d8b5d0f8?pictype=scale&from=30113&version=2.0.0.2&uin=1468667058&fname=TIM%E5%9B%BE%E7%89%8720180323114546.jpg&size=1024
         */

        private String source_url;
        private String resolved_url;
        private List<ClassifiersBean> classifiers;

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public String getResolved_url() {
            return resolved_url;
        }

        public void setResolved_url(String resolved_url) {
            this.resolved_url = resolved_url;
        }

        public List<ClassifiersBean> getClassifiers() {
            return classifiers;
        }

        public void setClassifiers(List<ClassifiersBean> classifiers) {
            this.classifiers = classifiers;
        }

        public static class ClassifiersBean {
            /**
             * classifier_id : DefaultCustomModel_91236382
             * name : Default Custom Model
             * classes : [{"class":"没踢被子","score":0.903}]
             */

            private String classifier_id;
            private String name;
            private List<ClassesBean> classes;

            public String getClassifier_id() {
                return classifier_id;
            }

            public void setClassifier_id(String classifier_id) {
                this.classifier_id = classifier_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ClassesBean> getClasses() {
                return classes;
            }

            public void setClasses(List<ClassesBean> classes) {
                this.classes = classes;
            }

            public static class ClassesBean {
                /**
                 * class : 没踢被子
                 * score : 0.903
                 */

                @SerializedName("class")
                private String classX;
                private double score;

                public String getClassX() {
                    return classX;
                }

                public void setClassX(String classX) {
                    this.classX = classX;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }
            }
        }
    }
}
