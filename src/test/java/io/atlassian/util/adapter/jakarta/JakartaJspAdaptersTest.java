package io.atlassian.util.adapter.jakarta;

import org.junit.jupiter.api.Test;

import javax.servlet.jsp.JspFactory;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JakartaJspAdaptersTest {

    @Test
    void jspFactory() {
        var delegate = mock(JspFactory.class);
        var pageContext = mock(jakarta.servlet.jsp.PageContext.class);
        when(pageContext.getPage()).thenReturn("foo");

        var proxy = asJakartaJsp(delegate);
        proxy.releasePageContext(pageContext);

        verify(delegate).releasePageContext(argThat(pC -> pC.getPage().equals("foo")));
    }
}
