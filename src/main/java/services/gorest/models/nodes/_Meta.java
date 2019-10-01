package services.gorest.models.nodes;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "code",
        "message",
        "rateLimit"
})
public class _Meta {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("rateLimit")
    private RateLimit rateLimit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public _Meta() {
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(int code) {
        this.code = code;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("rateLimit")
    public RateLimit getRateLimit() {
        return rateLimit;
    }

    @JsonProperty("rateLimit")
    public void setRateLimit(RateLimit rateLimit) {
        this.rateLimit = rateLimit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(success).append(code).append(message).append(rateLimit).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof _Meta) == false) {
            return false;
        }
        _Meta rhs = ((_Meta) other);
        return new EqualsBuilder().append(success, rhs.success).append(code, rhs.code).append(message, rhs.message).append(rateLimit, rhs.rateLimit).append(additionalProperties, rhs.additionalProperties).isEquals();
    }
}
