package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.jakarta.servlet.http.JakartaWebConnectionAdapter;
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

    public static WebConnection from(jakarta.servlet.http.WebConnection delegate) {
        if (delegate instanceof JakartaWebConnectionAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXWebConnectionAdapter::new);
    }

    JavaXWebConnectionAdapter(jakarta.servlet.http.WebConnection delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.http.WebConnection getDelegate() {
        return delegate;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return JavaXServletInputStreamAdapter.from(delegate.getInputStream());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return JavaXServletOutputStreamAdapter.from(delegate.getOutputStream());
    }

    @Override
    public void close() throws Exception {
        delegate.close();
    }
}
