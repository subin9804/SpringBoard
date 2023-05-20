package org.subin.bootBoard.models.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.entities.Member;
import org.subin.bootBoard.repositories.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = repository.findByUserId(username);

        if(member == null) {
            throw new UsernameNotFoundException(username);
        }

        return MemberInfo.builder()
                .userNo(member.getUserNo())
                .userId(member.getUserId())
                .userPw(member.getUserPw())
                .userNm(member.getUserNm())
                .email(member.getEmail())
                .mobile(member.getMobile())
                .build();

    }
}
