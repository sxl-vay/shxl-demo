package top.boking.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author shxl
 * @Date 2024/10/26 21:49
 * @Version 1.0
 */
@Data
public class DubboRequest<T> implements Serializable {
    private Long id;
    private T params;
}
