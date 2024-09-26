package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.javax.servlet.jsp.JavaXJspFactoryAdapter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.jsp.JspFactory;
import jakarta.servlet.jsp.PageContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaJspFactoryAdapterTest {

    @Mock
    private JspFactory original;

    private JspFactory biAdapted;

    @BeforeEach
    void setUp() throws Exception {
        biAdapted = new JakartaJspFactoryAdapter(new JavaXJspFactoryAdapter(original));
    }

    @Test
    void getPageContext() {
        var servlet = mock(Servlet.class);
        var servletRequest = mock(ServletRequest.class);
        var servletResponse = mock(ServletResponse.class);
        when(servlet.getServletInfo()).thenReturn("foo");
        when(servletRequest.getRemoteAddr()).thenReturn("foo");
        when(servletResponse.getCharacterEncoding()).thenReturn("foo");

        var pageContext = mock(PageContext.class);
        when(pageContext.getPage()).thenReturn("res");
        when(original
                .getPageContext(
                        argThat(s -> s.getServletInfo().equals("foo")),
                        argThat(r -> r.getRemoteAddr().equals("foo")),
                        argThat(r -> r.getCharacterEncoding().equals("foo")),
                        eq("foo"), eq(false), eq(0), eq(false)))
                .thenReturn(pageContext);

        assertEquals("res",
                biAdapted.getPageContext(servlet, servletRequest, servletResponse, "foo", false, 0, false).getPage());
    }

    @Test
    void releasePageContext() {
        var pageContext = mock(PageContext.class);
        when(pageContext.getPage()).thenReturn("foo");

        biAdapted.releasePageContext(pageContext);

        verify(original).releasePageContext(argThat(pC -> pC.getPage().equals("foo")));
    }

    @Test
    void getEngineInfo() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.getEngineInfo());
    }

    @Test
    void getJspApplicationContext() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.getJspApplicationContext(null));
    }
}
