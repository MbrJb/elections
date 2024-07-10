package com.assessment.elections.controller;

import com.assessment.elections.model.Candidate;
import com.assessment.elections.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/add")
    public Candidate addNewCandidate(@RequestBody Candidate candidate){
        return candidateService.addCandidate(candidate);
    }

    @GetMapping
    public List<Candidate> getAllCandidates(){
        return candidateService.findAllCandidates();
    }

    @GetMapping("/find/{id}")
    public Candidate getCandidateById(@PathVariable("id") Long id) {
       return candidateService.findCandidateById(id);
    }

    @PutMapping("/update")
    public Candidate updateCandidate(@RequestBody Candidate candidate){
        return candidateService.updateCandidate(candidate);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCandidate(@PathVariable("id") Long id){
        candidateService.deleteCandidate(id);
    }

}
