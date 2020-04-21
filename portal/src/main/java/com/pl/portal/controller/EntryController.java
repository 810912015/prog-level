package com.pl.portal.controller;

import com.google.common.base.Throwables;
import com.pl.portal.dao.ScoreMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "EntryController",description = "EntryController api")
public class EntryController extends BaseController {

    @ApiOperation(value = "invite")
    @RequestMapping(value = "/invite/{iid}",method = RequestMethod.POST)
    public String doInvite(Map<String, Object> model, @PathVariable String iid) {
        try {
            List<Integer> li = sm.getQid(iid);
            if (li == null || li.isEmpty()) {
                model.put("cur", "[]");
                model.put("msg", "无此邀请或邀请已过期");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (int i = 0; i < li.size(); i++) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(li.get(i));
                }
                sb.append("]");
                model.put("cur", sb);
                model.put("msg", "");
                model.put("eiid", iid);
                model.put("userName", curUser().getEmail());
            }
        } catch (Exception e) {
            model.put("cur", "[]");
            model.put("msg", "无此邀请或邀请已过期");
            logger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
        }
        return "et";
    }

    private ScoreMapper sm;

    @Autowired
    public void setSm(ScoreMapper sm) {
        this.sm = sm;
    }
}
