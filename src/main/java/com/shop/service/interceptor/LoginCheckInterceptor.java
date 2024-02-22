package com.shop.service.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.shop.service.pojo.Result;
import com.shop.service.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//这边是用于写拦截器的具体操作,拦截器的配置在config文件夹里面(类似axios的二次封装)
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override  //目标资源方法(视图,controller层)运行前运行, return true 放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");
        //这边做登录校验操作

        //1.获取请求头token
        String jwt = request.getHeader("token");
        //2.判断jwt令牌是否为空串或者null
        if(!StringUtils.hasLength(jwt)){
            log.info("token为空,未登录");
            Result error = Result.error("NOT_LOGIN");
            //之前在controller层可以自动转化为json格式,这边需要手动转换,依赖了阿里的fastjson
            String noLogin = JSONObject.toJSONString(error);
            //响应给浏览器
            response.setStatus(401); //给前端响应码
            response.getWriter().write(noLogin);
            return false;
        }
        //3.存在jwt令牌,校验jwt令牌
        try {
            JwtUtils.ParseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //之前在controller层可以自动转化为json格式,这边需要手动转换,依赖了阿里的fastjson
            String noLogin = JSONObject.toJSONString(error);
            response.setStatus(401); //给前端响应码
            //响应给浏览器
            response.getWriter().write(noLogin);
            return false;
        }
        //4.放行
        log.info("合法,放行");
        return true;
    }

    @Override // 运行后
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override //试图渲染完毕后运行,最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");

    }
}
