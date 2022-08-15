package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jack.entity.UserInfo;
import com.jack.result.Result;
import com.jack.result.ResultCodeEnum;
import com.jack.service.UserInfoService;
import com.jack.util.MD5;
import com.jack.vo.LoginVo;
import com.jack.vo.RegisterVo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/13
 * @Description :
 **/
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Reference
    private UserInfoService userInfoService;

    /**
     * 登出
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("USER");
        return Result.ok();
    }

    /**
     * 会员登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo,HttpServletRequest request){
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();

        //判空校验
        if (phone.isEmpty() || password.isEmpty()){
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }

        //校验用户信息
        UserInfo userInfo = userInfoService.getByPhone(phone);
        if(null == userInfo) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (!MD5.encrypt(password).equals(userInfo.getPassword())){
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }

        //校验用户状态
        if (userInfo.getStatus() == 0){
            return Result.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }

        //保存session
        request.getSession().setAttribute("USER",userInfo);

        //返回前端
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone",userInfo.getPhone());
        map.put("nickName",userInfo.getNickName());
        return Result.ok(map);
    }

    /**
     * 会员注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpServletRequest request){
        String nickName = registerVo.getNickName();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        // 参数判空校验
        if (nickName.isEmpty() || phone.isEmpty() || password.isEmpty() || code.isEmpty()){
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }

        // 验证码校验
        String currentCode  =(String) request.getSession().getAttribute("CODE");
        if (!code.equals(currentCode)){
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }

        // 判断当前手机号是否已注册
        UserInfo userInfo = userInfoService.getByPhone(phone);
        if (userInfo != null){
            return Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }

        // 保存至数据库
        UserInfo info = new UserInfo();
        info.setNickName(nickName);
        info.setPhone(phone);
        info.setPassword(MD5.encrypt(password));
        info.setStatus(1);
        userInfoService.insert(info);
        return Result.ok();
    }

    @GetMapping("/sendCode/{moble}")
    public Result sendCode(@PathVariable String moble, HttpServletRequest request) {
        String code = "1111";
        request.getSession().setAttribute("CODE", code);
        return Result.ok(code);
    }

}
