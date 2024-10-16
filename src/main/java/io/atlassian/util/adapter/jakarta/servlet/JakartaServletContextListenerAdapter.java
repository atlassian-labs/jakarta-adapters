package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXServletContextEventAdapter;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletContextListenerAdapter implements ServletContextListener {

    private final javax.servlet.ServletContextListener delegate;

    public JakartaServletContextListenerAdapter(javax.servlet.ServletContextListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        delegate.contextInitialized(applyIfNonNull(sce, JavaXServletContextEventAdapter::new));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        delegate.contextDestroyed(applyIfNonNull(sce, JavaXServletContextEventAdapter::new));
    }
}
