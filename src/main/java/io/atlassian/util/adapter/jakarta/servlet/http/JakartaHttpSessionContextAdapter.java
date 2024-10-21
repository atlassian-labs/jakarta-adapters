package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpSessionContextAdapter;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpSessionContextAdapter implements HttpSessionContext {
    private final javax.servlet.http.HttpSessionContext delegate;

    public static HttpSessionContext from(javax.servlet.http.HttpSessionContext delegate) {
        if (delegate instanceof JavaXHttpSessionContextAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpSessionContextAdapter::new);
    }

    JakartaHttpSessionContextAdapter(javax.servlet.http.HttpSessionContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.http.HttpSessionContext getDelegate() {
        return delegate;
    }

    @Override
    public HttpSession getSession(String sessionId) {
        return JakartaHttpSessionAdapter.from(delegate.getSession(sessionId));
    }

    @Override
    public Enumeration<String> getIds() {
        return delegate.getIds();
    }
}
