/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.webservice;

import com.project.Dao.HuaweiDao;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author suat.erdogan
 */
@RestController
@RequestMapping("/rest")
public class SpringRestController {
    
     @Autowired
     private HuaweiDao huaweiDao; 
    
    @GetMapping()
    public List<Object> list() {
        return null;
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/project")
    @GetMapping("{id}/{password}")
    public Boolean get(@PathVariable String id,@PathVariable String password) {
        Boolean isUser=false;
        huaweiDao = new HuaweiDao();
        isUser = huaweiDao.checkLogin(id,password);
        return isUser;
    }
    
//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
//        return null;
//    }
//    
//    @PostMapping
//    public ResponseEntity<?> post(@RequestBody Object input) {
//        return null;
//    }
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }
//    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
