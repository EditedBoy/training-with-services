package com;

import com.persistence.CustomJDBC;
import com.util.enums.DataBaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class FirstMain {

    private CustomJDBC customJDBC;

    public FirstMain(CustomJDBC customJDBC) {
        this.customJDBC = customJDBC;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/first")
    public String home() {
        String databaseName = "customjdbcdb";
        customJDBC.openConnection();
        boolean dataBaseExist = customJDBC.isDataBaseExist(databaseName);
        if (!dataBaseExist) {
            customJDBC.createDataBase(databaseName);
        }
        customJDBC.closeConnection();

        return "Hello World!\nI'm First Micro-service ";
    }

    private Map<String, List<DataBaseProperties>> createFields() {
        Map<String, List<DataBaseProperties>> columns = new HashMap<>();

        List<DataBaseProperties> idProperties = new ArrayList<>();
        idProperties.add(DataBaseProperties.INT);
        idProperties.add(DataBaseProperties.NOT_NULL);
        idProperties.add(DataBaseProperties.AUTO_INCEMENT);
        idProperties.add(DataBaseProperties.PRIMARY_KEY);

        List<DataBaseProperties> nameProperties = new ArrayList<>();
        nameProperties.add(DataBaseProperties.TEXT);

        List<DataBaseProperties> availableProperties = new ArrayList<>();
        availableProperties.add(DataBaseProperties.BOOLEAN);

        columns.put("id", idProperties);
        columns.put("name", nameProperties);
        columns.put("available", availableProperties);

        return columns;
    }

    public static void main(String[] args) {
        SpringApplication.run(FirstMain.class, args);
    }
}
