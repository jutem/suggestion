package com.jutem.suggestion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration{
	
	private static final String DBNAME = "suggestion";

	@Override
	protected String getDatabaseName() {
		return DBNAME;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}

}
