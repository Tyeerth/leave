package cn.jxust.leave.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description  适用于Coller层的一个工具类,非常适合返回json数据的,
 * 返回类型的搭配有以下四种
 *              return JsonMsg.success();
 *             return JsonMsg.fail();
 *             return JsonMsg.success().addInfo("example_name", "你需要写的信息");
 *            return JsonMsg.fail().addInfo("example_name", "你需要写的信息");
 *
 * @Author   lcs
 * @Date 14:25 2019/11/12
 **/
public class JsonMsg {

    private int code;     //只储存100,200,其中100成功,200失败
    private String msg;   //只储存"操作成功"和"操作失败",与失败和成功对应,
    private Map<String, Object> extendInfo = new HashMap<String, Object>();//用来储存自己的需要返回到页面的信息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }

    public static JsonMsg success(){
        JsonMsg res = new JsonMsg();
        res.setCode(100);
        res.setMsg("操作成功！");
        return res;
    }

    public static JsonMsg fail(){
        JsonMsg res = new JsonMsg();
        res.setCode(200);
        res.setMsg("操作失败！");
        return res;
    }

    public JsonMsg addInfo(String key, Object obj){
        this.extendInfo.put(key,obj);
        return this;
    }

}
