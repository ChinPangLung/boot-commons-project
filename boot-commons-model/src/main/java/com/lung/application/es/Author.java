package com.lung.application.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Title: Author
 * @Author: lung
 * @Date: 19-3-12 下午1:41
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author implements Serializable {

    private Long id;

    private String name;

    private String remark;

}
