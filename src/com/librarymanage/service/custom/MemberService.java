package com.librarymanage.service.custom;

import com.librarymanage.dto.MemberDto;
import com.librarymanage.service.SuperService;

import java.util.ArrayList;

public interface MemberService extends SuperService {
    String save(MemberDto memberDto) throws Exception;
    String update(MemberDto memberDto) throws Exception;
    String delete(String memberId) throws Exception;
    MemberDto getMember(String memberId) throws Exception;
    ArrayList<MemberDto> getAll() throws Exception;
    String getLastID() throws Exception;
    ArrayList<MemberDto> search(String searchText) throws Exception;
}
