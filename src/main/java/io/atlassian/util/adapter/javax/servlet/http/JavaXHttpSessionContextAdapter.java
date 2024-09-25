package io.atlassian.util.adapter.javax.servlet.http;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpSessionContextAdapter implements HttpSessionContext {
    private final jakarta.servlet.http.HttpSessionContext delegate;

    public JavaXHttpSessionContextAdapter(jakarta.servlet.http.HttpSessionContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public HttpSession getSession(String sessionId) {
        return applyIfNonNull(delegate.getSession(sessionId), JavaXHttpSessionAdapter::new);
    }

    @Override
    public Enumeration<String> getIds() {
        return delegate.getIds();
    }
}
