package org.pac4j.jax.rs;

import java.util.Set;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.assertj.core.util.Sets;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.pac4j.core.config.Config;
import org.pac4j.jax.rs.features.Pac4JSecurityFeature;
import org.pac4j.jax.rs.servlet.features.ServletJaxRsContextFactoryProvider;

import io.undertow.server.session.SessionCookieConfig;

/**
 *
 * @author Victor Noel - Linagora
 * @since 1.0.0
 *
 */
public class RestEasyUndertowServletTest extends AbstractSessionTest {

    private UndertowJaxrsServer server;

    public class MyApp extends Application {
        @Override
        public Set<Class<?>> getClasses() {
            return getResources();
        }

        @Override
        public Set<Object> getSingletons() {
            final Config config = getConfig();
            return Sets.newLinkedHashSet(new ServletJaxRsContextFactoryProvider(config),
                    new Pac4JSecurityFeature(config, DEFAULT_CLIENT));
        }
    }

    @Before
    public void setUp() {
        // TODO use an autogenerated port...
        System.setProperty("org.jboss.resteasy.port", "24257");
        server = new UndertowJaxrsServer().start();

        server.deploy(new MyApp());
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Override
    protected WebTarget getTarget(String url) {
        return new ResteasyClientBuilder().build().target(TestPortProvider.generateURL(url));
    }

    @Override
    protected String cookieName() {
        return SessionCookieConfig.DEFAULT_SESSION_ID;
    }

    // TODO we don't have injection yet for something else than Jersey!
    @Ignore
    @Override
    public void testInject() {
        // do nothing
    }

    // TODO we don't have injection yet for something else than Jersey!
    @Ignore
    @Override
    public void directInject() {
        // do nothing
    }

    // TODO we don't have injection yet for something else than Jersey!
    @Ignore
    @Override
    public void directInjectSkipFail() {
        // do nothing
    }

    // TODO we don't have injection yet for something else than Jersey!
    @Ignore
    @Override
    public void directInjectSkipOk() {
        // do nothing
    }

    // TODO we don't have injection yet for something else than Jersey!
    @Ignore
    @Override
    public void directInjectManagerAuth() {
        // do nothing
    }

    // TODO we don't have injection yet for something else than Jersey!
    @Ignore
    @Override
    public void directInjectManagerNoAuth() {
        // do nothing
    }
}
