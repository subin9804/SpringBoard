package org.subin.bootBoard.configs.interceptors;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.subin.bootBoard.commons.configs.ConfigInfoService;

import java.util.Map;

/**
 * 사이트 설정 유지
 */
@Component
@RequiredArgsConstructor
public class SiteConfigInterceptor implements HandlerInterceptor {

    private final ConfigInfoService infoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /** 사이트 설정 조회 */
        Map<String, String> siteConfigs = infoService.get("siteConfig", new TypeReference<Map<String, String>>(){});
        System.out.println(siteConfigs);

        request.setAttribute("siteConfig", siteConfigs);

        return true;
    }
}
