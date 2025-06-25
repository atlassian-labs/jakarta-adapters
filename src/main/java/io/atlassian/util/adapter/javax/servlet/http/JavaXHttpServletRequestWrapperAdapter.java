package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletRequestAdapter;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpServletRequestWrapperAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXServletRequestAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequestWrapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;

public class JavaXHttpServletRequestWrapperAdapter extends HttpServletRequestWrapper implements Adapted<jakarta.servlet.http.HttpServletRequestWrapper> {

    public static HttpServletRequestWrapper from(jakarta.servlet.http.HttpServletRequestWrapper delegate) {
        if (delegate instanceof JakartaHttpServletRequestWrapperAdapter) {
            JakartaHttpServletRequestWrapperAdapter castDelegate = (JakartaHttpServletRequestWrapperAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpServletRequestWrapperAdapter::new);
    }

    protected JavaXHttpServletRequestWrapperAdapter(jakarta.servlet.http.HttpServletRequestWrapper request) {
        super(new JavaXHttpServletRequestAdapter(request));
    }

    @Override
    public jakarta.servlet.http.HttpServletRequestWrapper getDelegate() {
        return (jakarta.servlet.http.HttpServletRequestWrapper) ((JavaXHttpServletRequestAdapter) super.getRequest()).getDelegate();
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
