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
        "album_id",
        "title",
        "url",
        "thumbnail",
        "_links"
})
@NoArgsConstructor
@Data
public class Photo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("album_id")
    private String albumId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("_links")
    private Links links;

    public Photo(String albumId, String title, String url, String thumbnail) {
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnail = thumbnail;
    }
}
