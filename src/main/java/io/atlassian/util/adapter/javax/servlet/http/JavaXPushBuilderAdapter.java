package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaPushBuilderAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.http.PushBuilder;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXPushBuilderAdapter implements PushBuilder, Adapted<jakarta.servlet.http.PushBuilder> {

    private final jakarta.servlet.http.PushBuilder delegate;

    public static PushBuilder from(jakarta.servlet.http.PushBuilder delegate) {
        if (delegate instanceof JakartaPushBuilderAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXPushBuilderAdapter::new);
    }

    protected JavaXPushBuilderAdapter(jakarta.servlet.http.PushBuilder delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.http.PushBuilder getDelegate() {
        return delegate;
    }

    @Override
    public PushBuilder method(String method) {
        return JavaXPushBuilderAdapter.from(delegate.method(method));
    }

    @Override
    public PushBuilder queryString(String queryString) {
        return JavaXPushBuilderAdapter.from(delegate.queryString(queryString));
    }

    @Override
    public PushBuilder sessionId(String sessionId) {
        return JavaXPushBuilderAdapter.from(delegate.sessionId(sessionId));
    }

    @Override
    public PushBuilder setHeader(String name, String value) {
        return JavaXPushBuilderAdapter.from(delegate.setHeader(name, value));
    }

    @Override
    public PushBuilder addHeader(String name, String value) {
        return JavaXPushBuilderAdapter.from(delegate.addHeader(name, value));
    }

    @Override
    public PushBuilder removeHeader(String name) {
        return JavaXPushBuilderAdapter.from(delegate.removeHeader(name));
    }

    @Override
    public PushBuilder path(String path) {
        return JavaXPushBuilderAdapter.from(delegate.path(path));
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
