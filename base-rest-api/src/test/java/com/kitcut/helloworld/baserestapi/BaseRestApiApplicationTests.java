package com.kitcut.helloworld.baserestapi;

import com.kitcut.helloworld.baserestapi.dto.response.employee.EmployeeCreateResponse;
import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;
import com.kitcut.helloworld.baserestapi.util.MappingUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseRestApiApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMappingObject(){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        List<EmployeeEntity> listOrigin = new ArrayList<>();
        listOrigin.add(employeeEntity);
        List<EmployeeCreateResponse> listDest = MappingUtils.mappingListObject(listOrigin, EmployeeCreateResponse.class);
        int i = 1;
    }

}
