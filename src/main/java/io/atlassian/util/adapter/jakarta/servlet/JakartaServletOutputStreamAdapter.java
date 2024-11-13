package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXServletOutputStreamAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXWriteListenerAdapter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletOutputStreamAdapter extends ServletOutputStream implements Adapted<javax.servlet.ServletOutputStream> {

    private final javax.servlet.ServletOutputStream delegate;

    public static ServletOutputStream from(javax.servlet.ServletOutputStream delegate) {
        if (delegate instanceof JavaXServletOutputStreamAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletOutputStreamAdapter::new);
    }

    protected JakartaServletOutputStreamAdapter(javax.servlet.ServletOutputStream delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.ServletOutputStream getDelegate() {
        return delegate;
    }

    @Override
    public void print(String s) throws IOException {
        delegate.print(s);
    }

    @Override
    public void print(boolean b) throws IOException {
        delegate.print(b);
    }

    @Override
    public void print(char c) throws IOException {
        delegate.print(c);
    }

    @Override
    public void print(int i) throws IOException {
        delegate.print(i);
    }

    @Override
    public void print(long l) throws IOException {
        delegate.print(l);
    }

    @Override
    public void print(float f) throws IOException {
        delegate.print(f);
    }

    @Override
    public void print(double d) throws IOException {
        delegate.print(d);
    }

    @Override
    public void println() throws IOException {
        delegate.println();
    }

    @Override
    public void println(String s) throws IOException {
        delegate.println(s);
    }

    @Override
    public void println(boolean b) throws IOException {
        delegate.println(b);
    }

    @Override
    public void println(char c) throws IOException {
        delegate.println(c);
    }

    @Override
    public void println(int i) throws IOException {
        delegate.println(i);
    }

    @Override
    public void println(long l) throws IOException {
        delegate.println(l);
    }

    @Override
    public void println(float f) throws IOException {
        delegate.println(f);
    }

    @Override
    public void println(double d) throws IOException {
        delegate.println(d);
    }

    @Override
    public void write(byte[] b) throws IOException {
        delegate.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        delegate.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        delegate.flush();
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

    @Override
    public boolean isReady() {
        return delegate.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        delegate.setWriteListener(JavaXWriteListenerAdapter.from(writeListener));
    }

    @Override
    public void write(int b) throws IOException {
        delegate.write(b);
    }
}
