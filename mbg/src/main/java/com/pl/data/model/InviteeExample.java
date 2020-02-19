package com.pl.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InviteeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InviteeExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andCat1IsNull() {
            addCriterion("cat1 is null");
            return (Criteria) this;
        }

        public Criteria andCat1IsNotNull() {
            addCriterion("cat1 is not null");
            return (Criteria) this;
        }

        public Criteria andCat1EqualTo(String value) {
            addCriterion("cat1 =", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1NotEqualTo(String value) {
            addCriterion("cat1 <>", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1GreaterThan(String value) {
            addCriterion("cat1 >", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1GreaterThanOrEqualTo(String value) {
            addCriterion("cat1 >=", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1LessThan(String value) {
            addCriterion("cat1 <", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1LessThanOrEqualTo(String value) {
            addCriterion("cat1 <=", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1Like(String value) {
            addCriterion("cat1 like", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1NotLike(String value) {
            addCriterion("cat1 not like", value, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1In(List<String> values) {
            addCriterion("cat1 in", values, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1NotIn(List<String> values) {
            addCriterion("cat1 not in", values, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1Between(String value1, String value2) {
            addCriterion("cat1 between", value1, value2, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat1NotBetween(String value1, String value2) {
            addCriterion("cat1 not between", value1, value2, "cat1");
            return (Criteria) this;
        }

        public Criteria andCat2IsNull() {
            addCriterion("cat2 is null");
            return (Criteria) this;
        }

        public Criteria andCat2IsNotNull() {
            addCriterion("cat2 is not null");
            return (Criteria) this;
        }

        public Criteria andCat2EqualTo(String value) {
            addCriterion("cat2 =", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2NotEqualTo(String value) {
            addCriterion("cat2 <>", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2GreaterThan(String value) {
            addCriterion("cat2 >", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2GreaterThanOrEqualTo(String value) {
            addCriterion("cat2 >=", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2LessThan(String value) {
            addCriterion("cat2 <", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2LessThanOrEqualTo(String value) {
            addCriterion("cat2 <=", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2Like(String value) {
            addCriterion("cat2 like", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2NotLike(String value) {
            addCriterion("cat2 not like", value, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2In(List<String> values) {
            addCriterion("cat2 in", values, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2NotIn(List<String> values) {
            addCriterion("cat2 not in", values, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2Between(String value1, String value2) {
            addCriterion("cat2 between", value1, value2, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat2NotBetween(String value1, String value2) {
            addCriterion("cat2 not between", value1, value2, "cat2");
            return (Criteria) this;
        }

        public Criteria andCat3IsNull() {
            addCriterion("cat3 is null");
            return (Criteria) this;
        }

        public Criteria andCat3IsNotNull() {
            addCriterion("cat3 is not null");
            return (Criteria) this;
        }

        public Criteria andCat3EqualTo(String value) {
            addCriterion("cat3 =", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3NotEqualTo(String value) {
            addCriterion("cat3 <>", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3GreaterThan(String value) {
            addCriterion("cat3 >", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3GreaterThanOrEqualTo(String value) {
            addCriterion("cat3 >=", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3LessThan(String value) {
            addCriterion("cat3 <", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3LessThanOrEqualTo(String value) {
            addCriterion("cat3 <=", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3Like(String value) {
            addCriterion("cat3 like", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3NotLike(String value) {
            addCriterion("cat3 not like", value, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3In(List<String> values) {
            addCriterion("cat3 in", values, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3NotIn(List<String> values) {
            addCriterion("cat3 not in", values, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3Between(String value1, String value2) {
            addCriterion("cat3 between", value1, value2, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat3NotBetween(String value1, String value2) {
            addCriterion("cat3 not between", value1, value2, "cat3");
            return (Criteria) this;
        }

        public Criteria andCat4IsNull() {
            addCriterion("cat4 is null");
            return (Criteria) this;
        }

        public Criteria andCat4IsNotNull() {
            addCriterion("cat4 is not null");
            return (Criteria) this;
        }

        public Criteria andCat4EqualTo(String value) {
            addCriterion("cat4 =", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4NotEqualTo(String value) {
            addCriterion("cat4 <>", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4GreaterThan(String value) {
            addCriterion("cat4 >", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4GreaterThanOrEqualTo(String value) {
            addCriterion("cat4 >=", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4LessThan(String value) {
            addCriterion("cat4 <", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4LessThanOrEqualTo(String value) {
            addCriterion("cat4 <=", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4Like(String value) {
            addCriterion("cat4 like", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4NotLike(String value) {
            addCriterion("cat4 not like", value, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4In(List<String> values) {
            addCriterion("cat4 in", values, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4NotIn(List<String> values) {
            addCriterion("cat4 not in", values, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4Between(String value1, String value2) {
            addCriterion("cat4 between", value1, value2, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat4NotBetween(String value1, String value2) {
            addCriterion("cat4 not between", value1, value2, "cat4");
            return (Criteria) this;
        }

        public Criteria andCat5IsNull() {
            addCriterion("cat5 is null");
            return (Criteria) this;
        }

        public Criteria andCat5IsNotNull() {
            addCriterion("cat5 is not null");
            return (Criteria) this;
        }

        public Criteria andCat5EqualTo(String value) {
            addCriterion("cat5 =", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5NotEqualTo(String value) {
            addCriterion("cat5 <>", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5GreaterThan(String value) {
            addCriterion("cat5 >", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5GreaterThanOrEqualTo(String value) {
            addCriterion("cat5 >=", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5LessThan(String value) {
            addCriterion("cat5 <", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5LessThanOrEqualTo(String value) {
            addCriterion("cat5 <=", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5Like(String value) {
            addCriterion("cat5 like", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5NotLike(String value) {
            addCriterion("cat5 not like", value, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5In(List<String> values) {
            addCriterion("cat5 in", values, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5NotIn(List<String> values) {
            addCriterion("cat5 not in", values, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5Between(String value1, String value2) {
            addCriterion("cat5 between", value1, value2, "cat5");
            return (Criteria) this;
        }

        public Criteria andCat5NotBetween(String value1, String value2) {
            addCriterion("cat5 not between", value1, value2, "cat5");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
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