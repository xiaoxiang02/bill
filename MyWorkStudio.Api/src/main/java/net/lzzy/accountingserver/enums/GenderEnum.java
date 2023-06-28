package net.lzzy.accountingserver.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hp
 * 枚举
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenderEnum {
    /**
     *性别
     */
    GENDER_UNKNOWN(0, "未知"),
    GENDER_FEMALE (1, "男"),
    GENDER_MALE(2, "女");

    GenderEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
    @EnumValue
    private Integer code;
    @JsonValue
    private String value;

    /**
     * **根据value值获取枚举对象**
     * @param code
     */
    public static GenderEnum getEnum(Integer code){
        GenderEnum[] arr$=values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            GenderEnum status = arr$[i$];
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
