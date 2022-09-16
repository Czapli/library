package org.czaplinski.library.repository;

import org.czaplinski.library.model.CopyOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyOfBookRepository extends JpaRepository<CopyOfBook, Long> {
    @Query(nativeQuery = true)
    Integer findNumberOfCopies(@Param("BOOK_ID") long lookingTitleId);

}
