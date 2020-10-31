package com.tool.model.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JsoupHttpDTO {

    @NotBlank(message = "merId不能为空")
    private String merId;

    @NotBlank(message = "cardNo不能为空")
    private String cardNo;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotBlank(message = "mobile不能为空")
    private String mobile;

    @NotBlank(message = "idCard不能为空")
    private String idCard;

}
