package services.gorest.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.Post;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_meta",
        "result"
})
@NoArgsConstructor
@Data
public class GetPostResponse extends GeneralResponse {

    @JsonProperty("result")
    private Post result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
