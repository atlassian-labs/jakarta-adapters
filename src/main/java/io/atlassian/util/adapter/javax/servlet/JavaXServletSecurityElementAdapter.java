package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaServletSecurityElementAdapter;

import javax.servlet.HttpMethodConstraintElement;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity;
import java.util.Collection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXServletSecurityElementAdapter extends ServletSecurityElement implements Adapted<jakarta.servlet.ServletSecurityElement> {

    private final jakarta.servlet.ServletSecurityElement delegate;

    public static ServletSecurityElement from(jakarta.servlet.ServletSecurityElement delegate) {
        if (delegate instanceof JakartaServletSecurityElementAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXServletSecurityElementAdapter::new);
    }

    JavaXServletSecurityElementAdapter(jakarta.servlet.ServletSecurityElement delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.ServletSecurityElement getDelegate() {
        return delegate;
    }

    @Override
    public Collection<HttpMethodConstraintElement> getHttpMethodConstraints() {
        return transformIfNonNull(delegate.getHttpMethodConstraints(), JavaXHttpMethodConstraintElementAdapter::from);
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
}
