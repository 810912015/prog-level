package com.pl.search.service;

import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;

import java.util.List;

public interface IQueryMaker {

    FunctionScoreQueryBuilder makeQuery(String k);

    class Aq{
        private String analyzer;
        private String text;

        public String getAnalyzer() {
            return analyzer;
        }

        public void setAnalyzer(String analyzer) {
            this.analyzer = analyzer;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    class Ar{
        public static class Item{
            private String token;
            private Integer start_offset;
            private Integer end_offset;
            private String type;
            private Integer position;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public Integer getStart_offset() {
                return start_offset;
            }

            public void setStart_offset(Integer start_offset) {
                this.start_offset = start_offset;
            }

            public Integer getEnd_offset() {
                return end_offset;
            }

            public void setEnd_offset(Integer end_offset) {
                this.end_offset = end_offset;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Integer getPosition() {
                return position;
            }

            public void setPosition(Integer position) {
                this.position = position;
            }
        }

        public List<Ar.Item> getTokens() {
            return tokens;
        }

        public void setTokens(List<Ar.Item> tokens) {
            this.tokens = tokens;
        }

        private List<Ar.Item> tokens;
    }
}
