package com.example.DAO;

import com.example.entity.Tutorial;
import com.example.exception.DBOperationException;
import com.example.exception.TutorialNotFoundException;

import java.util.List;

public interface TutorialDao {
    void addTutorial(Tutorial tutorial) throws DBOperationException;
    Tutorial getTutorialById(int id) throws DBOperationException, TutorialNotFoundException;
    List<Tutorial> getAllTutorials() throws DBOperationException;
    void updateTutorial(Tutorial tutorial) throws DBOperationException, TutorialNotFoundException;
    void deleteTutorial(int id) throws DBOperationException, TutorialNotFoundException;
}
