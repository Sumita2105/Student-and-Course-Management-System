package com.institute.college.controller;

import com.institute.college.model.Person;
import com.institute.college.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Value("${eazyschool.pageSize}")
    private int defaultPageSize;

    @Value("${eazyschool.contact.successMsg}")
    private String message;

    @Autowired
    Environment environment;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model,Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(null != person.getEazyClass() && null != person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        logMessages();
        return "dashboard.html";
    }

    public void logMessages(){
        log.error("Error message from Dashboard page");
        log.warn("Warn message from Dashboard page");
        log.info("Info message from Dashboard page");
        log.debug("Debug message from Dashboard page");
        log.trace("Trace message from Dashboard page");
        log.error("Default Page size using @value annotation is "+defaultPageSize);
        log.error("Success message using @value annotation is "+message);
        log.error("Default Page size value with Environment is "+environment.getProperty("eazyschool.pageSize"));
        log.error("Success message value with Environment is "+environment.getProperty("eazyschool.contact.successMsg"));
        log.error("Java home environment variable using Environment is "+environment.getProperty("JAVA_HOME"));

    }

}
