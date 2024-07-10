package com.assessment.elections.service;

import com.assessment.elections.exception.CandidateNotFoundException;
import com.assessment.elections.model.Candidate;
import com.assessment.elections.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate addCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public List<Candidate> findAllCandidates(){
        return candidateRepository.findAll();
    }

    public Candidate findCandidateById(Long id){
        return candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException
                        ("There is no candidate with an id of " + id + "!"));
    }

    @Transactional
    public Candidate updateCandidate(Candidate candidate){

        Candidate existingCandidate = candidateRepository.findById(candidate.getId())
                .orElseThrow(() -> new CandidateNotFoundException
                ("There is no such candidate!"));

        existingCandidate.setName(candidate.getName());
        existingCandidate.setMotto(candidate.getMotto());
        return candidateRepository.save(existingCandidate);
    }

    @Transactional
    public void deleteCandidate(Long id){
       boolean exists = candidateRepository.findById(id).isPresent();

       if(exists){
           candidateRepository.deleteById(id);
       }
       else {
           throw new CandidateNotFoundException("There is no candidate with an id of " + id + "!");
       }

    }
}
