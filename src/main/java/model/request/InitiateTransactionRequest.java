package model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InitiateTransactionRequest {

    private String amount;
    private String email;
    private String currency;
    private String transaction_reference;
    private String callback_url;
    private String plan;
    private int invoice_limit;
    private InitiateTransactionMetadataRequest metadata;
}
