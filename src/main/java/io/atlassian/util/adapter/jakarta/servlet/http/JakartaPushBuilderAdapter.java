package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.http.JavaXPushBuilderAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.http.PushBuilder;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaPushBuilderAdapter implements PushBuilder, Adapted<javax.servlet.http.PushBuilder> {

    private final javax.servlet.http.PushBuilder delegate;

    public static PushBuilder from(javax.servlet.http.PushBuilder delegate) {
        if (delegate instanceof JavaXPushBuilderAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaPushBuilderAdapter::new);
    }

    protected JakartaPushBuilderAdapter(javax.servlet.http.PushBuilder delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.PushBuilder getDelegate() {
        return delegate;
    }

    @Override
    public PushBuilder method(String method) {
        return JakartaPushBuilderAdapter.from(delegate.method(method));
    }

    @Override
    public PushBuilder queryString(String queryString) {
        return JakartaPushBuilderAdapter.from(delegate.queryString(queryString));
    }

    @Override
    public PushBuilder sessionId(String sessionId) {
        return JakartaPushBuilderAdapter.from(delegate.sessionId(sessionId));
    }

    @Override
    public PushBuilder setHeader(String name, String value) {
        return JakartaPushBuilderAdapter.from(delegate.setHeader(name, value));
    }

    @Override
    public PushBuilder addHeader(String name, String value) {
        return JakartaPushBuilderAdapter.from(delegate.addHeader(name, value));
    }

    @Override
    public PushBuilder removeHeader(String name) {
        return JakartaPushBuilderAdapter.from(delegate.removeHeader(name));
    }

    @Override
    public PushBuilder path(String path) {
        return JakartaPushBuilderAdapter.from(delegate.path(path));
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

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
