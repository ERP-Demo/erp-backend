package cn.shiying.config.exception;


import cn.shiying.common.dto.Result;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCatch;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**课程管理自定义的异常类，其中定义异常类型所对应的错误代码
 * @author Administrator
 * @version 1.0
 **/
@ControllerAdvice//控制器增强
public class CustomExceptionCatch extends ExceptionCatch {
    static {
        builder.put(AccessDeniedException.class, Result.error(ErrorEnum.NO_AUTH));
    }
}
