package org.czaplinski.library.service;

import com.fasterxml.jackson.core.PrettyPrinter;
import org.czaplinski.library.model.CopyOfBook;
import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.repository.BookRepository;
import org.czaplinski.library.repository.CopyOfBookRepository;
import org.springframework.stereotype.Service;

@Service
public class CopyOfBookService {
    private final CopyOfBookRepository copyOfBookRepository;
    private final BookRepository bookRepository;

    public CopyOfBookService(CopyOfBookRepository copyOfBookRepository, BookRepository bookRepository) {
        this.copyOfBookRepository = copyOfBookRepository;
        this.bookRepository = bookRepository;
    }

    public void addCopyOfBook(long bookId, StatusOfBook statusOfBook) {
        copyOfBookRepository.save(
                new CopyOfBook(
                        bookRepository.findById(bookId).orElseThrow(),
                        statusOfBook));
    }

    public void changeStatus(long copyOfBookId, StatusOfBook statusOfBook) {
        CopyOfBook copyOfBookToUpdate = copyOfBookRepository.findById(copyOfBookId).orElseThrow();
        copyOfBookToUpdate.setStatus(statusOfBook);
        copyOfBookRepository.save(copyOfBookToUpdate);
    }

    public int numberOfCopies(long copyId) {
        CopyOfBook copyOfBook = copyOfBookRepository.findById(copyId).orElseThrow();
        long lookingTitleId = copyOfBook.getBook().getId();
        return copyOfBookRepository.findNumberOfCopies(lookingTitleId);
    }
}
