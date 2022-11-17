package org.czaplinski.library.service;

import lombok.RequiredArgsConstructor;
import org.czaplinski.library.config.ServiceAccountConfig;
import org.czaplinski.library.model.Borrow;
import org.czaplinski.library.model.Mail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class checkBorrowService {
    private final BorrowService borrowService;
    private final MailService mailService;
    private final ServiceAccountConfig adminConfig;
    private final LogService logService;

    //@Scheduled(cron = "0 0 6 * * *") //uncomment before sending on heroku
    @Scheduled(fixedDelay = 50000)              //to remove
    private void listMoreThan20Days() {
        List<Borrow> allBorrows = borrowService.getAllBorrow();
        List<Borrow> TwentyDaysPassedList = allBorrows.stream()
                .filter(borrow -> borrow.getReturnedDate() == null)
//            .filter(borrow -> borrow.getBorrowedDate().plusDays(20L).isEqual(LocalDate.now()))  //uncomment before sending on heroku
                .toList();
        TwentyDaysPassedList.forEach(borrow -> mailService.send(infoMail(borrow)));
    }

    //@Scheduled(cron = "0 0 6 * * MON-FRI") //uncomment before inserting on heroku
    @Scheduled(fixedDelay = 50000)          //to remove
    private void listUnreturnedBooks() {
        List<Borrow> allBorrows = borrowService.getAllBorrow();
        List<Borrow> timeHasExpiredList = allBorrows.stream()
                .filter(borrow -> borrow.getReturnedDate() == null)
//                .filter(borrow -> borrow.getBorrowedDate().plusDays(30L).isAfter(LocalDate.now())) //uncomment before sending on heroku
                .toList();
        if (timeHasExpiredList.size() >= 1) {
            mailService.send(expiredListMail(timeHasExpiredList));
            logService.saveLog("Number of late returners: " + timeHasExpiredList.size());
        }
    }

    private Mail expiredListMail(List<Borrow> borrows) {
        return new Mail(
                adminConfig.getServiceMail(),
                "Information about unreturned book",
                listUnreturnedBooksText(borrows)
        );
    }

    private String listUnreturnedBooksText(List<Borrow> borrows) {
        StringBuilder returnedText = new StringBuilder();
        returnedText.append("dear Administrator,\nI am sending a list of unreturned books along with the people who donated them\n");
        borrows.forEach(borrow -> {
            returnedText.append(borrow.getCopyOfBook().getBook().getTitle());
            returnedText.append(" is borrowed by ");
            returnedText.append(borrow.getBorrower().getLastName());
            returnedText.append(" for ");
            returnedText.append(Duration.between(borrow.getBorrowedDate().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays());
            returnedText.append(" days\n");
        });
        System.out.println(returnedText);        //to remove
        return returnedText.toString();
    }

    private Mail infoMail(Borrow borrow) {
        return new Mail(
                borrow.getBorrower().getEmailAddress(),
                "Information about returned book",
                infoDaysLeft(borrow)
        );
    }

    private String infoDaysLeft(Borrow borrow) {
        StringBuilder returnedText = new StringBuilder();
        returnedText.append("Dear ");
        returnedText.append(borrow.getBorrower().getLastName());
        returnedText.append(",\n\tYou have ");
        returnedText.append(Duration.between(borrow.getBorrowedDate().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays());
        returnedText.append(" days left to return book: ");
        returnedText.append(borrow.getCopyOfBook().getBook().getTitle());
        System.out.println(returnedText);                       //to remove
        return returnedText.toString();
    }
}

