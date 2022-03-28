package com.wanghaoyu.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data //会自动生成构造方法和get set方法
public class Result  implements Serializable {
    private int code; //200表示成功 非200表示失败
    private String msg;
    private Object data;

    //重载一下succ方法
    public static Result succ(Object data){
        return succ(200,"操作成功",data);
    }

    public static Result succ(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return  result;
    }

    //失败的情况
    public static Result fail(String msg){
        return fail(400,msg,null);
    }

    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }

    public static Result fail(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return  result;
    }
}
