package org.czaplinski.library.model.mapper;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.controller.exception.CopyOfBookNotFoundExceptions;
import org.czaplinski.library.model.Borrow;
import org.czaplinski.library.service.BorrowerService;
import org.czaplinski.library.service.CopyOfBookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class BorrowMapper {
    BorrowerService borrowerService;
    CopyOfBookService copyOfBookService;
    public Borrow mapToNewBorrow(long borrowerId, long copyId) throws CopyOfBookNotFoundExceptions, BookNotFoundExceptions {
        return new Borrow(
                borrowerService.findBorrowerById(borrowerId),
                copyOfBookService.findCopyOfBookById(copyId),
                LocalDate.now(),
                null
        );
    }
}
