/*
package com.lung.application.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.lung.application.es.Car;
import com.lung.application.service.IndexerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @Title: IndexerService
 * @Author: lung
 * @Date: 19-3-14 下午1:47
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **//*

@Service //dubbo的
@Component
public class IndexerServiceImpl implements IndexerService {

    private static final String CAR_INDEX_NAME = "car_index";

    private static final String CAR_INDEX_TYPE = "car_type";

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public long bulkIndex() {
        try {
        new Thread(() -> {
            int counter = 0;
            if (!elasticsearchTemplate.indexExists(CAR_INDEX_NAME)) {
                elasticsearchTemplate.createIndex(CAR_INDEX_NAME);
            }

            Gson gson = new Gson();
            List<IndexQuery> queries = new ArrayList<>();
            List<Car> cars = assembleTestData();

            for (Car car : cars) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(car.getId().toString());
                indexQuery.setSource(gson.toJson(car));
                indexQuery.setIndexName(CAR_INDEX_NAME);
                indexQuery.setType(CAR_INDEX_TYPE);
                queries.add(indexQuery);
                */
/**
                 * 分批提交索引
                 *//*

                if (counter % 500 == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    System.out.println("bulkIndex counter :" + counter);
                }
                counter++;
            }
            if (queries.size() > 0) {
                elasticsearchTemplate.bulkIndex(queries);
            }
            elasticsearchTemplate.refresh(CAR_INDEX_NAME);
            System.out.println("BulkIndex completed");
        }).start();

        } catch (Exception e) {
            System.out.println("IndexerService bulkIndex e :" + e.getMessage());
            throw e;
        }
        return -1;
    }

    private List<Car> assembleTestData() {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            cars.add(new Car(RandomUtils.nextLong(1, 211111), RandomStringUtils.randomAscii(20),
                    RandomStringUtils.randomAlphabetic(15), BigDecimal.valueOf(78000)));
        }
        return cars;
    }

}
*/
