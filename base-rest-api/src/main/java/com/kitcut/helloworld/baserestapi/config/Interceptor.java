package com.kitcut.helloworld.baserestapi.config;

import com.kitcut.helloworld.baserestapi.bean.UserSession;
import com.kitcut.helloworld.baserestapi.entity.TokenEntity;
import com.kitcut.helloworld.baserestapi.entity.UserEntity;
import com.kitcut.helloworld.baserestapi.service.PermissionUserService;
import com.kitcut.helloworld.baserestapi.service.TokenService;
import com.kitcut.helloworld.baserestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private UserSession userSession;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PermissionUserService permissionUserService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
//        Method method = ((HandlerMethod) handler).getMethod();
//        Permission permission = method.getAnnotation(Permission.class);
//        if (permission == null)
//            return true;
//        else {
//            String token = request.getHeader("Authorization");
//
//            //check token empty
//            if (StringUtils.isBlank(token)) {
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                String msg = MessageSourceUtils.getMessage("unauthorized.token.empty");
//                response.getWriter().append(msg);
//                return false;
//            } else {
//                //check exist token
//                TokenEntity tokenEntity = tokenService.findByToken(token);
//                if (tokenEntity == null) {
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    String msg = MessageSourceUtils.getMessage("unauthorized.token.not-found");
//                    response.getWriter().append(msg);
//                    return false;
//                } else {
//                    //check token expired
//                    if (tokenEntity.getExpiredTime().before(new Date())) {
//                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                        String msg = MessageSourceUtils.getMessage("unauthorized.token.expired");
//                        response.getWriter().append(msg);
//                        return false;
//                    } else {
//                        if (StringUtils.isBlank(permission.value())) {
//                            setUserSession(tokenEntity);
//                            return true;
//                        } else {
//                            PermissionUserEntity permissionUserEntity = permissionUserService
//                                    .findByUserIdAndPermissionName(tokenEntity.getUserId(), permission.value());
//                            if (permissionUserEntity == null) {
//                                response.setStatus(HttpStatus.FORBIDDEN.value());
//                                String msg = MessageSourceUtils.getMessage("permission.access-denied");
//                                response.getWriter().append(msg);
//                                return false;
//                            } else {
//                                setUserSession(tokenEntity);
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    private void setUserSession(TokenEntity tokenEntity) {
        UserEntity userEntity = userService.findById(tokenEntity.getUserId());
        userSession.setUserId(tokenEntity.getUserId());
        userSession.setUserName(userEntity.getName());
        userSession.setTokenEntity(tokenEntity);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion");
//    }

//    private String getBody(HttpServletRequest req) {
//        String body = "";
//        if (req.getMethod().equals("POST")) {
//            StringBuilder sb = new StringBuilder();
//            BufferedReader bufferedReader = null;
//
//            try {
//                bufferedReader = req.getReader();
//                char[] charBuffer = new char[128];
//                int bytesRead;
//                while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
//                    sb.append(charBuffer, 0, bytesRead);
//                }
//            } catch (IOException ex) {
//                // swallow silently -- can't get body, won't
//            } finally {
//                if (bufferedReader != null) {
//                    try {
//                        bufferedReader.close();
//                    } catch (IOException ex) {
//                        // swallow silently -- can't get body, won't
//                    }
//                }
//            }
//            body = sb.toString();
//        }
//        return body;
//    }
}
