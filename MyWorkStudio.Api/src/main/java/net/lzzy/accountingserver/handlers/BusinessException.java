package net.lzzy.accountingserver.handlers;

/**
 * @author hp
 */
public class BusinessException extends RuntimeException {
    private final String errInfo;

    public BusinessException(String message){
        super(message);
        errInfo = message;
    }

    public String getErrInfo() {
        return errInfo;
    }
}
