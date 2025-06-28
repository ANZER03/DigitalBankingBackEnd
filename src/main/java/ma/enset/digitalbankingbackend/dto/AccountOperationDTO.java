package ma.enset.digitalbankingbackend.dto;

import lombok.Data;
import ma.enset.digitalbankingbackend.enums.OperationType;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType operationType;
    private String description;
}
