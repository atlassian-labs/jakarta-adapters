package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXRegistrationAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.Registration;
import java.util.Map;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaRegistrationAdapter implements Registration, Adapted<javax.servlet.Registration> {

    private final javax.servlet.Registration delegate;

    public static Registration from(javax.servlet.Registration delegate) {
        if (delegate instanceof javax.servlet.ServletRegistration) {
            javax.servlet.ServletRegistration castDelegate = (javax.servlet.ServletRegistration) delegate;
            return JakartaServletRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof javax.servlet.FilterRegistration) {
            javax.servlet.FilterRegistration castDelegate = (javax.servlet.FilterRegistration) delegate;
            return JakartaFilterRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof JavaXRegistrationAdapter) {
            JavaXRegistrationAdapter castDelegate = (JavaXRegistrationAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaRegistrationAdapter::new);
    }

    protected JakartaRegistrationAdapter(javax.servlet.Registration delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.Registration getDelegate() {
        return delegate;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public String getClassName() {
        return delegate.getClassName();
    }

    @Override
    public boolean setInitParameter(String name, String value) {
        return delegate.setInitParameter(name, value);
    }

    @Override
    public String getInitParameter(String name) {
        return delegate.getInitParameter(name);
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> initParameters) {
        return delegate.setInitParameters(initParameters);
    }

    @Override
    public Map<String, String> getInitParameters() {
        return delegate.getInitParameters();
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
