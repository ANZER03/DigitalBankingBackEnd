package ma.enset.digitalbankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digitalbankingbackend.dto.CustomerDTO;
import ma.enset.digitalbankingbackend.entities.Customer;
import ma.enset.digitalbankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.digitalbankingbackend.repositories.CustomerRepository;
import ma.enset.digitalbankingbackend.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
//@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CustomerRestController {

    private BankAccountService bankAccountService;
    private PasswordEncoder passwordEncoder;
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
//    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public List<CustomerDTO> getCostumers() {
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCostumers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return bankAccountService.searchCustomers("%" + keyword + "%");
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }

    @GetMapping("/customer/profile")
    public CustomerDTO getCustomerProfile(Authentication authentication) throws CustomerNotFoundException {
        JwtAuthenticationToken jwt = (JwtAuthenticationToken) authentication;
        String username = jwt.getToken().getSubject();
        return this.bankAccountService.getCustomerByName(username);
    }

    @PostMapping("/customer/changePassword")
    public void changePassword(Long customerId, String currentPassword, String newPassword, String confirmationPassword) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(""));

        if (!passwordEncoder.matches(currentPassword, customer.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        // Validate new password confirmation
        if (!newPassword.equals(confirmationPassword)) {
            throw new IllegalArgumentException("New password and confirmation do not match");
        }

        // Check if new password is different from current password
        if (passwordEncoder.matches(newPassword, customer.getPassword())) {
            throw new IllegalArgumentException("New password must be different from current password");
        }

        customer.setPassword(passwordEncoder.encode(newPassword));
        this.customerRepository.save(customer);
    }

}
