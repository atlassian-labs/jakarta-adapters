package io.atlassian.util.adapter.javax.servlet.http;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaHttpUpgradeHandlerAdapter;
import io.atlassian.util.adapter.jakarta.servlet.http.JakartaWebConnectionAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.WebConnection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXHttpUpgradeHandlerAdapter implements HttpUpgradeHandler, Adapted<jakarta.servlet.http.HttpUpgradeHandler> {

    private final jakarta.servlet.http.HttpUpgradeHandler delegate;

    public static HttpUpgradeHandler from(jakarta.servlet.http.HttpUpgradeHandler delegate) {
        if (delegate instanceof JakartaHttpUpgradeHandlerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXHttpUpgradeHandlerAdapter::new);
    }

    protected JavaXHttpUpgradeHandlerAdapter(jakarta.servlet.http.HttpUpgradeHandler delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.http.HttpUpgradeHandler getDelegate() {
        return delegate;
    }

    @Override
    public void init(WebConnection wc) {
        delegate.init(JakartaWebConnectionAdapter.from(wc));
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
