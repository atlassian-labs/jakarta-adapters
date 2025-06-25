package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletRequestWrapperAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestWrapperAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaServletRequestWrapperAdapter extends ServletRequestWrapper implements Adapted<javax.servlet.ServletRequestWrapper> {

    public static ServletRequestWrapper from(javax.servlet.ServletRequestWrapper delegate) {
        if (delegate instanceof javax.servlet.http.HttpServletRequestWrapper) {
            javax.servlet.http.HttpServletRequestWrapper castDelegate = (javax.servlet.http.HttpServletRequestWrapper) delegate;
            return JakartaHttpServletRequestWrapperAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXServletRequestWrapperAdapter) {
            JavaXServletRequestWrapperAdapter castDelegate = (JavaXServletRequestWrapperAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletRequestWrapperAdapter::new);
    }

    protected JakartaServletRequestWrapperAdapter(javax.servlet.ServletRequestWrapper delegate) {
        super(new JakartaServletRequestAdapter(delegate));
    }

    @Override
    public javax.servlet.ServletRequestWrapper getDelegate() {
        return (javax.servlet.ServletRequestWrapper) ((JakartaServletRequestAdapter) super.getRequest()).getDelegate();
    }

    @Override
    public boolean isWrapperFor(Class<?> wrappedType) {
        if (getRequest() instanceof ServletRequestWrapper) {
            ServletRequestWrapper wrapper = (ServletRequestWrapper) getRequest();
            return wrapper.isWrapperFor(wrappedType);
        }
        return false;
    }

    @Override
    public boolean isWrapperFor(ServletRequest wrapped) {
        if (getRequest() instanceof ServletRequestWrapper) {
            ServletRequestWrapper wrapper = (ServletRequestWrapper) getRequest();
            return wrapper.isWrapperFor(wrapped);
        }
        return false;
    }

    @Override
    public ServletRequest getRequest() {
        return JakartaServletRequestAdapter.from(getDelegate().getRequest());
    }

    @Override
    public void setRequest(ServletRequest request) {
        getDelegate().setRequest(JavaXServletRequestAdapter.from(request));
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
