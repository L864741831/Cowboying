package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/11/2 16:33
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class RanchBottomVideoResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"id":43,"name":"fasf","coverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/6a914006779c449496a3e845464334b9/covers/93700897b4f14b62ad9fc722328f3428-00001.jpg?Expires=1541151977&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=ik76Cx%2FQgKIcjAJ8C4SnPPKwIG8%3D","playUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/6a914006779c449496a3e845464334b9/4226d98a5822452c8d136895676396dc-b83ba1267cd3cb23f7127829e4236316-ld.mp4?Expires=1541151977&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=7P2U%2FvvQ%2FyheFiPt31EAnxazVCY%3D","createTime":null},{"id":44,"name":"fds","coverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/ef29e9777f0941f88bdbdcb5a1b3912d/covers/26ab9ee9b6d342efb9ea019b99e9a4f3-00001.jpg?Expires=1541151978&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=M5Nh9jx63Cy4mNufXU7g4o57Paw%3D","playUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/ef29e9777f0941f88bdbdcb5a1b3912d/db5a2f6c8e0046adb66cbef5595a9713-38529f3c66ccda2eb5569fff8e99131b-ld.mp4?Expires=1541151978&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=nCV9kV2%2F6bPGlyLjXXpuqonxnwo%3D","createTime":null}]
     */

    private String code;
    private String message;
    private List<BizDataBean> bizData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean {
        /**
         * id : 43
         * name : fasf
         * coverUrl : http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/6a914006779c449496a3e845464334b9/covers/93700897b4f14b62ad9fc722328f3428-00001.jpg?Expires=1541151977&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=ik76Cx%2FQgKIcjAJ8C4SnPPKwIG8%3D
         * playUrl : https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/6a914006779c449496a3e845464334b9/4226d98a5822452c8d136895676396dc-b83ba1267cd3cb23f7127829e4236316-ld.mp4?Expires=1541151977&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=7P2U%2FvvQ%2FyheFiPt31EAnxazVCY%3D
         * createTime : null
         */

        private int id;
        private String name;
        private String coverUrl;
        private String playUrl;
        private Object createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }
    }
}
