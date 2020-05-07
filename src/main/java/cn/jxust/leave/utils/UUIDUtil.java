package cn.jxust.leave.utils;

import java.util.UUID;

/**
 * @author tyeerth
 * @date 2020/5/4 - 10:10
 */
public class UUIDUtil {

    private UUIDUtil() {}

    /**
     * 简单模式，简单模式为不带'-'的UUID字符串
     * @return 生成32位的uuid
     */
    public static String GeneratorUUIDOfSimple() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}