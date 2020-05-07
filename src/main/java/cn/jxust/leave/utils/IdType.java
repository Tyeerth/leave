package cn.jxust.leave.utils;

import lombok.Getter;

/**
 * id生成类型枚举
 * @author tyeerth
 * @date 2020/4/24 - 8:24
 */
@Getter
public enum IdType {
    /**
     * 数据库自增
     */
    AUTO(0),
    /**
     * 该类型为未设定类型
     */
    NONE(1),
    /**
     * 用户输入ID
     * 该类型可以通过自己注册自动填充插件进行填充
     */
    INPUT(2),
    /* 以下3种类型、只有当插入对象ID 为空，才自动填充。 */
    /**
     * 全局唯一ID (idWorker)
     */
    ID_WORKER(3),
    /**
     * 全局唯一ID（UUID）
     */
    UUID(4),
    /**
     * 字符串全局唯一ID (idWorker 的字符串表示)
     */
    ID_WORKER_STR(5);
    private final int key ;
    IdType(int key){
        this.key = key;
    }
}