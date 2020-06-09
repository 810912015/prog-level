package com.pl.portal.service;

import com.pl.data.common.api.CommonResult;
import com.pl.data.model.Question;
import com.pl.data.model.TArticle;
import com.pl.portal.dto.ThinQuestion;

import java.util.List;

public interface ISummary {
    class Item<T>{
        private Integer count;
        private List<T> items;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }
    }
    class Data{
        private Item<TArticle> articles;
        private Item<ThinQuestion> questions;

        public Item<TArticle> getArticles() {
            return articles;
        }

        public void setArticles(Item<TArticle> articles) {
            this.articles = articles;
        }

        public Item<ThinQuestion> getQuestions() {
            return questions;
        }

        public void setQuestions(Item<ThinQuestion> questions) {
            this.questions = questions;
        }
    }

    CommonResult<Data> summary();
}
