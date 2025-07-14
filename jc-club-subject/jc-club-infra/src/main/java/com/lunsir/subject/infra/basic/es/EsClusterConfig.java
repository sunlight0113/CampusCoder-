package com.lunsir.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lunSir
 * @create 2024-10-12 17:24
 */
@Data
public class EsClusterConfig implements Serializable {

    /**
     * 集群名称
     */
    private String name;

    /**
     * 集群节点
     */
    private String nodes;

}

