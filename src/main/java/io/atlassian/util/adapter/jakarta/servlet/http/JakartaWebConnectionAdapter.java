package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletInputStreamAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletOutputStreamAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXWebConnectionAdapter;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.WebConnection;

import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaWebConnectionAdapter implements WebConnection, Adapted<javax.servlet.http.WebConnection> {
    private final javax.servlet.http.WebConnection delegate;

    public static WebConnection from(javax.servlet.http.WebConnection delegate) {
        if (delegate instanceof JavaXWebConnectionAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaWebConnectionAdapter::new);
    }

    protected JakartaWebConnectionAdapter(javax.servlet.http.WebConnection delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.WebConnection getDelegate() {
        return delegate;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return JakartaServletInputStreamAdapter.from(delegate.getInputStream());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return JakartaServletOutputStreamAdapter.from(delegate.getOutputStream());
    }

    @Override
    public void close() throws Exception {
        delegate.close();
    }
}
