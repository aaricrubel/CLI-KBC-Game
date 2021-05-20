import java.util.LinkedHashMap;
import java.util.Scanner;

public class Kbc {
    Questions qobj = new Questions();
    int totalMoneyWon = 0;
    boolean hasLifeline = true;
    int level = 1;
    boolean hasWon = true;

    public void kbc() {
        Scanner scanner = new Scanner(System.in);
        qobj.questions();
        int prizeWon = 0;
        int roundNumber = 0;
        int lifeLine = 1;
        boolean wrongAnswer = false;
        System.out.println("Welcome to KBC");
        System.out.println("Choose any option given below to start the game");
        System.out.println("1 -> Start the game");
        System.out.println("2 -> Quit the game");

        int userChoice = Integer.parseInt(scanner.nextLine());

        if (userChoice == 2) {
            System.exit(0);
        }

        while (roundNumber <= 14) {
            System.out.println("\n\t\tLifeline Remains:   " + lifeLine + "\n");
            System.out.println("Question " + (roundNumber + 1) + " : " + qobj.questions.get(roundNumber).get("name"));
            System.out.println("\n\t\t\tOptions:");
            System.out.println("\t\t\t\tOption 1: " + qobj.questions.get(roundNumber).get("option1"));
            System.out.println("\t\t\t\tOption 2: " + qobj.questions.get(roundNumber).get("option2"));
            System.out.println("\t\t\t\tOption 3: " + qobj.questions.get(roundNumber).get("option3"));
            System.out.println("\t\t\t\tOption 4: " + qobj.questions.get(roundNumber).get("option4"));

            System.out.println("Enter your choice from 1 to 4 or type 0 to use lifeline or 5 to quit the game");
            int answer = scanner.nextInt();
            while (answer > 5 || answer < 0) {
                System.out.println("Please choose a correct option");
                System.out.println("Enter your choice from 1 to 4");
                answer = scanner.nextInt();
            }
            if (answer == 5)
                break;
            else if (answer == 0 && roundNumber == 14) {
                System.out.println("You can't use a lifeline in your last question");
                System.out.println("Enter your choice from 1 to 4 or 5 to quit the game");
                answer = scanner.nextInt();
            } else if (answer == 0 && lifeLine == 0) {
                System.out.println("You don't have a lifeline");
                System.out.println("Enter your choice from 1 to 4 or 5 to quit the game");
                answer = scanner.nextInt();
            } else if (answer == 0 && lifeLine == 1) {
                var q = lifeLine(qobj.questions.get(roundNumber));
                System.out.println("Question: " + roundNumber + 1 + " : " + q.get("name"));
                System.out.println("Options: ");

                if ((Integer) q.get("answer") == 1 || (Integer) q.get("answer") == 2) {
                    System.out.println("\t\t\tOption 1: " + q.get("option1"));
                    System.out.println("\t\t\tOption 2: " + q.get("option2"));
                } else if ((Integer) q.get("answer") == 3 || (Integer) q.get("answer") == 4) {
                    System.out.println("\t\t\tOption 1: " + q.get("option3"));
                    System.out.println("\t\t\tOption 2: " + q.get("option4"));
                }
                lifeLine--;

                System.out.println("Your choice 1 or 2: ");
                answer = scanner.nextInt();
            }

            if (isAnswerCorrect(qobj.questions.get(roundNumber), answer)) {
                System.out.println("\n\t\t\t****Correct Answer !****");
                prizeWon = (Integer) qobj.questions.get(roundNumber).get("money");
                System.out.println("\t\t\tMoney Won Till Now: " + prizeWon);
            } else {
                System.out.println("\n\nIncorrect !");
                System.out.println("The Correct answer was: " + qobj.questions.get(roundNumber).get("answer"));
                wrongAnswer = true;
                break;
            }
            roundNumber++;
        }

        if (!wrongAnswer) {
            System.out.println("Your total prize won ==> Rs. " + prizeWon);
        } else {
            if (roundNumber + 1 < 5) {
                System.out.println("You Won: Rs. 0");
            } else if (roundNumber + 1 >= 5 && roundNumber + 1 < 11) {
                System.out.println("You Won: Rs. 10,000");
            } else if (roundNumber + 1 >= 11) {
                System.out.println("You Won: Rs. 3,20,000");
            }
        }
    }

    private boolean isAnswerCorrect(LinkedHashMap<String, Object> question, int answer) {
        return (Integer) question.get("answer") == answer;
    }

    private LinkedHashMap<String, Object> lifeLine(LinkedHashMap<String, Object> ques) {
        int correctOption = (Integer) ques.get("answer");
        String optionToDelete1 = "";
        String optionToDelete2 = "";

        if (correctOption == 1) {
            optionToDelete1 = "option3";
            optionToDelete2 = "option4";
        } else if (correctOption == 2) {
            optionToDelete1 = "option3";
            optionToDelete2 = "option4";
        } else if (correctOption == 3) {
            optionToDelete1 = "option1";
            optionToDelete2 = "option2";
        } else if (correctOption == 4) {
            optionToDelete1 = "option1";
            optionToDelete2 = "option2";
        }

        ques.remove(optionToDelete1);
        ques.remove(optionToDelete2);

        return ques;
    }
}
