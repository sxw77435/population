package com.uni.population.authentication.model.dto;

import com.uni.population.member.model.dto.MemberDto;
import com.uni.population.member.model.dto.MemberRoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class CustomUser extends User {

    private int no;
    private String id;
    private String pwd;
    private String name;
    private List<MemberRoleDTO> memberRoleList;

    public CustomUser(MemberDto member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getId(), member.getPwd(), authorities);
        setDetails(member);
    }

    private void setDetails(MemberDto member){
        this.no = member.getNo();
        this.id = member.getId();
        this.pwd = member.getPwd();
        this.name = member.getName();
        this.memberRoleList = member.getMemberRoleList();
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberRoleDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", memberRoleList=" + memberRoleList +
                '}';
    }
}
