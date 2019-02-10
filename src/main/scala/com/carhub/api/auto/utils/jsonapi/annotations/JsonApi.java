package com.carhub.api.auto.utils.jsonapi.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonApi {

    String apiType();

}
