package org.czaplinski.library.service;


import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.CopyOfBookNotFoundExceptions;
import org.czaplinski.library.model.CopyOfBook;
import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.repository.BookRepository;
import org.czaplinski.library.repository.CopyOfBookRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CopyOfBookService {
    private final CopyOfBookRepository copyOfBookRepository;

    public void addCopyOfBook(CopyOfBook copyOfBook) {
        copyOfBookRepository.save(copyOfBook);
    }
    public CopyOfBook findCopyOfBookById(long copyOfBookId) throws CopyOfBookNotFoundExceptions {
        return copyOfBookRepository.findById(copyOfBookId).orElseThrow(CopyOfBookNotFoundExceptions::new);
    }

    public void changeStatus(long copyOfBookId, StatusOfBook statusOfBook) throws CopyOfBookNotFoundExceptions {
        CopyOfBook copyOfBookToUpdate = copyOfBookRepository.findById(copyOfBookId).orElseThrow(CopyOfBookNotFoundExceptions::new);
        copyOfBookToUpdate.setStatus(statusOfBook);
        copyOfBookRepository.save(copyOfBookToUpdate);
    }

    public int numberOfCopies(long copyId) throws CopyOfBookNotFoundExceptions {
        CopyOfBook copyOfBook = copyOfBookRepository.findById(copyId).orElseThrow(CopyOfBookNotFoundExceptions::new);
        long lookingTitleId = copyOfBook.getBook().getId();
        return copyOfBookRepository.findNumberOfCopies(lookingTitleId);
    }
}
