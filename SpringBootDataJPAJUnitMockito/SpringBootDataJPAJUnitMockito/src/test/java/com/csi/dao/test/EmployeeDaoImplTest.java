package com.csi.dao.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    @MockBean
    EmployeeRepo employeeRepoImpl;

    @Test
    public void saveDataTest(){
        Date dob = null;
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-MM-yyyy");

        try {
            dob= simpleDateFormat.parse("09-09-2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Employee employee = new Employee(121, "SWARA", 89898.89, dob );

        employeeDaoImpl.saveData(employee);

        verify(employeeRepoImpl, times(1)).save(employee);


    }

    @Test
    public void getAllDataTest(){

        Date dob = null;
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-MM-yyyy");

        try {
            dob= simpleDateFormat.parse("09-09-2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        when(employeeRepoImpl.findAll()).thenReturn(Stream.of(new Employee(121, "SWARA", 89898.89, dob ), new Employee(122, "MAYUR", 122121, dob )).collect(Collectors.toList()));

        assertEquals(2, employeeDaoImpl.getAllData().size());

    }

    @Test
    public void updateDataTest(){
        Date dob = null;
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-MM-yyyy");

        try {
            dob= simpleDateFormat.parse("09-09-2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Employee employee = new Employee(121, "SWARA", 89898.89, dob );

        employeeDaoImpl.updateData(employee);

        verify(employeeRepoImpl, times(1)).save(employee);
    }

    @Test
    public void deleteDataByIdTest(){
        Date dob = null;
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-MM-yyyy");

        try {
            dob= simpleDateFormat.parse("09-09-2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Employee employee = new Employee(121, "SWARA", 89898.89, dob );

        employeeDaoImpl.deleteDataById(employee.getEmpId());

        verify(employeeRepoImpl, times(1)).deleteById(employee.getEmpId());
    }


}
