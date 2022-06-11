package com.example.demo.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceService {
    @Autowired
    private SequenceDao sequenceDao;

    public String generate(String sequenceId){
        Sequence sequence = sequenceDao.getSequence(sequenceId);
        int nextValue = sequenceDao.getNextValue(sequenceId);
        return sequence.getPrefix() + nextValue + sequence.getSuffix();
    }
}
