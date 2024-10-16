package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.ServletContextEvent;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static java.util.Objects.requireNonNull;

public class JavaXServletContextEventAdapter extends ServletContextEvent {

    public JavaXServletContextEventAdapter(jakarta.servlet.ServletContextEvent delegate) {
        super(asJavaX(requireNonNull(delegate).getServletContext()));
    }
}
