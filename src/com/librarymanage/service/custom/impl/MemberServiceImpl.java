package com.librarymanage.service.custom.impl;

import com.librarymanage.dao.DaoFactory;
import com.librarymanage.dao.custom.MemberDao;
import com.librarymanage.dto.MemberDto;
import com.librarymanage.entity.MemberEntity;
import com.librarymanage.service.custom.MemberService;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao = (MemberDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEMBER);

    @Override
    public String save(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.create(entity)? "Success" : "Fail";
    }

    @Override
    public String update(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.update(entity)? "Success" : "Fail";
    }

    @Override
    public String delete(String memberId) throws Exception {
        return memberDao.delete(memberId)? "Success" : "Fail";
    }

    @Override
    public MemberDto getMember(String memberId) throws Exception {
        MemberEntity entity = memberDao.get(memberId);
        if (entity != null) {
            return getMemberDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<MemberDto> getAll() throws Exception {
        ArrayList<MemberEntity> memberEntities = memberDao.getAll();
        if (memberEntities != null && !memberEntities.isEmpty()) {
            ArrayList<MemberDto> memberDtos = new ArrayList<>();

            for (MemberEntity memberEntity : memberEntities) {
                memberDtos.add(getMemberDto(memberEntity));
            }
            return memberDtos;
        }
        return null;
    }

    @Override
    public String getLastID() throws Exception {
        String lastID = memberDao.getLastID();
        if (lastID !=null){
            return lastID;
        }
        return null;
    }

    @Override
    public ArrayList<MemberDto> search(String searchText) throws Exception {
        ArrayList<MemberEntity> memberEntities = memberDao.search(searchText);
        if (memberEntities != null && !memberEntities.isEmpty()) {
            ArrayList<MemberDto> memberDtos = new ArrayList<>();

            for (MemberEntity memberEntity : memberEntities) {
                memberDtos.add(getMemberDto(memberEntity));
            }
            return memberDtos;
        }
        return null;
    }

    private MemberEntity getMemberEntity(MemberDto memberDto) {
        return new MemberEntity(
                memberDto.getMemberID(),
                memberDto.getName(),
                memberDto.getAddress(),
                memberDto.getContact(),
                memberDto.getEmail(),
                memberDto.getMembershipDate()
        );
    }

    private MemberDto getMemberDto(MemberEntity memberEntity) {
        return new MemberDto(
                memberEntity.getMemberID(),
                memberEntity.getName(),
                memberEntity.getAddress(),
                memberEntity.getContact(),
                memberEntity.getEmail(),
                memberEntity.getMembershipDate(),
                new Button("Remove")
        );
    }
}
