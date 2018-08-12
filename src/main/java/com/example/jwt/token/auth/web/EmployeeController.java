package com.example.jwt.token.auth.web;

import com.example.jwt.token.auth.domain.Employee;
import com.example.jwt.token.auth.dto.EmployeeDto;
import com.example.jwt.token.auth.mapper.EmployeeMapper;
import com.example.jwt.token.auth.repository.EmployeeRepository;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

  @Autowired
  EmployeeRepository employeeRepository;

  private EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

  @RequestMapping(value = "/info", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('INFO')")
  public String info() {
    return "Welcome in Employee Manager!";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('CREATE')")
  public EmployeeDto create(@RequestBody @Valid EmployeeDto employeeDto,
      BindingResult bindingResult) {
    Employee employee = employeeMapper.toEmployee(employeeDto);
    employee = employeeRepository.save(employee);
    return employeeMapper.toEmployeeDto(employee);
  }

  @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('READ')")
  public EmployeeDto view(@PathVariable(value = "employeeId") String employeeId) {
    final Employee employee = employeeRepository.getOne(UUID.fromString(employeeId));
    return employeeMapper.toEmployeeDto(employee);
  }

  @RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('DELETE')")
  public void delete(@PathVariable(value = "employeeId") String employeeId) {
    final Employee employee = employeeRepository.getOne(UUID.fromString(employeeId));
    employeeRepository.delete(employee);
  }
}
