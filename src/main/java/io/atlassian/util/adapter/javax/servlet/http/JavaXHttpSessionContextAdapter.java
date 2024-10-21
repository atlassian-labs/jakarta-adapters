package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpSessionContextAdapter;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpSessionContextAdapter implements HttpSessionContext, Adapted<jakarta.servlet.http.HttpSessionContext> {
    private final jakarta.servlet.http.HttpSessionContext delegate;

    public static HttpSessionContext from(jakarta.servlet.http.HttpSessionContext delegate) {
        if (delegate instanceof JakartaHttpSessionContextAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpSessionContextAdapter::new);
    }

    JavaXHttpSessionContextAdapter(jakarta.servlet.http.HttpSessionContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.http.HttpSessionContext getDelegate() {
        return delegate;
    }

    @Override
    public HttpSession getSession(String sessionId) {
        return JavaXHttpSessionAdapter.from(delegate.getSession(sessionId));
    }

    @Override
    public Enumeration<String> getIds() {
        return delegate.getIds();
    }
}
