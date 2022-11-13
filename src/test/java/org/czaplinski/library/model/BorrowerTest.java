package org.czaplinski.library.model;

import org.czaplinski.library.model.dto.BorrowerDto;
import org.czaplinski.library.model.mapper.BorrowerMapper;
import org.czaplinski.library.repository.BorrowerRepository;
import org.czaplinski.library.service.BorrowerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BorrowerTest {

    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private BorrowerMapper borrowerMapper;
    @Autowired
    private BorrowerService borrowerService;

    @Test
    void addUserToRepositoryTest() {

        //Given
        Borrower borrower = new Borrower("firstName test", "lastName test", "test@czaplinski.org", "1234", LocalDate.now());
        borrowerRepository.save(borrower);

        //When
        Borrower shouldFindBorrower = borrowerRepository.findById(borrower.getId()).orElseThrow();

        //Then
        assertEquals(borrower.getId(), shouldFindBorrower.getId());
    }
    @Test
    void addUserToRepositoryByServiceTest() {

        //Given
        Borrower borrower = new Borrower("firstName test", "lastName test", "test@czaplinski.org", "1234", LocalDate.now());
        borrowerService.addBorrower(borrower);

        //When
        Borrower shouldFindBorrower = borrowerRepository.findById(borrower.getId()).orElseThrow();

        //Then
        assertEquals(borrower.getId(), shouldFindBorrower.getId());
    }

    @Test
    void mapUserDtoToUserTest() {
        //Given
        BorrowerDto borrowerDto = new BorrowerDto("firstName test", "lastName test", "test@czaplinski.org", "1234");

        //When
        Borrower borrower = borrowerMapper.mapToBorrower(borrowerDto);

        //Then
        assertEquals(borrowerDto.getFirstName(), borrower.getFirstName());
        assertEquals(borrowerDto.getLastName(), borrower.getLastName());
        assertEquals(borrowerDto.getEmailAddress(), borrower.getEmailAddress());
        assertEquals(borrowerDto.getParcelLocker(), borrower.getParcelLocker());
        assertEquals(LocalDate.now(), borrower.getAccountCreationDate());
    }
}