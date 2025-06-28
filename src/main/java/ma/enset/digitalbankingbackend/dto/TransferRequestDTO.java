package ma.enset.digitalbankingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;
}
