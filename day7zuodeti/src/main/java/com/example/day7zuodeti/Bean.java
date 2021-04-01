package com.example.day7zuodeti;

import java.util.List;

public class Bean {

    /**
     * error : false
     * results : [{"_id":"5ccdbc219d212239df927a93","createdAt":"2019-05-04T16:21:53.523Z","title":"标题1","publishedAt":"2019-05-04T16:21:59.733Z","source":"web","type":0,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img01.png","url_2":"","url_3":"","used":true,"content":"内容内容内容1"},{"_id":"5cc43919fc3326376038d233","createdAt":"2019-04-27T19:12:25.536Z","title":"标题2","url_1":"https://gitee.com/ccyy2019/server/raw/master/small02.png","url_2":"https://gitee.com/ccyy2019/server/raw/master/small03.png","url_3":"https://gitee.com/ccyy2019/server/raw/master/small04.png","publishedAt":"2019-04-27T19:12:51.865Z","source":"web","type":1,"used":true,"content":"内容内容内容2"},{"_id":"5c6a4ae99d212226776d3256","createdAt":"2019-02-18T06:04:25.571Z","title":"标题3","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":2,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img02.png","url_2":"","url_3":"","used":true,"content":"内容内容内容3"},{"_id":"5c6a4ae99d212226776d3257","createdAt":"2019-02-18T06:04:25.571Z","title":"标题4","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":2,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img03.png","url_2":"","url_3":"","used":true,"content":"内容内容内容3"},{"_id":"5c6a4ae99d212226776d3258","createdAt":"2019-02-18T06:04:25.571Z","title":"标题5","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":2,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img04.png","url_2":"","url_3":"","used":true,"content":"内容内容内容3"},{"_id":"5c6a4ae99d212226776d3259","createdAt":"2019-02-18T06:04:25.571Z","title":"标题6","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":2,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img05.jpg","url_2":"","url_3":"","used":true,"content":"内容内容内容3"},{"_id":"5c6a4ae99d212226776d32510","createdAt":"2019-02-18T06:04:25.571Z","title":"标题7","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":2,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img06.jpg","url_2":"","url_3":"","used":true,"content":"内容内容内容3"},{"_id":"5c6a4ae99d212226776d32510","createdAt":"2019-02-18T06:04:25.571Z","title":"标题8","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":2,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img07.jpg","url_2":"","url_3":"","used":true,"content":"内容内容内容3"},{"_id":"5c6a4ae99d212226776d32510","createdAt":"2019-02-18T06:04:25.571Z","title":"标题9","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":2,"url_1":"https://gitee.com/ccyy2019/server/raw/master/img08.jpg","url_2":"","url_3":"","used":true,"content":"内容内容内容3"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5ccdbc219d212239df927a93
         * createdAt : 2019-05-04T16:21:53.523Z
         * title : 标题1
         * publishedAt : 2019-05-04T16:21:59.733Z
         * source : web
         * type : 0
         * url_1 : https://gitee.com/ccyy2019/server/raw/master/img01.png
         * url_2 :
         * url_3 :
         * used : true
         * content : 内容内容内容1
         */

        private String _id;
        private String createdAt;
        private String title;
        private String publishedAt;
        private String source;
        private int type;
        private String url_1;
        private String url_2;
        private String url_3;
        private boolean used;
        private String content;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl_1() {
            return url_1;
        }

        public void setUrl_1(String url_1) {
            this.url_1 = url_1;
        }

        public String getUrl_2() {
            return url_2;
        }

        public void setUrl_2(String url_2) {
            this.url_2 = url_2;
        }

        public String getUrl_3() {
            return url_3;
        }

        public void setUrl_3(String url_3) {
            this.url_3 = url_3;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
