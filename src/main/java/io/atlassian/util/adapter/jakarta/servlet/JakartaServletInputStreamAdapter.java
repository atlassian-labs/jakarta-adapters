package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXReadListenerAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletInputStreamAdapter;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import java.io.IOException;
import java.io.OutputStream;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletInputStreamAdapter extends ServletInputStream implements Adapted<javax.servlet.ServletInputStream> {

    private final javax.servlet.ServletInputStream delegate;

    public static ServletInputStream from(javax.servlet.ServletInputStream delegate) {
        if (delegate instanceof JavaXServletInputStreamAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletInputStreamAdapter::new);
    }

    JakartaServletInputStreamAdapter(javax.servlet.ServletInputStream delegate) {
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

    @Override
    public void skipNBytes(long n) throws IOException {
        delegate.skipNBytes(n);
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
}
