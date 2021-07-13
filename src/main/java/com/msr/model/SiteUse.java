package com.msr.model;

import lombok.Data;

import javax.persistence.Id;

@Data
public class SiteUse {
    @Id
    private int id;

    private int siteId;

    private String description;

    private long sizeSqft;

    private int useTypeId;

    private UseType useType;
}
