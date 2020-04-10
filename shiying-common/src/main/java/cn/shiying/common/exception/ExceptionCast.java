package cn.shiying.common.exception;

import cn.shiying.common.dto.Result;
import cn.shiying.common.enums.ErrorEnum;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-14 17:31
 **/
public class ExceptionCast {

    public static void cast(ErrorEnum error){
        throw new CustomException(Result.error(error));
    }
}
