package game.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Questions {


    private static List<Question> questions;
    private static int numOfQuestions;

    private static String source;
    private static String[] splitString;

    public static void readFile(String filePath){
        // get source string
        try {
            source = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            System.out.println("Could Not Find File'"+filePath+"'");
        }

        // split the string into an array of strings
        splitString = splitString(source,',');

        // create a list of questions from the array af strings
        questions = createQuestionsArray(splitString);
        numOfQuestions = questions.size();
    }

    private static String[] splitString(String s, char delimeter){
        // get number of substrings
        int numOfStrings = 0;
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(currChar==delimeter||currChar=='\n'){
                numOfStrings++;
            }
        }

        //create substring array
        String[] splitStrings = new String[numOfStrings];

        // fill in each substring
        String currString = "";
        int currStringNum = 0;
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(currChar==delimeter||currChar=='\n'){
                splitStrings[currStringNum] = currString;
                currString = "";
                currStringNum++;
            }else{
                currString += currChar;
            }
        }

        // return final array
        return splitStrings;
    }

    private static List<Question> createQuestionsArray(String[] strings){

        List<Question> Questions = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {

            // File Format

            // NumberOfAnswers      Question    Answers     CorrectAnswer
            // int                  String      String[]    int

            // get the numberOfAnswers
            int numberOfAnswers = Integer.parseInt(strings[i]);     
            i++;

            // get the question
            String Question = strings[i];                           
            i++;

            // get the array of answers
            String[] answers = new String[numberOfAnswers];         
            for (int j = 0; j<answers.length; j++) {
                answers[j] = strings[i];                                
                i++;
            }

            // get the correct answer
            int correctAnswer = 0;

            Questions.add(new Question(Question, answers, correctAnswer));
        }
        return Questions;
    }

    public static Question getQuestion(int questionNumber){
        // check if array is empty
        if(questions.isEmpty()){
            System.out.println("The List Of Questions, was empty");
            return new Question();
        }

        // check if out of bounds
        if(questionNumber<0||questionNumber>=numOfQuestions){
            System.out.println("trying to index Question Array out of bounds");
            return new Question();
        }

        // return question
        return questions.get(questionNumber);
    }

    public static void printQuestions(){
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
