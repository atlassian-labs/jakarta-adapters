package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.javax.servlet.http.JavaXWebConnectionAdapter;
import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.WebConnection;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaHttpUpgradeHandlerAdapter implements HttpUpgradeHandler {

    private final javax.servlet.http.HttpUpgradeHandler delegate;

    public JakartaHttpUpgradeHandlerAdapter(javax.servlet.http.HttpUpgradeHandler delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void init(WebConnection wc) {
        delegate.init(applyIfNonNull(wc, JavaXWebConnectionAdapter::new));
    }

    @Override
    public void destroy() {
        delegate.destroy();
    }
}
