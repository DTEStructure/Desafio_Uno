package com.previred.restGDD.service;

import com.previred.restGDD.models.GddRequest;
import com.previred.restGDD.models.GddResponse;

public interface GddService {
	public abstract GddResponse getPeriodos(GddRequest request);
}
