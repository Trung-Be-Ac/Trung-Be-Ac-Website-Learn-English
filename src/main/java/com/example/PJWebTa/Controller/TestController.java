package com.example.PJWebTa.Controller;

import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Entity.Test;
import com.example.PJWebTa.Model.Repository.LessonRepo;
import com.example.PJWebTa.Model.Repository.TestRepo;

@Controller
public class TestController {

    @Autowired
    private TestRepo testRepo;

    @Autowired
    private LessonRepo lessonRepo;

    // View Test Detail
    @GetMapping("/TestDetail/{testID}")
    public String viewTestDetail(@PathVariable("testID") int testID, Model model) throws Exception {
        Test test = testRepo.getTestByID(testID);
        model.addAttribute("TestDetail", test);
        return "Testt/TestDetail"; 
    }

    // View All Tests
    @GetMapping("/AllTest")
    public String viewAllTests(Model model) throws Exception {
        ArrayList<Test> allTests = testRepo.getAllTests();
        model.addAttribute("AllTests", allTests);
        return "Testt/TestMain"; 
    }

    // Create Test
    @GetMapping("/CreateTest")
    public String pageCreateTest(Model model) throws Exception {
        ArrayList<Lesson> allLessons = lessonRepo.viewAllLessons();
        model.addAttribute("AllLessons", allLessons);
        return "Testt/CreateTest";  
    }

    @PostMapping("/createTest")
    public String createTest(
            @RequestParam("testName") String testName,
            @RequestParam("lessonID") int lessonID,
            @RequestParam("testQuestionType") String testQuestionType,
            @RequestParam("testTime") LocalTime testTime,
            Model model) throws Exception {
                // LocalTime testTime

        Lesson lesson = lessonRepo.getLessonbyID(lessonID);
        Test test = new Test(0, testName, lesson, testQuestionType, testTime);
        TestRepo.AddTest(test);

        return "redirect:/AllTest";
    }

    // Edit Test
    @GetMapping("/EditTest/{testID}")
    public String pageEditTest(@PathVariable("testID") int testID, Model model) throws Exception {
        Test test = testRepo.getTestByID(testID);
        ArrayList<Lesson> allLessons = lessonRepo.viewAllLessons();
        model.addAttribute("Test", test);
        model.addAttribute("AllLessons", allLessons);
        return "Testt/TestEdit"; 
    }
    @PostMapping("/editTest")
    public String editTest(
            @RequestParam("testID") int testID,
            @RequestParam("testName") String testName,
            @RequestParam("lessonID") int lessonID,
            @RequestParam("testQuestionType") String testQuestionType,
            @RequestParam("testTime") LocalTime testTime) throws Exception {

        Lesson lesson = lessonRepo.getLessonbyID(lessonID);
        TestRepo.UpdateTest(testName, lesson, testQuestionType, testTime, testID);

        return "redirect:/TestDetail/" + testID;
    }

    // Delete Test
    @GetMapping("/DeleteTest/{testID}")
    public String deleteTest(@PathVariable("testID") int testID) throws Exception {
        TestRepo.DeleteTestByID(testID);
        return "redirect:/AllTests";
    }
}
