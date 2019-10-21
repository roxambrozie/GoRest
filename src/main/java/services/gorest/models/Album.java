package services.gorest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.nodes.Links;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "user_id",
        "title",
        "_links"
})
@NoArgsConstructor
@Data
public class Album {
    @JsonProperty("id")
    private String id;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("_links")
    private Links links;

    public Album(int userId, String title) {
        this.userId = userId;
        this.title = title;
    }
}
