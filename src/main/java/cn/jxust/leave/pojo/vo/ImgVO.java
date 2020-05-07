package cn.jxust.leave.pojo.vo;

/**
 * @author tyeerth
 * @date 2020/5/4 - 10:14
 */
public class ImgVO {

    public ImgVO() {
    }

    public ImgVO(String img, String uuid) {
        this.img = img;
        this.uuid = uuid;
    }

    private String img;
    private String uuid;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ImgVO{" +
                "img='" + img + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}