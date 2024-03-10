package com.uni.population.member.model.dao;

import com.uni.population.member.model.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    MemberDto findMemberById(String username);
}
