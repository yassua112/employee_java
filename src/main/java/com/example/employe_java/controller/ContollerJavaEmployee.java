package com.example.employe_java.controller;

import java.util.*;

import com.example.employe_java.model.ModelEmploye;
import com.example.employe_java.service.GetEmployeeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ContollerJavaEmployee {

    @Autowired
    GetEmployeeService getEmployeeService;


    @GetMapping("/getEmployee")
    @ResponseBody
    public ResponseEntity<List<ModelEmploye>> getAllemployee(@RequestParam(required = false) String name) {
        try {
            List<ModelEmploye> employee = new ArrayList<ModelEmploye>();

            if (name == null)
                getEmployeeService.findAll().forEach(employee::add);
            else
                getEmployeeService.findByUsername(name).forEach(employee::add);

            if (employee.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
        	e.getStackTrace();
        	System.out.print(e.getMessage());
        	String msg = e.getMessage();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    @PostMapping("/addNewEmployee")
    public ResponseEntity<Map<String,String>> addNewEmployee(@RequestBody String param) {
        ModelEmploye empl = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        Map<String,String> response = new HashMap<>();
        try {
            System.out.print(param);
            jsonNode = objectMapper.readTree(param);
            empl = objectMapper.treeToValue(jsonNode, ModelEmploye.class);
            ModelEmploye employee = getEmployeeService
                    .save(new ModelEmploye(empl.getUsername(),empl.getFirstName(),empl.getLastName(),empl.getEmail(),empl.getBirdDate(),empl.getBasicSallary(),empl.getStatus(),empl.getGroup(),empl.getDescription()));
            response.put("status","200");
            response.put("mssg","Data complate inserted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.getStackTrace();
            System.out.print(e.getMessage());
            String msg = e.getMessage();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getbyId/{id}")
    public ResponseEntity<ModelEmploye> getEmployeeById(@PathVariable("id") long id) {
        Optional<ModelEmploye> employeData = getEmployeeService.findById(id);
        if (employeData.isPresent()) {
            return new ResponseEntity<>(employeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeID(@PathVariable("id") long id) {
        try {
            getEmployeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/editemployee/{id}")
    public ResponseEntity<HttpStatus> updateEmployee(@PathVariable("id") long id,@RequestBody ModelEmploye param) {
        Optional<ModelEmploye> modelEmployee = getEmployeeService.findById(id);
        ModelEmploye empl = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            System.out.print(param);
//            jsonNode = objectMapper.readTree(param);
//            empl = objectMapper.treeToValue(jsonNode, ModelEmploye.class);
           if(modelEmployee.isPresent()) {
               ModelEmploye mdls = modelEmployee.get();
               mdls.setUsername(param.getUsername());
               mdls.setFirstName(param.getUsername());
               mdls.setFirstName(param.getFirstName());
               mdls.setLastName(param.getLastName());
               mdls.setEmail(param.getEmail());
               mdls.setBirdDate(param.getBirdDate());
               mdls.setStatus(param.getStatus());
               mdls.setGroup(param.getGroup());
               mdls.setDescription(param.getDescription());
               getEmployeeService.save(mdls);
               return new ResponseEntity<>(HttpStatus.OK);
           }else{
               return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
