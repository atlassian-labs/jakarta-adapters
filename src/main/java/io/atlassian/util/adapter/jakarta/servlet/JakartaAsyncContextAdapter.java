package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXAsyncContextAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXAsyncListenerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaAsyncContextAdapter implements AsyncContext, Adapted<javax.servlet.AsyncContext> {

    private final javax.servlet.AsyncContext delegate;

    public static AsyncContext from(javax.servlet.AsyncContext delegate) {
        if (delegate instanceof JavaXAsyncContextAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaAsyncContextAdapter::new);
    }

    protected JakartaAsyncContextAdapter(javax.servlet.AsyncContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.AsyncContext getDelegate() {
        return delegate;
    }

    @Override
    public ServletRequest getRequest() {
        return asJakarta(delegate.getRequest());
    }

    @Override
    public ServletResponse getResponse() {
        return asJakarta(delegate.getResponse());
    }

    @Override
    public boolean hasOriginalRequestAndResponse() {
        return delegate.hasOriginalRequestAndResponse();
    }

    @Override
    public void dispatch() {
        delegate.dispatch();
    }

    @Override
    public void dispatch(String path) {
        delegate.dispatch(path);
    }

    @Override
    public void dispatch(ServletContext context, String path) {
        delegate.dispatch(asJavaX(context), path);
    }

    @Override
    public void complete() {
        delegate.complete();
    }

    @Override
    public void start(Runnable run) {
        delegate.start(run);
    }

    @Override
    public void addListener(AsyncListener listener) {
        delegate.addListener(JavaXAsyncListenerAdapter.from(listener));
    }

    @Override
    public void addListener(AsyncListener listener, ServletRequest servletRequest, ServletResponse servletResponse) {
        delegate.addListener(JavaXAsyncListenerAdapter.from(listener), asJavaX(servletRequest), asJavaX(servletResponse));
    }

    @Override
    public <T extends AsyncListener> T createListener(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) delegate.createListener((Class) clazz);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void setTimeout(long timeout) {
        delegate.setTimeout(timeout);
    }

    @Override
    public long getTimeout() {
        return delegate.getTimeout();
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
