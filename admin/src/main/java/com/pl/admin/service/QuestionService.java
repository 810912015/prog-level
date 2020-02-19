package com.pl.admin.service;


import com.pl.admin.dto.PlayDto;
import com.pl.admin.dto.QuestionDto;
import com.pl.data.model.Pass;

public interface QuestionService {
    QuestionDto addOrUpdate(QuestionDto d);

    Engine.ExecuteResult pass(Pass p);

    String play(PlayDto pd);
}
