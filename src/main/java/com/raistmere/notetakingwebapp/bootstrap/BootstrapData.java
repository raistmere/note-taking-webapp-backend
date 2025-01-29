//package com.raistmere.notetakingwebapp.bootstrap;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.jdbc.datasource.init.ScriptUtils;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Component
//public class BootstrapData implements CommandLineRunner {
//
//    private DataSource dataSource;
//
//    public BootstrapData(DataSource dataSource) {
//
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        createDatabase();
//        createData();
//    }
//
//    private void createDatabase() {
//
//        Resource sqlScript = new ClassPathResource("schema.sql");
//
//        try {
//
//            ScriptUtils.executeSqlScript(dataSource.getConnection(), sqlScript);
//        } catch (SQLException e) {
//
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void createData() {
//
//        Resource sqlScript = new ClassPathResource("data.sql");
//
//        try {
//
//            ScriptUtils.executeSqlScript(dataSource.getConnection(), sqlScript);
//        } catch (SQLException e) {
//
//            throw new RuntimeException(e);
//        }
//    }
//}
