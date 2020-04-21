package com.pl.portal.service.question;


import com.pl.portal.dto.Bound;
import com.pl.portal.dto.PlayDto;
import com.pl.portal.dto.QuestionDto;
import com.pl.portal.service.runner.Engine;
import com.pl.data.model.Pass;

import java.util.List;

public interface QuestionService {
    QuestionDto addOrUpdate(QuestionDto d);

    Engine.ExecuteResult pass(Pass p);

    String play(PlayDto pd);

    List<Pass> getByUidOrAid(Integer uid, String aid, PassArgs bound);

    class PassArgs extends Bound{
        private Long qid;

        public Long getQid() {
            return qid;
        }

        public void setQid(Long qid) {
            this.qid = qid;
        }
    }
}
