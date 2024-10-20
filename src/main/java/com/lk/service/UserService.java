package com.lk.service;

import com.lk.dao.UserDao;
import com.lk.domain.Result;
import com.lk.domain.User;
import com.lk.domain.Video;
import com.lk.utils.JwtUtil;
import com.lk.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public Result register(String id, String password) {
        User user=userDao.findById(id);
        if(user==null){
            userDao.register(id, Md5Util.getMD5String(password));
            return Result.success();
        }else {
            return Result.error("该用户已存在");
        }
    }

    public Result login(String id, String password) {
        User user=userDao.findById(id);
        if(user==null){
            return Result.error("用户不存在");
        }else {
            if(Md5Util.checkPassword(password,user.getPassword())){
                Map<String,Object> claim=new HashMap<>();
                claim.put("id",user.getId());
                String token= JwtUtil.genToken(claim);
                return Result.success(token);
            }else {
                return Result.error("密码错误");
            }
        }
    }

    public Result getFavorite(String id) {
        List<Video> videos= userDao.getFavorite(id);
        return Result.success(videos);
    }
}
