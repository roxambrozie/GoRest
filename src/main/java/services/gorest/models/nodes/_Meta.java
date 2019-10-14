package services.gorest.models.nodes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "code",
        "message",
        "rateLimit"
})
@NoArgsConstructor
@Data

public class _Meta {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("rateLimit")
    private RateLimit rateLimit;
    @JsonIgnoreProperties
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
