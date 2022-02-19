package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyTransactionDataLogResponse {

    private int time_spent;
    private int attempts;
    private boolean success;
    private boolean mobile;
    private List<VerifyTransactionDataLogHistoryResponse> history;
}
