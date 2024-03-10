package com.uni.population.member.model.dto;

public class MemberRoleDTO {
    private int memberNo;					// 회원번호
    private int authorityCode;				// 권한코드

    private AuthorityMemberDTO authority;	// 회원보유권한

    public MemberRoleDTO() {
    }

    public MemberRoleDTO(int memberNo, int authorityCode, AuthorityMemberDTO authority) {
        this.memberNo = memberNo;
        this.authorityCode = authorityCode;
        this.authority = authority;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(int authorityCode) {
        this.authorityCode = authorityCode;
    }

    public AuthorityMemberDTO getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityMemberDTO authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "MemberRoleDTO [memberNo=" + memberNo + ", authorityCode=" + authorityCode + ", authority=" + authority
                + "]";
    }
}
