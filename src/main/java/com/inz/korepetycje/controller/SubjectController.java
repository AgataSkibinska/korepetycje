package com.inz.korepetycje.controller;

import com.inz.korepetycje.model.advertisement.Subject;
import com.inz.korepetycje.repository.SubjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/all")
    public List<String> getAllSubjects(){
        return subjectRepository.findAll().stream().map(Subject::getName).collect(Collectors.toList());
    }
}
