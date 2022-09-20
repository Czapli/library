package org.czaplinski.library.model.mapper;

import org.czaplinski.library.model.Borrower;
import org.czaplinski.library.model.dto.BorrowerDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowerMapper {
    public Borrower mapToBorrower(BorrowerDto borrowerDto){
        return new Borrower(
                borrowerDto.getFirstName(),
                borrowerDto.getLastName(),
                borrowerDto.getEmailAddress(),
                borrowerDto.getParcelLocker(),
                LocalDate.now()
        );
    }
}
