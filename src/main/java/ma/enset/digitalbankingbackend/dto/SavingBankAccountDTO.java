package ma.enset.digitalbankingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.digitalbankingbackend.enums.AccountStatus;

import java.util.Date;

@Data @NoArgsConstructor
@AllArgsConstructor
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}