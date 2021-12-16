package com.example.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelloService {
    @Autowired
    private HelloRepository helloRepository;

    public EmployeeDto getEmployee(String id) {
        //search
        Map<String, Object> map = helloRepository.findById(id);

        //get value from the map
        String eId = (String) map.get("id");
        String eName = (String) map.get("name");
        Integer eAge = (Integer) map.get("age");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(eId);
        employeeDto.setName(eName);
        employeeDto.setAge(eAge);
        return employeeDto;
    }
}
