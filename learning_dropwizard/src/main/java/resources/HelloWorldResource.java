/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import core.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Florian Bauer on 02.10.14. flbaue@posteo.de
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/hello-world", description = "Says hello!")
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @ApiOperation(value = "Says hello to NAME", notes = "More notes about this method", response = Saying.class)
    public Saying sayHello(@ApiParam(value = "your NAME", required = false) @QueryParam("name")
                               Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
