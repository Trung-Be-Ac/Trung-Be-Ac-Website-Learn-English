package com.example.PJWebTa.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

public class LessonController {
    @Autowired
    LessonRepo lessonRepo = new LessonRepo();

    @Autowired
    CourseRepo courseRepo = new CourseRepo();

    @Autowired
    UserRepo userRepo = new UserRepo();

    // View Lesson Details
    @GetMapping("/LessonDetail/{lessonID}")
    public String viewLessonDetail(@PathVariable("lessonID") int lessonID, Model model) throws Exception {
        Lesson lesson = lessonRepo.getLessonbyID(lessonID);
        model.addAttribute("LessonDetail", lesson);
        return "Lesson/LessonDetail";
    }

    // Create Lesson
    @GetMapping("/CreateLesson")
    public String pageCreateLesson(Model model) throws Exception {
        return "Lesson/CreateLesson";
    }

    @PostMapping("/createLesson")
    public String createLesson(@RequestParam("courseID") int courseID,
            @RequestParam("lessonDescription") String lessonDescription, @RequestParam("lessonName") String lessonName,
            HttpSession session) throws Exception {
        User user = (User) session.getAttribute("LoginSuccess");
        Course course = courseRepo.getCoursebyID(courseID);
        Lesson lesson = new Lesson(0, course, user, lessonDescription, lessonName);
        lessonRepo.addLesson(lesson);
        return "redirect:/CourseDetail/{courseID}";
    }

    // Edit Lesson
    @GetMapping("/EditLesson/{lessonID}")
    public String editLessonPage(@PathVariable("lessonID") int lessonID, Model model) throws Exception {
        Lesson lesson = lessonRepo.getLessonbyID(lessonID);
        ArrayList<Course> allCourses = courseRepo.Viewallcourse();
        model.addAttribute("Lesson", lesson);
        model.addAttribute("AllCourses", allCourses);
        return "Lesson/LessonEdit";
    }

    @PostMapping("/editLesson")
    public String editLesson(@RequestParam("lessonID") int lessonID,
            @RequestParam("courseID") int courseID,
            @RequestParam("lessonName") String lessonName,
            @RequestParam("lessonDescription") String lessonDescription) throws Exception {
        Course course = courseRepo.getCoursebyID(courseID);
        lessonRepo.updateLesson(lessonID, course, lessonDescription, lessonName);
        return "redirect:/LessonDetail/{lessonID}";
    }
}