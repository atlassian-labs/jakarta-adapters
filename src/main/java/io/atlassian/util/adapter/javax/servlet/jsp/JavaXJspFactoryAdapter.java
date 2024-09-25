package io.atlassian.util.adapter.javax.servlet.jsp;

import io.atlassian.util.adapter.jakarta.servlet.jsp.JakartaPageContextAdapter;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXJspFactoryAdapter extends JspFactory {
    private final jakarta.servlet.jsp.JspFactory delegate;

    public JavaXJspFactoryAdapter(jakarta.servlet.jsp.JspFactory delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public PageContext getPageContext(Servlet servlet,
                                      ServletRequest servletRequest,
                                      ServletResponse servletResponse,
                                      String s,
                                      boolean b,
                                      int i,
                                      boolean b1) {
        return applyIfNonNull(
                delegate.getPageContext(asJakarta(servlet), asJakarta(servletRequest), asJakarta(servletResponse), s, b, i, b1),
                JavaXPageContextAdapter::new);
    }

    @Override
    public void releasePageContext(PageContext pageContext) {
        delegate.releasePageContext(applyIfNonNull(pageContext, JakartaPageContextAdapter::new));
    }

    @Override
    public JspEngineInfo getEngineInfo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JspApplicationContext getJspApplicationContext(ServletContext servletContext) {
        throw new UnsupportedOperationException();
    }
}
