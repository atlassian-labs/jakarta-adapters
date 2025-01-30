package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpUpgradeHandlerAdapter;
import io.atlassian.util.adapter.javax.servlet.http.JavaXWebConnectionAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.WebConnection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpUpgradeHandlerAdapter implements HttpUpgradeHandler, Adapted<javax.servlet.http.HttpUpgradeHandler> {

    private final javax.servlet.http.HttpUpgradeHandler delegate;

    public static HttpUpgradeHandler from(javax.servlet.http.HttpUpgradeHandler delegate) {
        if (delegate instanceof JavaXHttpUpgradeHandlerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaHttpUpgradeHandlerAdapter::new);
    }

    protected JakartaHttpUpgradeHandlerAdapter(javax.servlet.http.HttpUpgradeHandler delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.http.HttpUpgradeHandler getDelegate() {
        return delegate;
    }

    @Override
    public void init(WebConnection wc) {
        delegate.init(JavaXWebConnectionAdapter.from(wc));
    }

    @Override
    public void destroy() {
        delegate.destroy();
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
