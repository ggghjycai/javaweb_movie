package scnu.cn.edu.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import scnu.cn.edu.entity.Member;
import scnu.cn.edu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "*")
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/index/isLogin")
    @ResponseBody
    public String isLogin(HttpServletRequest request, HttpSession session){
        Member member = (Member) request.getServletContext().getAttribute("memberLogin");
        if(member != null){
            //用户已登录，可以进行操作
            return "{ \"result\":\"true\" }";
        }else{
            //用户未登录
            return "{ \"result\":\"false\" }";
        }
    }

    @RequestMapping("/index/toLogin")
    public String toLogin(HttpServletRequest request,HttpSession session){
        Member member= (Member) request.getServletContext().getAttribute("memberLogin");
        if(member!=null){
            return  "user";
        }
       else {
            return "login";
        }
    }

    @RequestMapping("/index/doLogin")
    @ResponseBody
    public String dologin(String email,String password,int userType, HttpServletRequest request, HttpSession session){
        Member member =memberService.login(email,password,userType);

        if(member != null){
            member.setPassword("");//去敏
//            session.setAttribute("memberLogin",member);
            request.getServletContext().setAttribute("memberLogin", member);
            return "{ \"result\":\"登录成功\" }";
        }else{
            return "{ \"result\":\"登录失败\" }";
        }
    }

    @RequestMapping  ("/user/information")
    @ResponseBody
    public String getUserInfo(Model model,HttpServletRequest request, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Member member = (Member) request.getServletContext().getAttribute("memberLogin");
        if (member == null){
            return "";
        }
        model.addAttribute("userEmail",member.getEmail());
        model.addAttribute("pm",member.getPm());
        model.addAttribute("userType", member.getUserType());

        map.put("userEmail", member.getEmail());
        map.put("pm", member.getPm());
        map.put("userType",member.getUserType());

        //返回json数据
        String str = JSON.toJSONString(map);
        return str;
    }

    @RequestMapping("/user/become")
    @ResponseBody
    public String becomeVIP(HttpServletRequest request, HttpSession session){
        Member member = (Member) request.getServletContext().getAttribute("memberLogin");
        Member member1 = memberService.becomeVIP(member.getEmail());
        request.getServletContext().setAttribute("memberLogin", member1);

        return JSON.toJSONString(member1.getPm());
    }



    @RequestMapping("/user/logout")
    @ResponseBody
    public boolean deleteUser(HttpServletRequest request, HttpSession session){
        request.getServletContext().removeAttribute("memberLogin");
        return true;
    }


    @RequestMapping("/index/toRegister")
    public String toRegister(){ return "register";}

    @RequestMapping("/index/doRegister")
    @ResponseBody
    public String doRegister(String email, String password){
        return memberService.register(email, password);
    }

    @RequestMapping("/index/checkemail")
    @ResponseBody
    public String checkEmail(String email){
        Member member = memberService.findEmail(email);
        if(member != null){
            return "该邮件已被注册";
        }
        else{
            return "该邮件可用！";
        }
    }
}
