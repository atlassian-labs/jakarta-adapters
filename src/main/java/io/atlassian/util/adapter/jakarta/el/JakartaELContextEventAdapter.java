package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.ELContextEvent;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;

public class JakartaELContextEventAdapter extends ELContextEvent {

    public JakartaELContextEventAdapter(javax.el.ELContextEvent delegate) {
        super(asJakartaJsp(delegate.getELContext()));
    }
}
