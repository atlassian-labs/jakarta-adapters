package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXAsyncListenerAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletContextAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletResponseAdapter;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaAsyncContextAdapter implements AsyncContext {

    private final javax.servlet.AsyncContext delegate;

    public JakartaAsyncContextAdapter(javax.servlet.AsyncContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ServletRequest getRequest() {
        return JakartaServletRequestAdapter.from(delegate.getRequest());
    }

    @Override
    public ServletResponse getResponse() {
        return JakartaServletResponseAdapter.from(delegate.getResponse());
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
        delegate.dispatch(applyIfNonNull(context, JavaXServletContextAdapter::new), path);
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
        delegate.addListener(applyIfNonNull(listener, JavaXAsyncListenerAdapter::new));
    }

    @Override
    public void addListener(AsyncListener listener, ServletRequest servletRequest, ServletResponse servletResponse) {
        delegate.addListener(
                applyIfNonNull(listener, JavaXAsyncListenerAdapter::new),
                JavaXServletRequestAdapter.from(servletRequest),
                JavaXServletResponseAdapter.from(servletResponse));
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
}
