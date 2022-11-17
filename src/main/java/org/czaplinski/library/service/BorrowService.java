package org.czaplinski.library.service;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BorrowNotFoundExceptions;
import org.czaplinski.library.model.Borrow;
import org.czaplinski.library.model.Mail;
import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final LogService logService;
    private final MailService mailService;

    public boolean borrowBook(Borrow borrow) {
        if (borrow.getCopyOfBook().getStatus().equals(StatusOfBook.IN_USE)) {
            borrow.getCopyOfBook().setStatus(StatusOfBook.BORROWED);
            borrow.getCopyOfBook().setBorrow(borrow);
            borrowRepository.save(borrow);
            logService.saveLog(logMessageBorrow(borrow));
            mailService.send(mailMessageBorrow(borrow));
            return true;
        }
        return false;
    }

    public void returnBook(long borrowId) throws BorrowNotFoundExceptions {
        Borrow returnedBook = borrowRepository.findById(borrowId).orElseThrow(BorrowNotFoundExceptions::new);
        returnedBook.getCopyOfBook().setStatus(StatusOfBook.IN_USE);
        returnedBook.setReturnedDate(LocalDate.now());
        returnedBook.getCopyOfBook().setBorrow(null);
        borrowRepository.save(returnedBook);
        logService.saveLog(logMessageReturn(returnedBook));
        mailService.send(mailMessageReturn(returnedBook));
    }

    public List<Borrow> getAllBorrow() {
        return borrowRepository.findAll();
    }

    private static Mail mailMessageBorrow(Borrow borrow) {
        return new Mail(borrow.getBorrower().getEmailAddress(),
                "Borrow book ",
                "You borrow: " + borrow.getCopyOfBook().getBook().getTitle()
                        + "\n remember you should returned this book before: "
                        + borrow.getBorrowedDate().plusDays(30L));
    }

    private Mail mailMessageReturn(Borrow borrow) {
        return new Mail(borrow.getBorrower().getEmailAddress(),
                "Returned book: ",
                borrow.getCopyOfBook().getBook().getTitle());
    }

    private String logMessageBorrow(Borrow borrow) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(borrow.getBorrower().getFirstName());
        logMessage.append(" ");
        logMessage.append(borrow.getBorrower().getLastName());
        logMessage.append(" borrow ");
        logMessage.append(borrow.getCopyOfBook().getBook().getTitle());
        return logMessage.toString();
    }

    private String logMessageReturn(Borrow borrow) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(borrow.getBorrower().getFirstName());
        logMessage.append(" ");
        logMessage.append(borrow.getBorrower().getLastName());
        logMessage.append(" returned ");
        logMessage.append(borrow.getCopyOfBook().getBook().getTitle());
        return logMessage.toString();
    }
}
