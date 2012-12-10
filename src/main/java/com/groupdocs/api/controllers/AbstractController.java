package com.groupdocs.api.controllers;

import freemarker.log.Logger;

public abstract class AbstractController {

    protected Logger log = Logger.getLogger(this.getClass().getName());

//    @Value("#{wiringProperties['sample.key']}")
//    protected String sampleKey;
}
