package com.pl.portal.controller;

import com.pl.portal.dto.Result;
import com.pl.portal.dto.ThinQuestion;
import com.pl.portal.service.question.IQuestionSvc;
import com.pl.data.model.Question;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "QuestionController",description = "question api")
@RequestMapping("question")
public class QuestionController extends BaseController{
    @RequestMapping(value = "tags",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<IQuestionSvc.Meta>> tags(){
        return questionService.allTags();
    }

    @RequestMapping(value = "bytag",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Question>> byTag(@RequestBody IQuestionSvc.Args meta){
        return questionService.byTag(meta);
    }

    @RequestMapping(value = "recommend",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<IQuestionSvc.Index<Question>>> recommend(){
        return questionService.recommend();
    }

    @Autowired
    private IQuestionSvc questionService;
}
