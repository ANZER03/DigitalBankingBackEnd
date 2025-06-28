package ma.enset.digitalbankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DiscriminatorValue("SA")
@AllArgsConstructor @NoArgsConstructor
public class SavingAccount extends BankAccount{
    private double interestRate;
}
