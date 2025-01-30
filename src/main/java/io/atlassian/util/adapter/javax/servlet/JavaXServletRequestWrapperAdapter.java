package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestWrapperAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestWrapperAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXServletRequestWrapperAdapter extends ServletRequestWrapper implements Adapted<jakarta.servlet.ServletRequestWrapper> {

    public static ServletRequestWrapper from(jakarta.servlet.ServletRequestWrapper delegate) {
        if (delegate instanceof jakarta.servlet.http.HttpServletRequestWrapper castDelegate) {
            return JavaXHttpServletRequestWrapperAdapter.from(castDelegate);
        }
        if (delegate instanceof JakartaServletRequestWrapperAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletRequestWrapperAdapter::new);
    }

    protected JavaXServletRequestWrapperAdapter(jakarta.servlet.ServletRequestWrapper delegate) {
        super(new JavaXServletRequestAdapter(delegate));
    }

    @Override
    public jakarta.servlet.ServletRequestWrapper getDelegate() {
        return (jakarta.servlet.ServletRequestWrapper) ((JavaXServletRequestAdapter) super.getRequest()).getDelegate();
    }

    @Override
    public boolean isWrapperFor(Class<?> wrappedType) {
        if (getRequest() instanceof ServletRequestWrapper wrapper) {
            return wrapper.isWrapperFor(wrappedType);
        }
        return false;
    }

    @Override
    public boolean isWrapperFor(ServletRequest wrapped) {
        if (getRequest() instanceof ServletRequestWrapper wrapper) {
            return wrapper.isWrapperFor(wrapped);
        }
        return false;
    }

    @Override
    public ServletRequest getRequest() {
        return JavaXServletRequestAdapter.from(getDelegate().getRequest());
    }

    @Override
    public void setRequest(ServletRequest request) {
        getDelegate().setRequest(JakartaServletRequestAdapter.from(request));
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
