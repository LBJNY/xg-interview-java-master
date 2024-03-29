package com.xingu.xg.common.consts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回体
 * @author luo
 */
@Data
@ApiModel("统一返回体")
public class ComResult<T> implements Serializable {
    /**
     * 编码
     */
    @ApiModelProperty("编码")
    private Integer code;

    /**
     * 消息
     */
    @ApiModelProperty("消息")
    private String msg;

    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;

    public ComResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ComResult() {
    }
}
