package io.atlassian.util.adapter.jakarta.servlet.http;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpSessionContextAdapter implements HttpSessionContext {
    private final javax.servlet.http.HttpSessionContext delegate;

    public JakartaHttpSessionContextAdapter(javax.servlet.http.HttpSessionContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public HttpSession getSession(String sessionId) {
        return applyIfNonNull(delegate.getSession(sessionId), JakartaHttpSessionAdapter::new);
    }

    @Override
    public Enumeration<String> getIds() {
        return delegate.getIds();
    }
}
