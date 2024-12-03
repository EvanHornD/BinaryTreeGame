package AbandonedCode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import AbandonedCode.QuestionTypes.FinishRoom;
import AbandonedCode.QuestionTypes.MultipleChoice;
import AbandonedCode.QuestionTypes.OpenEnded;
import AbandonedCode.QuestionTypes.Question;

public class Questions {


    private static Question[] questions;
    private static Question[] finishMessages;
    private static int numOfQuestions;
    private static int numOfFinishMessages;

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
        Question[][] arrays = createQuestionsArrays(splitString);
        questions = arrays[0];
        numOfQuestions = questions.length;
        finishMessages = arrays[1];
        numOfFinishMessages = finishMessages.length;
    }

    private static String[] splitString(String s, char delimeter){
        // get number of substrings
        int numOfStrings = 1;
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
        splitStrings[currStringNum]=currString;

        // return final array
        return splitStrings;
    }

    private static Question[][] createQuestionsArrays(String[] strings){

        List<Question> Questions = new ArrayList<>();
        List<Question> finishMessages = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {

            String question;
            
            String questionType = strings[i];
            i++;

            switch(questionType){
                case "MultipleChoice":

                    // MultipleChoice format
                    // NumberOfAnswers      Question    Answers     CorrectAnswer
                    // int                  String      String[]    int

                    // Example 4,What is the space complexity of merge sort?,O(1),O(log n),O(n),O(n log n),4

                    // get the numberOfAnswers
                    int numberOfAnswers = Integer.parseInt(strings[i]);
                    i++;

                    // get the question
                    question = strings[i];                           
                    i++;

                    // get the array of answers
                    String[] answers = new String[numberOfAnswers];         
                    for (int j = 0; j<answers.length; j++) {
                        answers[j] = strings[i];                                
                        i++;
                    }

                    // get the correct answer
                    int correctAnswerNumber = Integer.parseInt(""+strings[i].charAt(0));

                    Questions.add(new MultipleChoice());
                break;

                case "OpenEnded":

                    // OpenEnded format
                    // Question     AnswerFormat        CorrectAnswer
                    // String       String              String

                    // Example What is the method header for removing an item from a stack,(EG. Add() Clear()), Pop()

                    // get the question
                    question = strings[i];
                    i++;

                    // get the answer format
                    String answerFormat = strings[i];
                    i++;

                    // get the correct answer
                    String correctAnswer = strings[i].substring(0,strings[i].length()-1);


                    Questions.add(new OpenEnded());
                break;

                case "FinishRoom":

                    // MultipleChoice
                    // finishMessage        congratsMessage
                    // String               String

                    // Example The End, Thank you For playing the game. You Win!

                    // get the finish message
                    String finishMessage = strings[i];
                    i++;

                    // get the congrats message
                    String congratsMessage = strings[i].substring(0,strings[i].length()-1);

                    finishMessages.add(new FinishRoom());
                break;
            }
        }
        return new Question[][]{
            Questions.toArray(new Question[0]),
            finishMessages.toArray(new Question[0])
        };
    }

    public static Question getQuestion(int questionNumber){
        // check if array is empty
        if(numOfQuestions==0){
            System.out.println("The List Of Questions, was empty");
            return new OpenEnded();
        }

        // check if out of bounds
        if(questionNumber<0||questionNumber>=numOfQuestions){
            System.out.println("trying to index Question Array out of bounds");
            return new OpenEnded();
        }

        // return question
        return questions[questionNumber];
    }

    public static Question getFinishRoom(int messageNumber){
        // check if array is empty
        if(numOfFinishMessages==0){
            System.out.println("The List Of Messages, was empty");
            return new FinishRoom();
        }

        // check if out of bounds
        if(messageNumber<0||messageNumber>=numOfFinishMessages){
            System.out.println("trying to index FinishMessage Array out of bounds");
            return new FinishRoom();
        }

        // return question
        return finishMessages[messageNumber];
    }

    public static int size(){
        return numOfQuestions;
    }

    public static int finishMessagesSize(){
        return numOfFinishMessages;
    }

    public static void printQuestions(){
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
