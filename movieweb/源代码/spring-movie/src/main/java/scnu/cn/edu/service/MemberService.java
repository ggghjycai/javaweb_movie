package scnu.cn.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import scnu.cn.edu.common.MD5Util;
import scnu.cn.edu.entity.Member;
import scnu.cn.edu.mapper.MemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends ServiceImpl<MemberMapper,Member> {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public Member login(String email,String password, int userType){
        // 进入数据库查询
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Member member = memberMapper.selectOne(queryWrapper);
        if(member == null){
            return null;
        }
        else{
            if(userType != member.getUserType()){
                return null;
            }
            else{
                if(member.getPassword().equals(MD5Util.md5(password))){
                    return member;
                }else {
                    return null;
                }
            }

        }
    }

    public Member findEmail(String email){
        Member member = memberMapper.selectById(email);
        return member;
    }


    public String register(String email,String password){
        Member member = new Member();
        member.setEmail(email);
        member.setPm(0);
        member.setUserType(0);
        //对密码进行加密
        String password1 = MD5Util.md5(password);
        member.setPassword(password1);
        memberMapper.insert(member);
        return "注册成功" ;
    }

    public Member becomeVIP(String email) {
        Member member = memberMapper.selectById(email);
        member.setPm(1);
        memberMapper.updateById(member);
        return member;
    }



}

