package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletResponseAdapter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletResponseAdapter implements ServletResponse {

    private final javax.servlet.ServletResponse delegate;

    public static ServletResponse from(javax.servlet.ServletResponse delegate) {
        if (delegate instanceof javax.servlet.http.HttpServletResponse castDelegate) {
            return new JakartaHttpServletResponseAdapter(castDelegate);
        }
        return applyIfNonNull(delegate, JakartaServletResponseAdapter::new);
    }

    protected JakartaServletResponseAdapter(javax.servlet.ServletResponse delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.ServletResponse getDelegate() {
        return delegate;
    }

    @Override
    public String getCharacterEncoding() {
        return delegate.getCharacterEncoding();
    }

    @Override
    public String getContentType() {
        return delegate.getContentType();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return applyIfNonNull(delegate.getOutputStream(), JakartaServletOutputStreamAdapter::new);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return delegate.getWriter();
    }

    @Override
    public void setCharacterEncoding(String charset) {
        delegate.setCharacterEncoding(charset);
    }

    @Override
    public void setContentLength(int len) {
        delegate.setContentLength(len);
    }

    @Override
    public void setContentLengthLong(long len) {
        delegate.setContentLengthLong(len);
    }

    @Override
    public void setContentType(String type) {
        delegate.setContentType(type);
    }

    @Override
    public void setBufferSize(int size) {
        delegate.setBufferSize(size);
    }

    @Override
    public int getBufferSize() {
        return delegate.getBufferSize();
    }

    @Override
    public void flushBuffer() throws IOException {
        delegate.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        delegate.resetBuffer();
    }

    @Override
    public boolean isCommitted() {
        return delegate.isCommitted();
    }

    @Override
    public void reset() {
        delegate.reset();
    }

    @Override
    public void setLocale(Locale loc) {
        delegate.setLocale(loc);
    }

    @Override
    public Locale getLocale() {
        return delegate.getLocale();
    }
}