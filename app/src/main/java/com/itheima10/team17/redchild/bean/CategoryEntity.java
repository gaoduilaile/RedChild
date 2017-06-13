package com.itheima10.team17.redchild.bean;

import java.util.List;

/**
 * Created by Destiny on 2016/6/17.
 */
public class CategoryEntity {

    /**
     * category : [{"id":1,"leafNode":false,"name":"孕妈专区","parentId":0,"pic":"/images/category/ym.png","tag":"妈妈内衣  祛纹纤体"},{"id":2,"leafNode":false,"name":"寝具服饰","parentId":0,"pic":"/images/category/yf.png","tag":"童鞋   婴幼儿服饰"},{"id":3,"leafNode":false,"name":"宝宝用品","parentId":0,"pic":"/images/category/bb.png","tag":"奶粉辅食 婴幼儿营养"},{"id":11,"leafNode":false,"name":"妈妈个人护理","parentId":1,"tag":""},{"id":12,"leafNode":false,"name":"孕妇服饰","parentId":1,"tag":""},{"id":13,"leafNode":false,"name":"孕妇内衣","parentId":1,"tag":""},{"id":21,"leafNode":false,"name":"婴幼儿护齿","parentId":3},{"id":22,"leafNode":false,"name":"儿童玩具","parentId":3},{"id":31,"leafNode":false,"name":"女士围裙","parentId":2},{"id":32,"leafNode":false,"name":"男士围裙","parentId":2},{"id":111,"leafNode":true,"name":"洁牙护齿","parentId":11,"tag":""},{"id":112,"leafNode":true,"name":"成人尿不湿","parentId":11},{"id":121,"leafNode":true,"name":"韩版孕妇服饰","parentId":12},{"id":131,"leafNode":true,"name":"欧美风孕妇内衣","parentId":13},{"id":211,"leafNode":true,"name":"幼儿牙刷","parentId":21},{"id":212,"leafNode":true,"name":"7周岁儿童玩具","parentId":22},{"id":311,"leafNode":true,"name":"韩版围裙","parentId":31},{"id":312,"leafNode":true,"name":"日版围裙","parentId":32}]
     * response : category
     * version : 1
     */

    private String response;
    private int version;
    /**
     * id : 1
     * leafNode : false
     * name : 孕妈专区
     * parentId : 0
     * pic : /images/category/ym.png
     * tag : 妈妈内衣  祛纹纤体
     */

    private ErrEntity.ErrorBean error;

    public ErrEntity.ErrorBean getError() {
        return error;
    }

    private List<CategoryBean> category;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class CategoryBean {
        private int id;
        private boolean leafNode;
        private String name;
        private int parentId;
        private String pic;
        private String tag;
        private boolean isSelected;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isLeafNode() {
            return leafNode;
        }

        public void setLeafNode(boolean leafNode) {
            this.leafNode = leafNode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public boolean getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        @Override
        public String toString() {
            return "CategoryBean{" +
                    "id=" + id +
                    ", leafNode=" + leafNode +
                    ", name='" + name + '\'' +
                    ", parentId=" + parentId +
                    ", pic='" + pic + '\'' +
                    ", tag='" + tag + '\'' +
                    ", isSelected=" + isSelected +
                    '}';
        }
    }
}
