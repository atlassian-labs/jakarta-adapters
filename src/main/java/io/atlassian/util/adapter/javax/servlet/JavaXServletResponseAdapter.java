package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletResponseAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletResponseAdapter implements ServletResponse {

    private final jakarta.servlet.ServletResponse delegate;

    public static ServletResponse from(jakarta.servlet.ServletResponse delegate) {
        if (delegate instanceof jakarta.servlet.http.HttpServletResponse castDelegate) {
            return new JavaXHttpServletResponseAdapter(castDelegate);
        }
        return applyIfNonNull(delegate, JavaXServletResponseAdapter::new);
    }

    protected JavaXServletResponseAdapter(jakarta.servlet.ServletResponse delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.ServletResponse getDelegate() {
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
        return applyIfNonNull(delegate.getOutputStream(), JavaXServletOutputStreamAdapter::new);
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