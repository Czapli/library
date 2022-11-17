package org.czaplinski.library.repository;

import org.czaplinski.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
