package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.jsp.tagext.JakartaBodyContentAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspWriterAdapter;
import jakarta.servlet.jsp.JspWriter;

import java.io.IOException;
import java.io.Writer;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaJspWriterAdapter extends JspWriter implements Adapted<javax.servlet.jsp.JspWriter> {

    private final javax.servlet.jsp.JspWriter delegate;

    public static JspWriter from(javax.servlet.jsp.JspWriter delegate) {
        if (delegate instanceof javax.servlet.jsp.tagext.BodyContent castDelegate) {
            return JakartaBodyContentAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXJspWriterAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaJspWriterAdapter::new);
    }

    protected JakartaJspWriterAdapter(javax.servlet.jsp.JspWriter delegate) {
        super(0, false);
        this.delegate = delegate;
    }

    @Override
    public javax.servlet.jsp.JspWriter getDelegate() {
        return delegate;
    }

    @Override
    public int getBufferSize() {
        return delegate.getBufferSize();
    }

    @Override
    public boolean isAutoFlush() {
        return delegate.isAutoFlush();
    }

    @Override
    public void newLine() throws IOException {
        delegate.newLine();
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
    public void print(char[] s) throws IOException {
        delegate.print(s);
    }

    @Override
    public void print(String s) throws IOException {
        delegate.print(s);
    }

    @Override
    public void print(Object obj) throws IOException {
        delegate.print(obj);
    }

    @Override
    public void println() throws IOException {
        delegate.println();
    }

    @Override
    public void println(boolean x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(char x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(int x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(long x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(float x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(double x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(char[] x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(String x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void println(Object x) throws IOException {
        delegate.println(x);
    }

    @Override
    public void clear() throws IOException {
        delegate.clear();
    }

    @Override
    public void clearBuffer() throws IOException {
        delegate.clearBuffer();
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
    public int getRemaining() {
        return delegate.getRemaining();
    }

    @Override
    public void write(int c) throws IOException {
        delegate.write(c);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        delegate.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        delegate.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        delegate.write(str, off, len);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return delegate.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return delegate.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return delegate.append(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        delegate.write(cbuf, off, len);
    }
}
