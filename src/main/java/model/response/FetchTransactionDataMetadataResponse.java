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
public class FetchTransactionDataMetadataResponse {

    private List<FetchTransactionDataMetadataCustomFieldsResponse> custom_fields;
    private String referrer;
}
