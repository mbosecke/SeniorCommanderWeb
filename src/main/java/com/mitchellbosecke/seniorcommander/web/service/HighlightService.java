package com.mitchellbosecke.seniorcommander.web.service;


import java.util.List;

/**
 * Created by mitch_000 on 2016-09-11.
 */
public interface HighlightService {

      List<Highlight> findHighlights(String communityName);
}
