package cn.shiying.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorEnum
 *
 * @author bobbi
 * @date 2018/10/07 14:19
 * @email 571002217@qq.com
 * @description error类型枚举类
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {

    // 系统错误
    UNKNOWN(500,"系统内部错误，请联系管理员"),
    PATH_NOT_FOUND(404,"路径不存在，请检查路径"),
    NO_AUTH(403,"没有权限，请联系管理员"),
    DUPLICATE_KEY(501,"数据库中已存在该记录"),
    TOKEN_GENERATOR_ERROR(502,"token生成失败"),
    TOKEN_ERROR(505,"token过期请重新登录"),
    NO_UUID(503,"uuid为空"),
    SQL_ILLEGAL(504,"sql非法"),
    LOAD_TIME_LANG(505,"连接超时"),

    //用户权限错误
    INVALID_TOKEN(1001,"token不合法"),
    UNAUTHENTICATED(1002,"此操作需要登陆系统！"),
    NEED_MORE_AUTH(1003,"已超出你的权限范围"),

    //登录模块错误
    LOGIN_FAIL(10001,"登录失败"),
    CAPTCHA_WRONG(10002,"验证码错误"),
    USERNAME_OR_PASSWORD_WRONG(10003,"用户名或密码错误!"),
    USERNAME_NOT_FOUND(10004,"用户名不存在"),
    USERNAME_IS_NULL(10005,"用户名不能为空"),
    PASSWORD_IS_NULL(10006,"密码不能为空"),

    //数据校验
    MENU_NAME_IS_NULL(20001,"菜单名称不能为空"),
    MENU_PARENT_IS_NULL(20002,"上级菜单不能为空"),
    MENU_URL_IS_NULL(20003,"菜单URL不能为空"),
    MENU_PARENT_NO_CATALOG(20004,"上级菜单只能为目录类型"),
    MENU_PARENT_NO_MENU(20005,"上级菜单只能为菜单类型"),
    PARENT_IS_NULL(20006,"父项目id为空");

    private int code;
    private String msg;

}
