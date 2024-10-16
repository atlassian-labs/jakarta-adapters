package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletContextEventAdapter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletContextListenerAdapter implements ServletContextListener {

    private final jakarta.servlet.ServletContextListener delegate;

    public JavaXServletContextListenerAdapter(jakarta.servlet.ServletContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        delegate.contextInitialized(applyIfNonNull(sce, JakartaServletContextEventAdapter::new));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        delegate.contextDestroyed(applyIfNonNull(sce, JakartaServletContextEventAdapter::new));
    }
}
