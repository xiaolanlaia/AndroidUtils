package com.wjf.moduledesignpattern.createType.Clone;

import androidx.annotation.NonNull;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/26 8:26
 */

public class CloneEnum implements Cloneable {

    public enum Gender {
        MAN, WOMAN
    }

    public Gender gender;

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneEnum tmp = (CloneEnum) super.clone();
        switch (this.gender) {
            case MAN:
                tmp.gender = Gender.MAN;
                break;
            case WOMAN:
                tmp.gender = Gender.WOMAN;
                break;
            default:
                break;
        }
        return tmp;

    }
}
