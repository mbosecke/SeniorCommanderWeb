package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.QuoteModel;
import com.mitchellbosecke.seniorcommander.web.repository.QuoteModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteModelRepository quoteModelRepository;

    @Override
    public QuoteModel findRandomQuote(String communityName) {
        List<QuoteModel> quotes = quoteModelRepository.findRandomQuotes(communityName, new PageRequest(0, 1));
        QuoteModel result = null;
        if(!quotes.isEmpty()){
            result = quotes.get(0);
        }
        return result;
    }

    @Override
    public Page<QuoteModel> findQuotes(String communityName, Pageable pageable) {
        return quoteModelRepository.findAllByComunityName(communityName, pageable);
    }
}
