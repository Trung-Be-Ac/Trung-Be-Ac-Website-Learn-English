package com.example.PJWebTa.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PJWebTa.Model.Entity.Course;
import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Entity.User;
import com.example.PJWebTa.Model.Repository.CourseRepo;
import com.example.PJWebTa.Model.Repository.LessonRepo;
import com.example.PJWebTa.Model.Repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class CourseController {
    @Autowired
    CourseRepo courseRepo = new CourseRepo();
    LessonRepo lessonRepo = new LessonRepo();
    UserRepo userRepo = new UserRepo();

    @GetMapping("/Course")
    public String CourseMainPage(Model model) throws Exception {
        ArrayList<Course> allCourses = courseRepo.Viewallcourse();
        model.addAttribute("AllCourses", allCourses);
        return "Course/CourseMain";
    }

    // View Detail Course
    @GetMapping("/CourseDetail/{courseID}")
    public String ViewCourseDetail(@PathVariable("courseID") int courseID, Model model) throws Exception {
        Course course = courseRepo.getCoursebyID(courseID);
        ArrayList<Lesson> allLessons = lessonRepo.viewAllLessons();
        model.addAttribute("AllLessons", allLessons);
        model.addAttribute("CourseDetails", course);
        return "Course/CourseDetails";

    }

    // Create Course
    @GetMapping("/CreateCourse")
    public String pageCreateCourse(Model model) throws Exception {
        ArrayList <Course> courseList =courseRepo.Viewallcourse();
        model.addAttribute("CourseList", courseList);
        return "/Course/CreateCourse";
    }

    @PostMapping("/createCourse")
    public String CreateCourse(@RequestParam("courseName") String name, @RequestParam("courseLevel") int level,
            @RequestParam("coureDescription") String description, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("LoginSuccess");
        Course course = new Course(0, name, level, description, 0, 0, 0.0, user);
        courseRepo.AddCourse(course);
        return "redirect:/Course";

    }

    // Search Course
    @PostMapping("getCourseByName")
    public String SearchCourseByName(@RequestParam("searchNameCourse") String namecourse, Model model,
            HttpSession httpSession) throws Exception {
        ArrayList<Course> courseList = courseRepo.Viewallcourse();
        ArrayList<Course> findCourse = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getCourseName().toLowerCase().contains(namecourse.toLowerCase())) {
                findCourse.add(course);
            }
        }
        model.addAttribute("CourseList", findCourse);
        return "/Course/CourseMain";
    }

    //  Update Course
    @GetMapping("/EditCourse/{courseID}")
    public String EditCourse(Model model, @PathVariable("courseID") int courseID) throws Exception {
        Course course = courseRepo.getCoursebyID(courseID);
        model.addAttribute("Course", course);
        return "Course/CourseEdit";
    }

    @PostMapping("/editCourse")
    public String editCourse(@RequestParam("courseID") int courseID, @RequestParam("courseName") String courseName,
            @RequestParam("courseLevel") int courseLevel,
            @RequestParam("courseDescription") String courseDescription,
            @RequestParam("courseTotallesson") int courseTotallesson) throws Exception {
        Course course = new Course(courseID, courseName, courseLevel, courseDescription, courseTotallesson);
        courseRepo.updateCourse(course);
        return "redirect:/Course";

    }

}
