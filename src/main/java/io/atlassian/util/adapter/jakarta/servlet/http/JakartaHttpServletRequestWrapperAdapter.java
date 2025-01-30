package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestWrapperAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;
import jakarta.servlet.http.HttpServletRequestWrapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JakartaHttpServletRequestWrapperAdapter extends HttpServletRequestWrapper implements Adapted<javax.servlet.http.HttpServletRequestWrapper> {

    public static HttpServletRequestWrapper from(javax.servlet.http.HttpServletRequestWrapper delegate) {
        if (delegate instanceof JavaXHttpServletRequestWrapperAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpServletRequestWrapperAdapter::new);
    }

    protected JakartaHttpServletRequestWrapperAdapter(javax.servlet.http.HttpServletRequestWrapper delegate) {
        super(new JakartaHttpServletRequestAdapter(delegate));
    }

    @Override
    public javax.servlet.http.HttpServletRequestWrapper getDelegate() {
        return (javax.servlet.http.HttpServletRequestWrapper) ((JakartaHttpServletRequestAdapter) super.getRequest()).getDelegate();
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
