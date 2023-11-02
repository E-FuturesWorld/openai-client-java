package com.efutures.dto.response.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class Model {

    public String id;
    public String object;
    @JsonProperty("owned_by")
    public String ownedBy;
    public List<Permission> permission;
    public String root;
    public String parent;
}
