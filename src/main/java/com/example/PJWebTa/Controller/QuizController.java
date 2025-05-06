package com.example.PJWebTa.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.PJWebTa.Model.Entity.QuizTest;
import com.example.PJWebTa.Model.Entity.Test;
import com.example.PJWebTa.Model.Repository.QuizRepo;
import com.example.PJWebTa.Model.Repository.TestRepo;

@Controller
public class QuizController {

    @Autowired
    QuizRepo quizRepo = new QuizRepo();

    @Autowired
    TestRepo testRepo = new TestRepo();

    //  View all quizzes
    @GetMapping("/Quizzes")
    public String viewAllQuizzes(Model model) throws Exception {
        ArrayList<QuizTest> allQuizzes = quizRepo.getAllQuiz();
        model.addAttribute("AllQuizzes", allQuizzes);
        return "Quiz/AllQuiz";
    }

    //  View quiz detail
    @GetMapping("/QuizDetail/{quizID}")
    public String viewQuizDetail(@PathVariable("quizID") int quizID, Model model) throws Exception {
        QuizTest quiz = quizRepo.getQuizByID(quizID);
        model.addAttribute("QuizDetail", quiz);
        return "Quiz/QuizDetail";
    }

    //  Page create quiz
    @GetMapping("/CreateQuiz")
    public String pageCreateQuiz(Model model) throws Exception {
        model.addAttribute("AllTests", testRepo.getAllTests());
        return "Quiz/CreateQuiz";
    }

    //  create quiz
    @PostMapping("/createQuiz")
    public String createQuiz(
            @RequestParam("quizTitle") String title,
            @RequestParam("quizText") String text,
            @RequestParam("quizA") String quizA,
            @RequestParam("quizB") String quizB,
            @RequestParam("quizC") String quizC,
            @RequestParam("quizD") String quizD,
            @RequestParam("quizCorrectAnswer") String correctAnswer,
            @RequestParam("testID") int testID) throws Exception {

        Test test = testRepo.getTestByID(testID);
        QuizTest quiz = new QuizTest(0, title, text, quizA, quizB, quizC, quizD, correctAnswer, test);
        QuizRepo.addQuiz(quiz);
        return "redirect:/AllQuizzes";
    }

    // Page edit quiz
    @GetMapping("/EditQuiz/{quizID}")
    public String editQuizPage(@PathVariable("quizID") int quizID, Model model) throws Exception {
        QuizTest quiz = quizRepo.getQuizByID(quizID);
        model.addAttribute("Quiz", quiz);
        model.addAttribute("AllTests", testRepo.getAllTests());
        return "Quiz/EditQuiz";
    }

    // edit quiz
    @PostMapping("/editQuiz")
    public String editQuiz(
            @RequestParam("quizID") int quizID,
            @RequestParam("quizTitle") String title,
            @RequestParam("quizText") String text,
            @RequestParam("quizA") String quizA,
            @RequestParam("quizB") String quizB,
            @RequestParam("quizC") String quizC,
            @RequestParam("quizD") String quizD,
            @RequestParam("quizCorrectAnswer") String correctAnswer,
            @RequestParam("testID") int testID) throws Exception {

        Test test = testRepo.getTestByID(testID);
        QuizTest quiz = new QuizTest(quizID, title, text, quizA, quizB, quizC, quizD, correctAnswer, test);
        QuizRepo.updateQuiz(quiz);
        return "redirect:/QuizDetail/" + quizID;
    }

    // Delete quiz
    @GetMapping("/DeleteQuiz/{quizID}")
    public String deleteQuiz(@PathVariable("quizID") int quizID) throws Exception {
        QuizRepo.deleteQuizByID(quizID);
        return "redirect:/AllQuizzes";
    }
}
