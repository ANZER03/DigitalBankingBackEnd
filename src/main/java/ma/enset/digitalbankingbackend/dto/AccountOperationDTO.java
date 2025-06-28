package ma.enset.digitalbankingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.digitalbankingbackend.enums.OperationType;

import java.util.Date;

@Data @NoArgsConstructor
@AllArgsConstructor
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType operationType;
    private String description;
}
