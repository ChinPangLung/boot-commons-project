package com.lung.application.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lung.application.service.IndexerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: IndexerController
 * @Author: lung
 * @Date: 19-3-14 下午2:40
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@RestController
public class IndexerController {

    @Reference
    private IndexerService indexerService;

    @RequestMapping(value = "bulkIndex")
    public long bulkIndex() {
        long l = indexerService.bulkIndex();
        return l;
    }
}
