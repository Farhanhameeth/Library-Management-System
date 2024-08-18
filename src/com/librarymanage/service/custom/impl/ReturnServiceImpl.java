package com.librarymanage.service.custom.impl;

import com.librarymanage.Enum.DaoType;
import com.librarymanage.dao.DaoFactory;
import com.librarymanage.dao.custom.BookDao;
import com.librarymanage.dao.custom.BorrowingDao;
import com.librarymanage.dao.custom.MemberDao;
import com.librarymanage.dao.custom.ReturnDao;
import com.librarymanage.db.DBConnection;
import com.librarymanage.dto.ReturnDto;
import com.librarymanage.entity.BookEntity;
import com.librarymanage.entity.BorrowingEntity;
import com.librarymanage.entity.MemberEntity;
import com.librarymanage.entity.ReturnEntity;
import com.librarymanage.service.custom.ReturnService;

import java.sql.Connection;
import java.util.ArrayList;

public class ReturnServiceImpl implements ReturnService {

    private final ReturnDao returnDao = (ReturnDao) DaoFactory.getInstance().getDao(DaoType.RETURN);
    private final BookDao bookDao = (BookDao) DaoFactory.getInstance().getDao(DaoType.BOOK);
    private final BorrowingDao borrowingDao = (BorrowingDao) DaoFactory.getInstance().getDao(DaoType.BORROWING);
    private final MemberDao memberDao = (MemberDao) DaoFactory.getInstance().getDao(DaoType.MEMBER);


    @Override
    public String save(ReturnDto returnDto) throws Exception {
        ReturnEntity entity = getReturnEntity(returnDto);
        return returnDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public ArrayList<ReturnDto> getAll() throws Exception {
        ArrayList<ReturnEntity> returnEntities = returnDao.getAll();

        if (returnEntities != null && !returnEntities.isEmpty()) {
            ArrayList<ReturnDto> returnDtos = new ArrayList<>();

            for (ReturnEntity returnEntity : returnEntities) {
                ReturnDto returnDto = getReturnDto(returnEntity);

                MemberEntity memberEntity = memberDao.get(returnEntity.getMemberId());
                if (memberEntity != null) {
                    returnDto.setMemberName(memberEntity.getName());
                }

                BookEntity bookEntity = bookDao.get(returnEntity.getBookId());
                if (bookEntity != null) {
                    returnDto.setBookTitle(bookEntity.getTitle());
                }

                BorrowingEntity borrowingEntity = borrowingDao.get(returnEntity.getBorrowId());
                if (borrowingEntity != null) {
                    returnDto.setDueDate(borrowingEntity.getDueDate());
                }
                returnDto.setPaid(returnEntity.isPaid());
            returnDtos.add(returnDto);
            }
        return returnDtos;
        }
        return null;
    }

    @Override
    public String getLastId() throws Exception {
        return returnDao.getLastID();
    }

    @Override
    public String returnBook(ReturnDto returnDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);

            ReturnEntity returnEntity = new ReturnEntity(
                    returnDto.getReturnId(),
                    returnDto.getReturnDate(),
                    returnDto.getFine(),
                    returnDto.getBorrowId(),
                    returnDto.getMemberId(),
                    returnDto.getBookId(),
                    returnDto.getIsPaid()
            );
            if (returnDao.create(returnEntity)) {

                BookEntity bookEntity = bookDao.get(returnDto.getBookId());
                bookEntity.setQoh(bookEntity.getQoh() + 1);

                if (bookDao.update(bookEntity)) {

                    if (borrowingDao.updateReturnStatus(returnDto.getBorrowId())) {
                        connection.commit();
                        return "Success";
                    } else {
                        connection.rollback();
                        return "Borrowing Delete Failed...";
                    }
                } else {
                    connection.rollback();
                    return "Book Update Failed...";
                }
            } else {
                connection.rollback();
                return "Book Return Failed...";
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private ReturnDto getReturnDto(ReturnEntity returnEntity) {
        return new ReturnDto(
                returnEntity.getReturnId(),
                returnEntity.getReturnDate(),
                returnEntity.getFine(),
                returnEntity.getBorrowId(),
                returnEntity.getMemberId(),
                "",
                returnEntity.getBookId(),
                "",
                null,
                returnEntity.isPaid()
        );
    }

    private ReturnEntity getReturnEntity(ReturnDto returnDto) {
        return new ReturnEntity(
                returnDto.getReturnId(),
                returnDto.getReturnDate(),
                returnDto.getFine(),
                returnDto.getBorrowId(),
                returnDto.getMemberId(),
                returnDto.getBookId(),
                returnDto.getIsPaid()
        );
    }
}
