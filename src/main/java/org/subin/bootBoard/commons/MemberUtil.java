package org.subin.bootBoard.commons;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.subin.bootBoard.constants.Role;
import org.subin.bootBoard.entities.Member;
import org.subin.bootBoard.models.member.MemberInfo;

@Component
public class MemberUtil {

    @Autowired
    private HttpSession session;

    /**
     * 로그인 여부
     * @return
     */
    public boolean isLogin() {

        return getMember() != null;
    }

    /**
     * 관리자 여부
     * @return
     */
    public boolean isAdmin() {

        return isLogin() && getMember().getRoles() == Role.ADMIN;
    }

    public MemberInfo getMember() {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        return memberInfo;
    }

    public Member getEntity() {
        if(isLogin()) {
            Member member = new ModelMapper().map(getMember(), Member.class);
            return member;
        }
        return null;
    }
}
