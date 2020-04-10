package com.pl.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamInviteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ExamInviteExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public Criteria handleQueryArgs(com.pl.data.common.api.IQueryArgs query) {
        Criteria c=createCriteria();
        if(query==null) return c;
        if(query.toSql()!=null){
            c.addCriterion(query.toSql());
        }
        setOrderByClause("id desc");
        setLimit(query.getSize());
        if(query.makeStart()!=null) setOffset(query.makeStart());
        return c;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("UserId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("UserId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("UserId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("UserId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("UserId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("UserId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("UserId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("UserId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("UserId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("UserId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andVuseridIsNull() {
            addCriterion("vUserId is null");
            return (Criteria) this;
        }

        public Criteria andVuseridIsNotNull() {
            addCriterion("vUserId is not null");
            return (Criteria) this;
        }

        public Criteria andVuseridEqualTo(Long value) {
            addCriterion("vUserId =", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridNotEqualTo(Long value) {
            addCriterion("vUserId <>", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridGreaterThan(Long value) {
            addCriterion("vUserId >", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("vUserId >=", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridLessThan(Long value) {
            addCriterion("vUserId <", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridLessThanOrEqualTo(Long value) {
            addCriterion("vUserId <=", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridIn(List<Long> values) {
            addCriterion("vUserId in", values, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridNotIn(List<Long> values) {
            addCriterion("vUserId not in", values, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridBetween(Long value1, Long value2) {
            addCriterion("vUserId between", value1, value2, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridNotBetween(Long value1, Long value2) {
            addCriterion("vUserId not between", value1, value2, "vuserid");
            return (Criteria) this;
        }

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(Integer value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(Integer value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(Integer value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(Integer value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(Integer value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(Integer value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<Integer> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<Integer> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(Integer value1, Integer value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(Integer value1, Integer value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andUemailIsNull() {
            addCriterion("uemail is null");
            return (Criteria) this;
        }

        public Criteria andUemailIsNotNull() {
            addCriterion("uemail is not null");
            return (Criteria) this;
        }

        public Criteria andUemailEqualTo(String value) {
            addCriterion("uemail =", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotEqualTo(String value) {
            addCriterion("uemail <>", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailGreaterThan(String value) {
            addCriterion("uemail >", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailGreaterThanOrEqualTo(String value) {
            addCriterion("uemail >=", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailLessThan(String value) {
            addCriterion("uemail <", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailLessThanOrEqualTo(String value) {
            addCriterion("uemail <=", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailLike(String value) {
            addCriterion("uemail like", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotLike(String value) {
            addCriterion("uemail not like", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailIn(List<String> values) {
            addCriterion("uemail in", values, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotIn(List<String> values) {
            addCriterion("uemail not in", values, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailBetween(String value1, String value2) {
            addCriterion("uemail between", value1, value2, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotBetween(String value1, String value2) {
            addCriterion("uemail not between", value1, value2, "uemail");
            return (Criteria) this;
        }

        public Criteria andIidIsNull() {
            addCriterion("iid is null");
            return (Criteria) this;
        }

        public Criteria andIidIsNotNull() {
            addCriterion("iid is not null");
            return (Criteria) this;
        }

        public Criteria andIidEqualTo(String value) {
            addCriterion("iid =", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotEqualTo(String value) {
            addCriterion("iid <>", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThan(String value) {
            addCriterion("iid >", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThanOrEqualTo(String value) {
            addCriterion("iid >=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThan(String value) {
            addCriterion("iid <", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThanOrEqualTo(String value) {
            addCriterion("iid <=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLike(String value) {
            addCriterion("iid like", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotLike(String value) {
            addCriterion("iid not like", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidIn(List<String> values) {
            addCriterion("iid in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotIn(List<String> values) {
            addCriterion("iid not in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidBetween(String value1, String value2) {
            addCriterion("iid between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotBetween(String value1, String value2) {
            addCriterion("iid not between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andDoneIsNull() {
            addCriterion("done is null");
            return (Criteria) this;
        }

        public Criteria andDoneIsNotNull() {
            addCriterion("done is not null");
            return (Criteria) this;
        }

        public Criteria andDoneEqualTo(String value) {
            addCriterion("done =", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotEqualTo(String value) {
            addCriterion("done <>", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneGreaterThan(String value) {
            addCriterion("done >", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneGreaterThanOrEqualTo(String value) {
            addCriterion("done >=", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneLessThan(String value) {
            addCriterion("done <", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneLessThanOrEqualTo(String value) {
            addCriterion("done <=", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneLike(String value) {
            addCriterion("done like", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotLike(String value) {
            addCriterion("done not like", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneIn(List<String> values) {
            addCriterion("done in", values, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotIn(List<String> values) {
            addCriterion("done not in", values, "done");
            return (Criteria) this;
        }

        public Criteria andDoneBetween(String value1, String value2) {
            addCriterion("done between", value1, value2, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotBetween(String value1, String value2) {
            addCriterion("done not between", value1, value2, "done");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("Remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("Remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("Remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("Remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("Remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("Remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("Remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("Remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("Remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("Remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("Remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("Remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("Remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("Remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andDcltIsNull() {
            addCriterion("Dclt is null");
            return (Criteria) this;
        }

        public Criteria andDcltIsNotNull() {
            addCriterion("Dclt is not null");
            return (Criteria) this;
        }

        public Criteria andDcltEqualTo(Date value) {
            addCriterion("Dclt =", value, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltNotEqualTo(Date value) {
            addCriterion("Dclt <>", value, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltGreaterThan(Date value) {
            addCriterion("Dclt >", value, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltGreaterThanOrEqualTo(Date value) {
            addCriterion("Dclt >=", value, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltLessThan(Date value) {
            addCriterion("Dclt <", value, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltLessThanOrEqualTo(Date value) {
            addCriterion("Dclt <=", value, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltIn(List<Date> values) {
            addCriterion("Dclt in", values, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltNotIn(List<Date> values) {
            addCriterion("Dclt not in", values, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltBetween(Date value1, Date value2) {
            addCriterion("Dclt between", value1, value2, "dclt");
            return (Criteria) this;
        }

        public Criteria andDcltNotBetween(Date value1, Date value2) {
            addCriterion("Dclt not between", value1, value2, "dclt");
            return (Criteria) this;
        }

        public Criteria andFirmidIsNull() {
            addCriterion("firmId is null");
            return (Criteria) this;
        }

        public Criteria andFirmidIsNotNull() {
            addCriterion("firmId is not null");
            return (Criteria) this;
        }

        public Criteria andFirmidEqualTo(Integer value) {
            addCriterion("firmId =", value, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidNotEqualTo(Integer value) {
            addCriterion("firmId <>", value, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidGreaterThan(Integer value) {
            addCriterion("firmId >", value, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidGreaterThanOrEqualTo(Integer value) {
            addCriterion("firmId >=", value, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidLessThan(Integer value) {
            addCriterion("firmId <", value, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidLessThanOrEqualTo(Integer value) {
            addCriterion("firmId <=", value, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidIn(List<Integer> values) {
            addCriterion("firmId in", values, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidNotIn(List<Integer> values) {
            addCriterion("firmId not in", values, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidBetween(Integer value1, Integer value2) {
            addCriterion("firmId between", value1, value2, "firmid");
            return (Criteria) this;
        }

        public Criteria andFirmidNotBetween(Integer value1, Integer value2) {
            addCriterion("firmId not between", value1, value2, "firmid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public void addCriterion(String sql) {
            super.addCriterion(sql);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}