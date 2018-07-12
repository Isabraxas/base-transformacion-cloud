package com.javainuse.services;

import com.javainuse.controllers.Employee;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "employee-producer")
public interface RemoteCallService {
    @RequestMapping(method = RequestMethod.GET, value = "/employee")
    public Employee getData();
}
