package com.mengxuegu.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 自定义响应结构 
 */
@Data
public class MengxueguResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;
	
	public MengxueguResult() {
    }
	public MengxueguResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public MengxueguResult(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public MengxueguResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static MengxueguResult ok() {
        return new MengxueguResult(null);
    }
    public static MengxueguResult ok(String message) {
        return new MengxueguResult(message, null);
    }
    public static MengxueguResult ok(Object data) {
        return new MengxueguResult(data);
    }
    public static MengxueguResult ok(String message, Object data) {
        return new MengxueguResult(message, data);
    }

    public static MengxueguResult build(Integer code, String message) {
        return new MengxueguResult(code, message, null);
    }

    public static MengxueguResult build(Integer code, String message, Object data) {
        return new MengxueguResult(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 MengxueguResult 对象
     * @param json
     * @return
     */
    public static MengxueguResult format(String json) {
        try {
            return JSON.parseObject(json, MengxueguResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
