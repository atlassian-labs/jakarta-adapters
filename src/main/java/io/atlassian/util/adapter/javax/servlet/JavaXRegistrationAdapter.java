package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.Registration;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class JavaXRegistrationAdapter implements Registration {

    private final jakarta.servlet.Registration delegate;

    public JavaXRegistrationAdapter(jakarta.servlet.Registration delegate) {
        this.delegate = requireNonNull(delegate);
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
}
