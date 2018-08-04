package com.example.jwt.token.auth.mapper;

import com.example.jwt.token.auth.domain.Employee;
import com.example.jwt.token.auth.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  EmployeeDto toEmployeeDto(Employee employee);

  Employee toEmployee(EmployeeDto employeeDto);
}
