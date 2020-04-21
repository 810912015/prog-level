package com.pl.search.service;


import com.pl.search.domain.Qdto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearch {
    Page<Qdto> search(String text, Integer page, Integer size);
}
