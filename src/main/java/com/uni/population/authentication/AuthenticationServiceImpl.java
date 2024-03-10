package com.uni.population.authentication;

import com.uni.population.authentication.model.dto.CustomUser;
import com.uni.population.member.model.dao.MemberDao;
import com.uni.population.member.model.dto.MemberDto;
import com.uni.population.member.model.dto.MemberRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final MemberDao memberDao;

    @Autowired
    public AuthenticationServiceImpl(MemberDao memberDao){ this.memberDao= memberDao;}

    @Override
    public Map<String, List<String>> getPermitListMap() {
        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new ArrayList<>(); // 권한별로 접근가능한 url리스트를 담을 리스트를만듬
        List<String> memberPermitList = new ArrayList<>();

        adminPermitList.add("/nation/nationinsert");
        adminPermitList.add("/nation/nationupdateform/{nationno}");

        memberPermitList.add("/nation/populationlist");


//      메뉴보기 페이지는 아무거나 (로그인 안할때도 볼수 있음)

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("memberPermitList", memberPermitList);

        return permitListMap;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("호출확인");
        System.out.println("username : "+username);

        MemberDto member = memberDao.findMemberById(username);

        System.out.println(member);


        if(member == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        List<MemberRoleDTO> memberRoleList = member.getMemberRoleList();
        List<GrantedAuthority> authorities = new ArrayList<>();
        memberRoleList.forEach(memberRole -> authorities.add(new SimpleGrantedAuthority(memberRole.getAuthority().getName())));

        System.out.println(authorities);
        return new CustomUser(member, authorities);
    }


}
