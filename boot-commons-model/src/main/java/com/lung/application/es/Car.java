package com.lung.application.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Title: Car
 * @Author: lung
 * @Date: 19-3-14 下午1:43
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Data
@Document(indexName = "carIndex", type = "carType", shards = 1, replicas = 0)
public class Car implements Serializable {
    @Id
    private Long id;

    private String brand;

    private String model;

    private BigDecimal amount;

    public Car() {
    }

    public Car(Long id, String brand, String model, BigDecimal amount) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.amount = amount;
    }
}
