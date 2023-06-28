package net.lzzy.accountingserver.constant;

/**
 * @author hp
 */

public enum Messages {
    /**
     * Controller中的返回信息
     */
    SUCCESS("成功"),
    WRONG_PASSWORD("错误的用户密码"),
    INCOMPLETE_INFO("信息不完整"),
    INVALID_FORMAT("非法的数据格式"),
    WRONG_ID("错误的ID"),
    RELATED_DATA("存在关联数据");


    private final String name;

    Messages(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
