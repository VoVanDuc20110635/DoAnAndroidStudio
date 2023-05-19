package com.data.filtro.service;

import com.data.filtro.model.Feedback;
import com.data.filtro.model.User;
import com.data.filtro.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedbackByProduct(int productId) {
        return feedbackRepository.findAllByProductId(productId);
    }

    public Feedback addFeedback(Feedback feedback) {

        return feedbackRepository.save(feedback);
    }

}