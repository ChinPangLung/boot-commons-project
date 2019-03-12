package com.lung.application.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Title: Article
 * @Author: lung
 * @Date: 19-3-12 下午1:37
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/

@Document(indexName = "projectname", type = "article", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article implements Serializable {

    private Long id;

    /**
     * 标题
     */
    private String title;

    private String abstracts;

    private String content;

    private String postTime;

    private String clickCount;

    private Author author;

    private Tutorial tutorial;

}
