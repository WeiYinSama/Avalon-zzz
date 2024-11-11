package net.avalon.zzz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Heda
 * @Create: 2024/11/7
 */
public enum VideoStatusEnum {

    IN_PROGRESS((byte) 0, "审核中"),
    APPROVED((byte) 1, "已通过"),
    REJECTED((byte) 2, "已拒绝");

    // 字段
    private byte code;
    private String desc;

    private static final Map<Byte, String> map;

    static {
        map = new HashMap<>(VideoStatusEnum.values().length);
        for (VideoStatusEnum videoStatus : VideoStatusEnum.values()) {
            map.put(videoStatus.getCode(), videoStatus.getDesc());
        }
    }

    // 构造方法
    // 枚举的构造器总是私有的
    VideoStatusEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public byte getCode() {
        return code;
    }

    public static String getDescByCode(byte code) {
        return map.get(code);
    }

}
