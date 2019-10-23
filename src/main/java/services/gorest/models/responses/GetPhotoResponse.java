package services.gorest.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.Photo;
import services.gorest.models.nodes._Meta;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_meta",
        "result"
})
@NoArgsConstructor
@Data
public class GetPhotoResponse {
    @JsonProperty("_meta")
    private _Meta meta;
    @JsonProperty("result")
    private Photo result;
}
