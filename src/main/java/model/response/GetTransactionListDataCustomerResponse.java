package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionListDataCustomerResponse {

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String customer_code;
    private int requested_amount;
}