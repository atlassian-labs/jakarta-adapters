package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaRegistrationAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.Registration;
import java.util.Map;
import java.util.Set;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXRegistrationAdapter implements Registration, Adapted<jakarta.servlet.Registration> {

    private final jakarta.servlet.Registration delegate;

    public static Registration from(jakarta.servlet.Registration delegate) {
        if (delegate instanceof jakarta.servlet.ServletRegistration castDelegate) {
            return JavaXServletRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof jakarta.servlet.FilterRegistration castDelegate) {
            return JavaXFilterRegistrationAdapter.from(castDelegate);
        }
        if (delegate instanceof JakartaRegistrationAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXRegistrationAdapter::new);
    }

    protected JavaXRegistrationAdapter(jakarta.servlet.Registration delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.Registration getDelegate() {
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
