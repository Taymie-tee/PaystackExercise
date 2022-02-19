package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchTransactionDataResponse {

    private int id;
    private String domain;
    private String status;
    private int amount;
    private String currency;
    private String transaction_date;
    private String reference;
    private String gateway_response;
    private String created_at;
    private String paid_at;
    private String channel;
    private String ip_address;
//    private FetchTransactionDataMetadataResponse metadata;
    private FetchTransactionDataLogResponse log;
    private int fees;
    private FetchTransactionDataAuthorizationResponse authorization;
    private FetchTransactionDataCustomerResponse customer;
    private String order_id;
    private String paidAt;
    private String createdAt;
    private int requested_amount;
}
