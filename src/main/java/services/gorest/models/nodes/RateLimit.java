package services.gorest.models.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "limit",
        "remaining",
        "reset"
})
@NoArgsConstructor
@Data
public class RateLimit {
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("remaining")
    private Integer remaining;
    @JsonProperty("reset")
    private Integer reset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
