package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.ServletContextEvent;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static java.util.Objects.requireNonNull;

public class JakartaServletContextEventAdapter extends ServletContextEvent {

    public JakartaServletContextEventAdapter(javax.servlet.ServletContextEvent delegate) {
        super(asJakarta(requireNonNull(delegate).getServletContext()));
    }
}
