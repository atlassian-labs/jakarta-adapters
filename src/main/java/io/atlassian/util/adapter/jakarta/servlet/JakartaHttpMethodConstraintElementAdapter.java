package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXHttpMethodConstraintElementAdapter;
import jakarta.servlet.HttpMethodConstraintElement;
import jakarta.servlet.annotation.ServletSecurity;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpMethodConstraintElementAdapter extends HttpMethodConstraintElement implements Adapted<javax.servlet.HttpMethodConstraintElement> {

    private final javax.servlet.HttpMethodConstraintElement delegate;

    public static HttpMethodConstraintElement from(javax.servlet.HttpMethodConstraintElement delegate) {
        if (delegate instanceof JavaXHttpMethodConstraintElementAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpMethodConstraintElementAdapter::new);
    }

    protected JakartaHttpMethodConstraintElementAdapter(javax.servlet.HttpMethodConstraintElement delegate) {
        super("null");
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.HttpMethodConstraintElement getDelegate() {
        return delegate;
    }

    @Override
    public String getMethodName() {
        return delegate.getMethodName();
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
