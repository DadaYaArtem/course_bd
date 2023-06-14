package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.UserAction;
import org.example.repository.UserActionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActionService {
    private final UserActionRepository userActionRepository;

    public List<UserAction> getAllActions() {
        return userActionRepository.findAll();
    }

    public void trackPageVisit(Long userId, String pageName) {
        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setAction("Visited page: " + pageName);
        userAction.setTimestamp(LocalDateTime.now());

        userActionRepository.save(userAction);
    }

    public void trackDataChange(Long userId, String fieldName) {
        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setAction("Edited data: " + fieldName);
        userAction.setTimestamp(LocalDateTime.now());

        userActionRepository.save(userAction);
    }

    public void trackDataDelete(Long userId, String action) {
        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setAction("Deleted data: " + action );
        userAction.setTimestamp(LocalDateTime.now());

        userActionRepository.save(userAction);
    }
}
