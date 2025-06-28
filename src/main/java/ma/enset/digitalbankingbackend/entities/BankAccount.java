package ma.enset.digitalbankingbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.digitalbankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Data @Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorColumn(name = "type", length = 4)
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount" , fetch = FetchType.LAZY)
    private List<AccountOperations> accountOperations;

}
