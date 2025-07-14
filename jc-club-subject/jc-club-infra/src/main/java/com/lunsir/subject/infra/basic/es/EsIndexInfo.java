package com.lunsir.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lunSir
 * @create 2024-10-12 17:26
 */
@Data
public class EsIndexInfo implements Serializable {

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 索引名称(mapping)
     */
    private String indexName;

}

