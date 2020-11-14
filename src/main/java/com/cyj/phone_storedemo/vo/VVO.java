package com.cyj.phone_storedemo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//跟PhoneSpecs对应
public class VVO {
    @JsonProperty("id")
    private Integer specsId;
    @JsonProperty("name")
    private String specsName;
    @JsonProperty("imgUrl")
    private String specsIcon;
    @JsonProperty("previewImgUrl")
    private String specsPreview;
}
