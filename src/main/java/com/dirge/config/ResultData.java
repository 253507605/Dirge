package com.dirge.config;

import com.dirge.constants.ResponseMessage;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author Jason.Chen
 */
public class ResultData {

    @ApiModelProperty("状态")
    private boolean result;

    @ApiModelProperty("返回码")
    private String errorCode;

    @ApiModelProperty("消息")
    private String errorMsg;

    @ApiModelProperty("数据")
    private Object data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultData(boolean result, String errorCode, String errorMsg, Object data) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ResultData(boolean result, String errorCode, String errorMsg) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static ResultData ok() {
        return new ResultData(true, "200", ResponseMessage.SUCCESS);
    }

    public static ResultData ok(Object data) {
        return new ResultData(true, "200", ResponseMessage.SUCCESS, data);
    }

    public static ResultData error(String errorMsg) {
        return new ResultData(false, "500", errorMsg, null);
    }
}
