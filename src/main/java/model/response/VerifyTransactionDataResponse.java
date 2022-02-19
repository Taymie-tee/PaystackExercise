package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyTransactionDataResponse {

    private int amount;
    private String currency;
    private String transaction_date;
    private String status;
    private String reference;
    private String domain;
    private int metadata;
    private String gateway_response;
    private String channel;
    private String ip_address;
    private VerifyTransactionDataLogResponse log;
    private VerifyTransactionDataAuthorizationResponse authorization;
    private VerifyTransactionDataAuthorizationCustomerResponse customer;
    private String plan;
    private int requested_amount;

}
