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
        "tintindex"
})
public class East {

    @JsonProperty("uv")
    private List<Integer> uv = new ArrayList<Integer>();
    @JsonProperty("texture")
    private String texture;
    @JsonProperty("tintindex")
    private Integer tintindex;

    /**
     * No args constructor for use in serialization
     *
     */
    public East() {
    }

    /**
     *
     * @param texture
     * @param uv
     * @param tintindex
     */
    public East(List<Integer> uv, String texture, Integer tintindex) {
        this.uv = uv;
        this.texture = texture;
        this.tintindex = tintindex;
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
     * The tintindex
     */
    @JsonProperty("tintindex")
    public Integer getTintindex() {
        return tintindex;
    }

    /**
     *
     * @param tintindex
     * The tintindex
     */
    @JsonProperty("tintindex")
    public void setTintindex(Integer tintindex) {
        this.tintindex = tintindex;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(uv).append(texture).append(tintindex).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof East) == false) {
            return false;
        }
        East rhs = ((East) other);
        return new EqualsBuilder().append(uv, rhs.uv).append(texture, rhs.texture).append(tintindex, rhs.tintindex).isEquals();
    }

}