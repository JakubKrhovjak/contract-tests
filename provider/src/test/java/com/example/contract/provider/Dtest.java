package com.example.contract.provider;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */


@Data
@AllArgsConstructor
class Employee {
  private String name;
  private List<EmployeeData> data;


}

@Data
@AllArgsConstructor
class EmployeeData {

    private int id;
    private int salary;

}
public class Dtest {

    @Test
    void name() {
//        Employee prvni = new Employee("prvni", List.of(new EmployeeData(1, 10000)));
//        Employee druhy = new Employee("druhy", List.of(new EmployeeData(1, 10001)));
//        Employee treti = new Employee("treti", List.of(new EmployeeData(1, 19999), new EmployeeData(1, 9999)));
//
//        var employees = List.of(prvni, druhy, treti);
//       var collect = employees.stream()
//               .filter(e -> e.getData().stream().anyMatch(d -> d.getSalary() > 10000))
//               .map(c -> c.getName())
//               .toList();
//
//        var sorted = collect.sort(Comparator.reverseOrder());
    }
}
