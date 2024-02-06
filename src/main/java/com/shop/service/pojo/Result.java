package com.shop.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object result;

//  增删改  成功响应
    public static Result success() {return new Result(1,"susscess",null);}
//  查询    成功响应
    public static Result success(Object data) {return new Result(1,"susscess",data);}
//  失败响应
    public static Result error(String msg) {return new Result(0,msg,null);}

}
