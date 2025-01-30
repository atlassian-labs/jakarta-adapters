package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXServletSecurityElementAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.HttpMethodConstraintElement;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.annotation.ServletSecurity;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaServletSecurityElementAdapter extends ServletSecurityElement implements Adapted<javax.servlet.ServletSecurityElement> {

    private final javax.servlet.ServletSecurityElement delegate;

    public static ServletSecurityElement from(javax.servlet.ServletSecurityElement delegate) {
        if (delegate instanceof JavaXServletSecurityElementAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaServletSecurityElementAdapter::new);
    }

    protected JakartaServletSecurityElementAdapter(javax.servlet.ServletSecurityElement delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.ServletSecurityElement getDelegate() {
        return delegate;
    }

    @Override
    public Collection<HttpMethodConstraintElement> getHttpMethodConstraints() {
        return transformIfNonNull(delegate.getHttpMethodConstraints(), JakartaHttpMethodConstraintElementAdapter::from);
    }

    @Override
    public Collection<String> getMethodNames() {
        return delegate.getMethodNames();
    }

    @Override
    public ServletSecurity.EmptyRoleSemantic getEmptyRoleSemantic() {
        return ServletSecurity.EmptyRoleSemantic.valueOf(delegate.getEmptyRoleSemantic().name());
    }

    @Override
    public ServletSecurity.TransportGuarantee getTransportGuarantee() {
        return ServletSecurity.TransportGuarantee.valueOf(delegate.getTransportGuarantee().name());
    }

    @Override
    public String[] getRolesAllowed() {
        return delegate.getRolesAllowed();
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
