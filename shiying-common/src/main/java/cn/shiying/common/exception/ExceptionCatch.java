package cn.shiying.common.exception;

import cn.shiying.common.dto.Result;
import cn.shiying.common.enums.ErrorEnum;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice//控制器增强
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    private static ImmutableMap<Class<? extends Throwable>, Result> EXCEPTIONS;
    protected static ImmutableMap.Builder<Class<? extends Throwable>,Result> builder = ImmutableMap.builder();

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customException(CustomException customException){
        LOGGER.error("catch exception:{}",customException.getMessage());
        Result result = customException.getResultCode();
        return result;
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception exception){
        LOGGER.error("catch exception:{}",exception.getMessage());
        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();
        }
        Result result = EXCEPTIONS.get(exception.getClass());
        if(result !=null){
            return result;
        }else{
            return Result.error(ErrorEnum.UNKNOWN);
        }


    }

    static {
        builder.put(HttpMessageNotReadableException.class,Result.error(ErrorEnum.UNKNOWN));
    }
}
