package com.xingu.xg.common.consts;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * 返回工具类
 * @author luo
 */
public class ComRT {

    private static Logger logger = LogManager.getLogger(ComRT.class);

    /**
     * 用于结果执行成功的返回，不关心数据
     *
     * @return
     */
    public static ComResult success() {
        return new ComResult(ComCodeMsg.SUCCESS, ComCodeMsg.SUCCESS_MSG, null);
    }

    /**
     * 用于结果执行成功的返回，关心数据，比如新增返回id等接口返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ComResult<T> success(T data) {
        return new ComResult<>(ComCodeMsg.SUCCESS, ComCodeMsg.SUCCESS_MSG, data);
    }

    /**
     * 此方法用于统一拦截器中，不建议单独使用
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ComResult<T> fail(Integer code, String message) {
        if (code.equals(5000)) {
            logger.log(Level.forName("xg", 150), new Date() + message);
        }
        return new ComResult<>(code, message, null);
    }

    /**
     * 此方法用于统一拦截器中，不建议单独使用
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ComResult<T> fail(Integer code, String message,T data) {
        if (code.equals(5000)) {
            logger.log(Level.forName("xg", 150), new Date() + message);
        }
        return new ComResult<>(code, message, data);
    }


}
