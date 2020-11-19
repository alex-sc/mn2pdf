package com.metanorma.fop.fonts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexander Dyuzhev
 */
@JacksonXmlRootElement(localName = "font")
public class FOPFont {
    
    @JacksonXmlProperty(isAttribute=true)
    String kerning = "yes";
    
    @JacksonXmlProperty(isAttribute=true, localName = "embed-url")
    String embed_url;
    
    @JacksonXmlProperty(isAttribute=true, localName = "sub-font")
    String sub_font;
    
    @JacksonXmlProperty(isAttribute=true, localName = "simulate-style")
    String simulate_style;
            
    @JacksonXmlElementWrapper(useWrapping = false)
    List<FOPFontAlternate> alternate = new ArrayList<>();
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "font-triplet")
    List<FOPFontTriplet> font_triplet = new ArrayList<>();
    
    @JsonIgnore
    boolean isUsing = false;
    
    public FOPFont () {
        //test
        /*embed_url = "C:/font.ttf";
        sub_font = "Cambria Math";
        simulate_style = "true";*/
    }

    public String getKerning() {
        return kerning;
    }

    public void setKerning(String kerning) {
        this.kerning = kerning;
    }

    public String getEmbed_url() {
        return embed_url;
    }

    public void setEmbed_url(String embed_url) {
        this.embed_url = embed_url;
    }
    
    public String getSub_font() {
        return sub_font;
    }
    
    public void setSub_font(String sub_font) {
        this.sub_font = sub_font;
    }

    public String getSimulate_style() {
        return simulate_style;
    }
    
    public void setSimulate_style(String simulate_style) {
        this.simulate_style = simulate_style;
    }

    public List<FOPFontAlternate> getAlternate() {
        return alternate;
    }

    public void setAlternate(ArrayList<FOPFontAlternate> alternate) {
        this.alternate = alternate;
    }

    public List<FOPFontTriplet> getFont_triplet() {
        return font_triplet;
    }

    public void setFont_triplet(List<FOPFontTriplet> font_triplet) {
        this.font_triplet = font_triplet;
    }

    @JsonIgnore
    public boolean isUsing() {
        return isUsing;
    }

    @JsonIgnore
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
    }

    
}
