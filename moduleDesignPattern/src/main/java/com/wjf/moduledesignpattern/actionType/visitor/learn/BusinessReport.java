package com.wjf.moduledesignpattern.actionType.visitor.learn;

import java.util.LinkedList;
import java.util.List;

public class BusinessReport {
    List<Staff> staffs = new LinkedList<>();

    public BusinessReport(){
        staffs.add(new Manager("王经理"));
        staffs.add(new Engineer("工程师1"));
        staffs.add(new Engineer("工程师2"));
        staffs.add(new Engineer("工程师3"));
        staffs.add(new Engineer("工程师4"));
    }

    public void showReport(Visitor visitor){
        for (Staff staff : staffs){
            staff.accept(visitor);
        }
    }
}
