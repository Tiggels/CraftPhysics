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
        "parent",
        "ambientocclusion",
        "textures",
        "elements"
})
public class Model {

    @JsonProperty("parent")
    private String parent;
    @JsonProperty("ambientocclusion")
    private Boolean ambientocclusion;
    @JsonProperty("textures")
    private Textures textures;
    @JsonProperty("elements")
    private List<Element> elements = new ArrayList<Element>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Model() {
    }

    /**
     *
     * @param textures
     * @param parent
     * @param elements
     * @param ambientocclusion
     */
    public Model(String parent, Boolean ambientocclusion, Textures textures, List<Element> elements) {
        this.parent = parent;
        this.ambientocclusion = ambientocclusion;
        this.textures = textures;
        this.elements = elements;
    }

    /**
     *
     * @return
     * The parent
     */
    @JsonProperty("parent")
    public String getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     * The parent
     */
    @JsonProperty("parent")
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     * The ambientocclusion
     */
    @JsonProperty("ambientocclusion")
    public Boolean getAmbientocclusion() {
        return ambientocclusion;
    }

    /**
     *
     * @param ambientocclusion
     * The ambientocclusion
     */
    @JsonProperty("ambientocclusion")
    public void setAmbientocclusion(Boolean ambientocclusion) {
        this.ambientocclusion = ambientocclusion;
    }

    /**
     *
     * @return
     * The textures
     */
    @JsonProperty("textures")
    public Textures getTextures() {
        return textures;
    }

    /**
     *
     * @param textures
     * The textures
     */
    @JsonProperty("textures")
    public void setTextures(Textures textures) {
        this.textures = textures;
    }

    /**
     *
     * @return
     * The elements
     */
    @JsonProperty("elements")
    public List<Element> getElements() {
        return elements;
    }

    /**
     *
     * @param elements
     * The elements
     */
    @JsonProperty("elements")
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(parent).append(ambientocclusion).append(textures).append(elements).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Model) == false) {
            return false;
        }
        Model rhs = ((Model) other);
        return new EqualsBuilder().append(parent, rhs.parent).append(ambientocclusion, rhs.ambientocclusion).append(textures, rhs.textures).append(elements, rhs.elements).isEquals();
    }

}