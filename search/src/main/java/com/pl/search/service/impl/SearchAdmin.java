package com.pl.search.service.impl;

import com.pl.data.mapper.QuestionMapper;
import com.pl.data.model.Question;
import com.pl.data.model.QuestionExample;
import com.pl.search.domain.Qdto;
import com.pl.search.repository.QuestionRepo;
import com.pl.search.service.ISearchAdmin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchAdmin implements ISearchAdmin {
    @Override
    public void importAll() {
        List<Question> l=mapper.selectByExample(new QuestionExample());
        if(l==null||l.isEmpty()) return;
        ModelMapper mm=new ModelMapper();
        for(Question q:l){
            Qdto d=mm.map(q,Qdto.class);
            repo.index(d);
        }
    }

    @Autowired
    private QuestionMapper mapper;
    @Autowired
    private QuestionRepo repo;
}
