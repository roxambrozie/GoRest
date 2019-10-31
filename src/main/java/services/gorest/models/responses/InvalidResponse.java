package services.gorest.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.InvalidResult;
import services.gorest.models.nodes._Meta;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_meta",
        "result"
})
@NoArgsConstructor
@Data
public class InvalidResponse {

    @JsonProperty
    private _Meta _meta;
    @JsonProperty("result")
    private InvalidResult result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
