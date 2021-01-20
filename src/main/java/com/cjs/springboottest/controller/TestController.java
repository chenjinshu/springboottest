package com.cjs.springboottest.controller;

import com.cjs.springboottest.util.HttpClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chen.jinshu (青禾)
 * 2019/07/08
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "testController", description = "testController相关api")
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "hello", notes = "hello")
    @ApiResponses({ @ApiResponse(code = 200, message = "success"),@ApiResponse(code = 400, message = "{code:****,message:'fail'}")})
    public String hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "hello~";
    }

    @RequestMapping(value = "/responseCookie", method = RequestMethod.GET)
    @ApiOperation(value = "responseCookie", notes = "responseCookie")
    @ApiResponses({ @ApiResponse(code = 200, message = "success"),@ApiResponse(code = 400, message = "{code:****,message:'fail'}")})
    public void responseCookie(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                System.out.println(cookies[i].getName() + " - " + cookies[i].getValue() + " - " + cookies[i].getSecure());
            }
        }

        Cookie cookie = new Cookie("user", request.getParameter("username") + "==" + request.getParameter("password"));
        cookie.setMaxAge(-1);
        cookie.setSecure(true);          // 只有https请求才会携带该cookie
        response.addCookie(cookie);
    }

    @RequestMapping(value = "/dispatch", method = RequestMethod.GET)
    @ApiOperation(value = "dispatch", notes = "dispatch")
    @ApiResponses({ @ApiResponse(code = 200, message = "success"),@ApiResponse(code = 400, message = "{code:****,message:'fail'}")})
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hello");
        requestDispatcher.forward(request, response);
    }
}
