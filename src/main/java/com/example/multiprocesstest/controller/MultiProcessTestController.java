package com.example.multiprocesstest.controller;

import com.example.multiprocesstest.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiProcessTestController {

    @Autowired
    AddService addService;

    @RequestMapping("/add")
    @ResponseBody
    public String add(Integer integer) {
        boolean add = addService.add(integer);
        if (add) {
            return "add成功过";
        } else {
            return "add失败";
        }
    }

    @RequestMapping("/print")
    public String print() {
        addService.printI();
        return "打印成功";
    }

}
