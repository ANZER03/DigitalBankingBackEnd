package ma.enset.digitalbankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.digitalbankingbackend.enums.OperationType;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperations {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date operationdate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;

}
