package com.example.main;

import com.example.DAO.TutorialDao;
import com.example.DAO.impl.Tutorialdaoimpl;
import com.example.entity.Tutorial;
import com.example.exception.DBOperationException;
import com.example.exception.TutorialNotFoundException;

import java.util.List;

public class TutorialManagement {
    static TutorialDao dao = new Tutorialdaoimpl();
    static int choice;

    static void main() {
        do {
            IO.println("\n--- Tutorial Management System ---");
            IO.println("1. Add Tutorial");
            IO.println("2. View All Tutorials");
            IO.println("3. View Tutorial by ID");
            IO.println("4. Update Tutorial");
            IO.println("5. Delete Tutorial");
            IO.println("6. Exit");
            IO.println("Enter your choice: ");
            choice = Integer.parseInt(IO.readln());
            try {
                switch (choice) {

                    case 1 -> {
                        IO.println("Please enter the tutorial ID: ");
                        int id = Integer.parseInt(IO.readln());
                        IO.println("Please enter the tutorial title: ");
                        String title = IO.readln();
                        IO.println("Please enter the tutorial author: ");
                        String author = IO.readln();
                        IO.println("Please enter the tutorial url: ");
                        String url = IO.readln();
                        dao.addTutorial(new Tutorial(id, title, author, url));
                        IO.println("Tutorial added successfully: ");
                    }

                    case 2 -> {
                        List<Tutorial> tutorials = dao.getAllTutorials();
                        if (tutorials.isEmpty()) {
                            IO.println("There is no Tutorial");
                        } else
                            tutorials.forEach(System.out::println);
                    }

                    case 3 ->
                    {
                        IO.println("Please enter the tutorial ID to search tutorial: ");
                        int searchId = Integer.parseInt(IO.readln());
                        if(dao.getTutorialById(searchId) != null)
                        {
                            IO.println("Tutorial with ID " + searchId + " found successfully");
                        }
                        else {
                            IO.println("Tutorial with ID " + searchId + " not found");
                        }
                    }

                    case 4 ->
                    {
                        IO.println("Please enter the tutorial ID to update tutorial: ");
                        int updateId = Integer.parseInt(IO.readln());
                        Tutorial t =dao.getTutorialById(updateId);
                        if(t != null)
                        {
                            IO.println("Enter the new tutorial title: ");
                            String title = IO.readln();
                            IO.println("Enter the new tutorial author: ");
                            String author = IO.readln();
                            IO.println("Enter the new tutorial url: ");
                            String url = IO.readln();
                            t.setTitle(title);
                            t.setAuthor(author);
                            t.setUrl(url);
                            dao.updateTutorial(t);
                            IO.println("Tutorial updated successfully");
                        }
                        else {
                            IO.println("Tutorial with ID " + updateId + " not found");
                        }
                    }

                    case 5 ->
                    {
                        IO.println("Please enter the tutorial ID to delete tutorial: ");
                        int deleteId = Integer.parseInt(IO.readln());
                        if(dao.getTutorialById(deleteId) != null)
                        {
                            dao.deleteTutorial(deleteId);
                            IO.println("Tutorial deleted successfully");
                        }
                        else {
                            IO.println("Tutorial with ID " + deleteId + " not found");
                        }
                    }

                    case 6 ->
                    {
                        IO.println("Thank you for using TutorialManagement");
                        System.exit(0);
                    }
                    default -> IO.println("Please enter a valid choice");

                }
            } catch (DBOperationException | TutorialNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (choice != 6);
    }
}