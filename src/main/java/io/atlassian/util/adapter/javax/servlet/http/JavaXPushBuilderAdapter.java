package io.atlassian.util.adapter.javax.servlet.http;

import javax.servlet.http.PushBuilder;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXPushBuilderAdapter implements PushBuilder {

    private final jakarta.servlet.http.PushBuilder delegate;

    public JavaXPushBuilderAdapter(jakarta.servlet.http.PushBuilder delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public PushBuilder method(String method) {
        return applyIfNonNull(delegate.method(method), JavaXPushBuilderAdapter::new);
    }

    @Override
    public PushBuilder queryString(String queryString) {
        return applyIfNonNull(delegate.queryString(queryString), JavaXPushBuilderAdapter::new);
    }

    @Override
    public PushBuilder sessionId(String sessionId) {
        return applyIfNonNull(delegate.sessionId(sessionId), JavaXPushBuilderAdapter::new);
    }

    @Override
    public PushBuilder setHeader(String name, String value) {
        return applyIfNonNull(delegate.setHeader(name, value), JavaXPushBuilderAdapter::new);
    }

    @Override
    public PushBuilder addHeader(String name, String value) {
        return applyIfNonNull(delegate.addHeader(name, value), JavaXPushBuilderAdapter::new);
    }

    @Override
    public PushBuilder removeHeader(String name) {
        return applyIfNonNull(delegate.removeHeader(name), JavaXPushBuilderAdapter::new);
    }

    @Override
    public PushBuilder path(String path) {
        return applyIfNonNull(delegate.path(path), JavaXPushBuilderAdapter::new);
    }

    @Override
    public void push() {
        delegate.push();
    }

    @Override
    public String getMethod() {
        return delegate.getMethod();
    }

    @Override
    public String getQueryString() {
        return delegate.getQueryString();
    }

    @Override
    public String getSessionId() {
        return delegate.getSessionId();
    }

    @Override
    public Set<String> getHeaderNames() {
        return delegate.getHeaderNames();
    }

    @Override
    public String getHeader(String name) {
        return delegate.getHeader(name);
    }

    @Override
    public String getPath() {
        return delegate.getPath();
    }
}
