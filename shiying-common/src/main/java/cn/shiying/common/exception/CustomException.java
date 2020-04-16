package cn.shiying.common.exception;

import cn.shiying.common.dto.Result;

public class CustomException extends RuntimeException {
    Result result;

    public CustomException(Result result){
        this.result = result;
    }
    public Result getResultCode(){
        return result;
    }
    public CustomException(String str){this.result=Result.error(str);}


}
