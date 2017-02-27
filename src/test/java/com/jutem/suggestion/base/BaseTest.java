package com.jutem.suggestion.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jutem.suggestion.config.SuggestionConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SuggestionConfig.class)
public abstract class BaseTest {

}
