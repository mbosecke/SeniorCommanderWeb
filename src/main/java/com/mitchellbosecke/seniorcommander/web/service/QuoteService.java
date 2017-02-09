package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.domain.QuoteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by mitch_000 on 2016-09-11.
 */
public interface QuoteService {

    QuoteModel findRandomQuote(String communityName);

    Page<QuoteModel> findQuotes(String communityName, Pageable pageable);

}
