package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.JakartaServletInputStreamAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletOutputStreamAdapter;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.WebConnection;

import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaWebConnectionAdapter implements WebConnection {
    private final javax.servlet.http.WebConnection delegate;

    public JakartaWebConnectionAdapter(javax.servlet.http.WebConnection delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return applyIfNonNull(delegate.getInputStream(), JakartaServletInputStreamAdapter::new);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return applyIfNonNull(delegate.getOutputStream(), JakartaServletOutputStreamAdapter::new);
    }

    @Override
    public void close() throws Exception {
        delegate.close();
    }
}
