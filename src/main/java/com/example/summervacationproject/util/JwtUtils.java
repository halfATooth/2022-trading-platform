package com.example.summervacationproject.util;

import com.example.summervacationproject.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtUtils {
    @Resource
    private UserService userService;

    //这是防止空指针的操作
    public static JwtUtils JwtUtils;
    @PostConstruct
    public void init() {
        JwtUtils = this;
        JwtUtils.userService = this.userService;
    }
    /**
     * 过期时间2小时
     */
    private static final long EXPIRE_TIME=2*60*60*1000;
    /**
     * 加密密钥
     */
    private static final String KEY = "lihaoshuo";

    /**
     * 生成token
     * @param id    用户id
     * @param userName  用户名
     * @return
     */
    public String createToken(String id,String userName){
        Map<String,Object> header = new HashMap();
        header.put("typ","JWT");
        header.put("alg","HS256");
        JwtBuilder builder = Jwts.builder().setHeader(header)
                .setId(id)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .setSubject(userName)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,KEY);
        return builder.compact();
    }

    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public int verify(String token){
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return 2;
        }
        //从token中获取用户id，查询该Id的用户是否存在，存在则token验证通过
        Integer id = Integer.parseInt(claims.getId());
        //这是防止空指针的操作
        String username = JwtUtils.userService.getName(id);
        if(username != null){
            return 1;
        }else{
            return 0;
        }
    }
}
