package ma.enset.digitalbankingbackend;

import ma.enset.digitalbankingbackend.dto.BankAccountDTO;
import ma.enset.digitalbankingbackend.dto.CurrentBankAccountDTO;
import ma.enset.digitalbankingbackend.dto.CustomerDTO;
import ma.enset.digitalbankingbackend.dto.SavingBankAccountDTO;
import ma.enset.digitalbankingbackend.entities.Customer;
import ma.enset.digitalbankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.digitalbankingbackend.repositories.CustomerRepository;
import ma.enset.digitalbankingbackend.services.BankAccountServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class DigitalBankingBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingBackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountServiceImp bankAccountService, CustomerRepository customerRepository, PasswordEncoder encoder) {
        return args -> {
            Stream.of("Hassan", "Imane", "Mohamed", "Anouar", "admin").forEach(name -> {
                Customer customer;
                if (name.equals("admin")) {
                    customer = Customer.builder()
                            .name(name)
                            .email(name + "@gmail.com")
                            .password(encoder.encode("1234"))
                            .roles(Arrays.asList("ADMIN", "USER"))
                            .build();
                } else {
                    customer = Customer.builder()
                            .name(name)
                            .email(name + "@gmail.com")
                            .password(encoder.encode("1234"))
                            .roles(List.of("USER"))
                            .build();
                }
                customerRepository.save(customer);


            });
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 0; i < 10; i++) {
                    String accountId;
                    if (bankAccount instanceof SavingBankAccountDTO) {
                        accountId = ((SavingBankAccountDTO) bankAccount).getId();
                    } else {
                        accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId, 10000 + Math.random() * 120000, "Credit");
                    bankAccountService.debit(accountId, 1000 + Math.random() * 9000, "Debit");
                }
            }
        };
    }

}
