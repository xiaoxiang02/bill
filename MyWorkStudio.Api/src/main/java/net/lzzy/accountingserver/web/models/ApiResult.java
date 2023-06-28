package net.lzzy.accountingserver.web.models;

import lombok.Getter;

/**
 * @author hp
 */
@Getter
public class ApiResult {
    /**请求是否成功*/
    private final boolean success;

    private final String message;

    private final Object data;


    public ApiResult(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
