package services.gorest.models.responses;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.User;
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
public class GetUserResponse {

    @JsonProperty
    private _Meta _meta;
    @JsonIgnore
    @JsonProperty("result")
    private User result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
