package com.example.demo.exam;

import com.example.demo.exam.annotation.Retry;
import com.example.demo.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    @Retry
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
