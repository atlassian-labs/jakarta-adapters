package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.javax.servlet.JavaXServletInputStreamAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletOutputStreamAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.WebConnection;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXWebConnectionAdapter implements WebConnection {
    private final jakarta.servlet.http.WebConnection delegate;

    public JavaXWebConnectionAdapter(jakarta.servlet.http.WebConnection delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return applyIfNonNull(delegate.getInputStream(), JavaXServletInputStreamAdapter::new);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return applyIfNonNull(delegate.getOutputStream(), JavaXServletOutputStreamAdapter::new);
    }

    @Override
    public void close() throws Exception {
        delegate.close();
    }
}
