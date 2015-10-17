package com.github.tiggels.craftphysics.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "uv",
        "texture",
        "cullface"
})
public class Down {

    @JsonProperty("uv")
    private List<Integer> uv = new ArrayList<Integer>();
    @JsonProperty("texture")
    private String texture;
    @JsonProperty("cullface")
    private String cullface;

    /**
     * No args constructor for use in serialization
     *
     */
    public Down() {
    }

    /**
     *
     * @param texture
     * @param uv
     * @param cullface
     */
    public Down(List<Integer> uv, String texture, String cullface) {
        this.uv = uv;
        this.texture = texture;
        this.cullface = cullface;
    }

    /**
     *
     * @return
     * The uv
     */
    @JsonProperty("uv")
    public List<Integer> getUv() {
        return uv;
    }

    /**
     *
     * @param uv
     * The uv
     */
    @JsonProperty("uv")
    public void setUv(List<Integer> uv) {
        this.uv = uv;
    }

    /**
     *
     * @return
     * The texture
     */
    @JsonProperty("texture")
    public String getTexture() {
        return texture;
    }

    /**
     *
     * @param texture
     * The texture
     */
    @JsonProperty("texture")
    public void setTexture(String texture) {
        this.texture = texture;
    }

    /**
     *
     * @return
     * The cullface
     */
    @JsonProperty("cullface")
    public String getCullface() {
        return cullface;
    }

    /**
     *
     * @param cullface
     * The cullface
     */
    @JsonProperty("cullface")
    public void setCullface(String cullface) {
        this.cullface = cullface;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(uv).append(texture).append(cullface).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Down) == false) {
            return false;
        }
        Down rhs = ((Down) other);
        return new EqualsBuilder().append(uv, rhs.uv).append(texture, rhs.texture).append(cullface, rhs.cullface).isEquals();
    }

}