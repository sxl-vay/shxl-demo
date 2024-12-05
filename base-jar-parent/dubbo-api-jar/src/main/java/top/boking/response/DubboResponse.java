package top.boking.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author shxl
 * @Date 2024/10/26 21:50
 * @Version 1.0
 */
@Data
public class DubboResponse<T> implements Serializable {
    private Long id;
    private Integer status;
    private String msg;
    private T result;

    public static <T> DubboResponse<T> success(T result) {
        DubboResponse<T> dubboResponse = new DubboResponse<>();
        dubboResponse.setMsg("ok");
        dubboResponse.setStatus(200);
        dubboResponse.setResult(result);
        return dubboResponse;
    }

    public static <T> DubboResponse<T> fail(T result) {
        DubboResponse<T> dubboResponse = new DubboResponse<>();
        dubboResponse.setMsg("error");
        dubboResponse.setStatus(500);
        dubboResponse.setResult(result);
        return dubboResponse;
    }

    public static <T> DubboResponse<T> fail(T result,Exception e) {
        DubboResponse<T> dubboResponse = new DubboResponse<>();
        dubboResponse.setMsg(e.getMessage());
        dubboResponse.setStatus(500);
        dubboResponse.setResult(result);
        return dubboResponse;
    }
}
