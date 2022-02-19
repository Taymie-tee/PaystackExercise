package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionListDataResponse {

    private int id;
    private String domain;
    private String status;
    private int amount;
    private String currency;
    private String transaction_date;
    private String reference;
    private String gateway_response;
    private String created_at;
    private String channel;
    private GetTransactionListDataCustomerResponse customer;
}
