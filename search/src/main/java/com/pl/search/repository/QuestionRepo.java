package com.pl.search.repository;

import com.pl.search.domain.Qdto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionRepo
   extends ElasticsearchRepository<Qdto,Long> {
}
