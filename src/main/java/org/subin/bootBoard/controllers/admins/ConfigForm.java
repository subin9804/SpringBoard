package org.subin.bootBoard.controllers.admins;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ConfigForm {
    private String siteTitle="";
    private String siteDescription="";
    private String cssJsVersion ="" + 1;
    private String joinTerms="";

}
