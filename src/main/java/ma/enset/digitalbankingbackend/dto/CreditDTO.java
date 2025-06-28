package ma.enset.digitalbankingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private String accountId;
    private double amount;
    private String description;
}