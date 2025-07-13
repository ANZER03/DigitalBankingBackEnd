package ma.enset.digitalbankingbackend.security;

import ma.enset.digitalbankingbackend.entities.Customer;
import ma.enset.digitalbankingbackend.repositories.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomerDetailsService implements UserDetailsService {
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = this.customerRepository.findByName(username);
        if (customer == null) throw new UsernameNotFoundException("User not found");

        return User.withUsername(customer.getName())
                .password(customer.getPassword())
                .roles(customer.getRoles().toArray(new String[0]))
                .build();
    }
}
