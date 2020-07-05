package com.qa.account.service;

public interface Mapper<Source, Target> {

	Target mapToDTO(Source source);
}

