package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaAsyncEventAdapter extends AsyncEvent {

    private final javax.servlet.AsyncEvent delegate;

    public JakartaAsyncEventAdapter(javax.servlet.AsyncEvent delegate) {
        super(null, null, null);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public AsyncContext getAsyncContext() {
        return applyIfNonNull(delegate.getAsyncContext(), JakartaAsyncContextAdapter::new);
    }

    @Override
    public ServletRequest getSuppliedRequest() {
        return asJakarta(delegate.getSuppliedRequest());
    }

    @Override
    public ServletResponse getSuppliedResponse() {
        return asJakarta(delegate.getSuppliedResponse());
    }

    @Override
    public Throwable getThrowable() {
        return delegate.getThrowable();
    }
}
