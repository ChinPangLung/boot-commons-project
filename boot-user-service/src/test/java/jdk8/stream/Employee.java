package jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Employee
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2019-07-10 09:55
 */
@Data
@ToString
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long empno; //员工号
    private String ename; //员工姓名
    private Integer salary; //薪水
    private Integer deptno; //所属部门号
}