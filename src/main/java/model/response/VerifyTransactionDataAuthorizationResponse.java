package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyTransactionDataAuthorizationResponse {

    private String authorization_code;
    private String card_type;
    private String last4;
    private String exp_month;
    private String exp_year;
    private String bin;
    private String bank;
    private String channel;
    private String signature;
    private boolean reusable;
    private String country_code;
    private String account_name;

}
