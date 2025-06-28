package ma.enset.digitalbankingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private long id;
    private String name;
    private String email;
}
