package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXReadListenerAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletInputStreamAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletInputStreamAdapter extends ServletInputStream implements Adapted<javax.servlet.ServletInputStream> {

    private final javax.servlet.ServletInputStream delegate;

    public static ServletInputStream from(javax.servlet.ServletInputStream delegate) {
        if (delegate instanceof JavaXServletInputStreamAdapter) {
            JavaXServletInputStreamAdapter castDelegate = (JavaXServletInputStreamAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletInputStreamAdapter::new);
    }

    protected JakartaServletInputStreamAdapter(javax.servlet.ServletInputStream delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.ServletInputStream getDelegate() {
        return delegate;
    }

    @Override
    public int readLine(byte[] b, int off, int len) throws IOException {
        return delegate.readLine(b, off, len);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return delegate.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return delegate.read(b, off, len);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return delegate.readAllBytes();
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        return delegate.readNBytes(len);
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        return delegate.readNBytes(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return delegate.skip(n);
    }

    // @Override Language level 12+
    public void skipNBytes(long n) throws IOException {
        reflectiveSkipNBytes(delegate, n);
    }

    public static void reflectiveSkipNBytes(InputStream delegate, long n) throws IOException {
        try {
            Method skipNBytes = InputStream.class.getMethod("skipNBytes", long.class);
            skipNBytes.invoke(delegate, n);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("Could not find or invoke skipNBytes on delegate stream.", e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            } else if (e.getCause() instanceof IOException) {
                throw (IOException) e.getCause();
            } else {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    @Override
    public int available() throws IOException {
        return delegate.available();
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        delegate.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        delegate.reset();
    }

    @Override
    public boolean markSupported() {
        return delegate.markSupported();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        return delegate.transferTo(out);
    }

    @Override
    public boolean isFinished() {
        return delegate.isFinished();
    }

    @Override
    public boolean isReady() {
        return delegate.isReady();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        delegate.setReadListener(JavaXReadListenerAdapter.from(readListener));
    }

    @Override
    public int read() throws IOException {
        return delegate.read();
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
