package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaAsyncListenerAdapter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXAsyncContextAdapter implements AsyncContext {

    private final jakarta.servlet.AsyncContext delegate;

    public JavaXAsyncContextAdapter(jakarta.servlet.AsyncContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ServletRequest getRequest() {
        return asJavaX(delegate.getRequest());
    }

    @Override
    public ServletResponse getResponse() {
        return asJavaX(delegate.getResponse());
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
        delegate.dispatch(asJakarta(context), path);
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
        delegate.addListener(applyIfNonNull(listener, JakartaAsyncListenerAdapter::new));
    }

    @Override
    public void addListener(AsyncListener listener, ServletRequest servletRequest, ServletResponse servletResponse) {
        delegate.addListener(applyIfNonNull(listener, JakartaAsyncListenerAdapter::new), asJakarta(servletRequest), asJakarta(servletResponse));
    }

    @Override
    public <T extends AsyncListener> T createListener(Class<T> clazz) throws ServletException {
        try {
            // ClassCastException likely
            return (T) delegate.createListener((Class) clazz);
        } catch (jakarta.servlet.ServletException e) {
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
