/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

import health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.HelloWorldResource;

/**
 * Created by Florian Bauer on 02.10.14. flbaue@posteo.de
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource helloWorldResource = new HelloWorldResource(configuration.getTemplate(),
                configuration.getDefaultName());
        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());

        environment.jersey().register(helloWorldResource);
        environment.healthChecks().register("template",templateHealthCheck);
    }
}
