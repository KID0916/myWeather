package me.missfan.syjh.beans;

/**
 * Created by Gr on 2016/2/21.
 */
public class Status {
    public static final String OK = "ok"; //接口正常
    public static final String INVALID_KEY= "invalid key"; //错误的用户 key
    public static final String UNKNOWN_CITY = "unknown city"; //未知城市
    public static final String NO_MORE_REQUESTS= "no more requests"; //超过访问次数
    public static final String ANR= "anr"; //服务无响应或超时
    public static final String PERMISSION_DENIED = "permission denied"; //没有访问权限
}
