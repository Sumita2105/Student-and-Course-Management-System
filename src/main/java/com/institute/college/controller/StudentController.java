package com.institute.college.controller;


import com.institute.college.model.Person;
import com.institute.college.repository.CoursesRepository;
import com.institute.college.repository.InstituteClassRepository;
import com.institute.college.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    InstituteClassRepository instituteClassRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;


    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession session)
    {
        Person person = (Person) session.getAttribute("loggedInPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", person);
        return  modelAndView;

    }
}
