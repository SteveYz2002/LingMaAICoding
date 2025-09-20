package com.steve.steveaicode.aop;

import com.steve.steveaicode.annotation.AuthCheck;
import com.steve.steveaicode.exception.BusinessException;
import com.steve.steveaicode.exception.ErrorCode;
import com.steve.steveaicode.model.entity.User;
import com.steve.steveaicode.model.enums.UserRoleEnum;
import com.steve.steveaicode.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     * @param joinPoint     切入点
     * @param authCheck     权限校验注解
     * @return
     * @throws Throwable
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint,  AuthCheck authCheck) throws  Throwable {
        String mustRole = authCheck.mustRole();
        //  校验用户是否登录，是否具有相应权限
        RequestAttributes  requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        if(mustRoleEnum == null){
            return joinPoint.proceed();
        }
        // 下面的必须有管理员权限
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if(userRoleEnum == null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "没有权限");
        }
        // 要求必须有管理员权限
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "没有权限");
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}
