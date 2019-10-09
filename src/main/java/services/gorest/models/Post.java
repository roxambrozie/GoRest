package services.gorest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.nodes.Links;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "user_id",
        "title",
        "body",
        "_links"
})
@NoArgsConstructor
@Data
public class Post {
    @JsonProperty("id")
    private String id;
    @JsonProperty("user_id")
    private int user_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;
    @JsonProperty("_links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Post(int user_id, String title, String body) {
        this.user_id = user_id;
        this.title = title;
        this.body = body;
    }
}
