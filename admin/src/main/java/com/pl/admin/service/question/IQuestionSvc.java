package com.pl.admin.service.question;

import com.pl.admin.dto.Bound;
import com.pl.admin.dto.Result;
import com.pl.admin.dto.ThinQuestion;
import com.pl.data.model.Question;

import java.util.List;

public interface IQuestionSvc {
    Result<List<Meta>> allTags();
    Result<List<Question>> byTag(Args tag);
    Result<List<Index<Question>>> recommend();

    class Meta{
        private int count;
        private List<String> desc;
        private String name;
        private String type;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<String> getDesc() {
            return desc;
        }

        public void setDesc(List<String> desc) {
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    class Args{
        private Meta meta;
        private Bound bound;

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public Bound getBound() {
            return bound;
        }

        public void setBound(Bound bound) {
            this.bound = bound;
        }
    }

    class Index<T> extends Meta{
        private List<T> list;

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
    }
}
