package com.ren.kai.rx;

/**
 * Created by Administrator on 2017/9/7.
 */

public class BusinessException extends Exception {

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;
    public static final int WRONG_EMPTY = 102;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code) {
        this(code2Msg(code));
    }
    private static String code2Msg(int code){
        String message = "";
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            case WRONG_EMPTY:
                message = "数据为空！";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }

}
