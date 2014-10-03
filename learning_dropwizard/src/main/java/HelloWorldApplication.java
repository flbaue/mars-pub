/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
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
        setupSwagger(environment);
    }

    private void setupSwagger(Environment environment){
        // Swagger Resource
        environment.jersey().register(new ApiListingResourceJSON());

        // Swagger providers
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner());

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader());

        // Set the swagger config options
        SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0.1");
        config.setBasePath("http://localhost:8080");
    }
}
