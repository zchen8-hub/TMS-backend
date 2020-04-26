package CS542.group6.TMS.dto;

import lombok.Data;

@Data
public class JsonResult<T> {
    private String code;
    private T data;
    private String msg;

    /**
     * Default json result without data return.
     */
    public JsonResult() {
        this.code = "200";
        this.msg = "Success";
    }

    /**
     * Default message and code with data return.
     * @param data
     */
    public JsonResult(T data){
        this.data = data;
        this.code = "200";
        this.msg = "Success";
    }

    public JsonResult(String msg){
        this.code = "200";
        this.msg = msg;
    }

    /**
     * Designate http code and message without data return.
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * Designate message with data return.
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg){
        this.data = data;
        this.code = "200";
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
