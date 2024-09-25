package io.atlassian.util.adapter.jakarta.servlet.http;

import io.atlassian.util.adapter.javax.servlet.http.JavaXHttpServletRequestAdapter;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.PushBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JakartaHttpServletRequestAdapterTest {

    @Mock
    private HttpServletRequest originalRequest;

    private HttpServletRequest biAdaptedRequest;

    @BeforeEach
    public void setUp() throws Exception {
        biAdaptedRequest = new JakartaHttpServletRequestAdapter(new JavaXHttpServletRequestAdapter(originalRequest));
    }

    @Test
    public void getAttribute() {
        when(originalRequest.getAttribute("test")).thenReturn("value");

        assertEquals("value", biAdaptedRequest.getAttribute("test"));
    }

    @Test
    public void getAttributeNames() {
        var retValue = mock(Enumeration.class);
        when(originalRequest.getAttributeNames()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getAttributeNames());
    }

    @Test
    public void getCharacterEncoding() {
        when(originalRequest.getCharacterEncoding()).thenReturn("UTF-8");

        assertEquals("UTF-8", biAdaptedRequest.getCharacterEncoding());
    }

    @Test
    public void setCharacterEncoding() throws Exception {
        biAdaptedRequest.setCharacterEncoding("UTF-8");

        verify(originalRequest).setCharacterEncoding("UTF-8");
    }

    @Test
    public void getContentLength() {
        when(originalRequest.getContentLength()).thenReturn(10);

        assertEquals(10, biAdaptedRequest.getContentLength());
    }

    @Test
    public void getContentLengthLong() {
        when(originalRequest.getContentLengthLong()).thenReturn(10L);

        assertEquals(10L, biAdaptedRequest.getContentLengthLong());
    }

    @Test
    public void getContentType() {
        when(originalRequest.getContentType()).thenReturn("text/plain");

        assertEquals("text/plain", biAdaptedRequest.getContentType());
    }

    @Test
    public void getInputStream() throws Exception {
        var inputStream = mock(ServletInputStream.class);
        when(inputStream.read()).thenReturn(-1);
        when(originalRequest.getInputStream()).thenReturn(inputStream);

        assertEquals(-1, biAdaptedRequest.getInputStream().read());
    }

    @Test
    public void getParameter() {
        when(originalRequest.getParameter("test")).thenReturn("value");

        assertEquals("value", biAdaptedRequest.getParameter("test"));
    }

    @Test
    public void getParameterNames() {
        var retValue = mock(Enumeration.class);
        when(originalRequest.getParameterNames()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getParameterNames());
    }

    @Test
    public void getParameterValues() {
        when(originalRequest.getParameterValues("test")).thenReturn(new String[]{"value"});

        assertArrayEquals(new String[]{"value"}, biAdaptedRequest.getParameterValues("test"));
    }

    @Test
    public void getParameterMap() {
        when(originalRequest.getParameterMap()).thenReturn(Map.of("test", new String[]{"value"}));

        assertEquals("value", biAdaptedRequest.getParameterMap().get("test")[0]);
    }

    @Test
    public void getProtocol() {
        when(originalRequest.getProtocol()).thenReturn("HTTP/1.1");

        assertEquals("HTTP/1.1", biAdaptedRequest.getProtocol());
    }

    @Test
    public void getScheme() {
        when(originalRequest.getScheme()).thenReturn("http");

        assertEquals("http", biAdaptedRequest.getScheme());
    }

    @Test
    public void getServerName() {
        when(originalRequest.getServerName()).thenReturn("localhost");

        assertEquals("localhost", biAdaptedRequest.getServerName());
    }

    @Test
    public void getServerPort() {
        when(originalRequest.getServerPort()).thenReturn(8080);

        assertEquals(8080, biAdaptedRequest.getServerPort());
    }

    @Test
    public void getReader() throws Exception {
        var retValue = mock(BufferedReader.class);
        when(originalRequest.getReader()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getReader());
    }

    @Test
    public void getRemoteAddr() {
        when(originalRequest.getRemoteAddr()).thenReturn("127.0.0.1");

        assertEquals("127.0.0.1", biAdaptedRequest.getRemoteAddr());
    }

    @Test
    public void getRemoteHost() {
        when(originalRequest.getRemoteHost()).thenReturn("localhost");

        assertEquals("localhost", biAdaptedRequest.getRemoteHost());
    }

    @Test
    public void setAttribute() {
        biAdaptedRequest.setAttribute("test", "value");

        verify(originalRequest).setAttribute("test", "value");
    }

    @Test
    public void removeAttribute() {
        biAdaptedRequest.removeAttribute("test");

        verify(originalRequest).removeAttribute("test");
    }

    @Test
    public void getLocale() {
        when(originalRequest.getLocale()).thenReturn(new Locale("en"));

        assertEquals(new Locale("en"), biAdaptedRequest.getLocale());
    }

    @Test
    public void getLocales() {
        var retValue = mock(Enumeration.class);
        when(originalRequest.getLocales()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getLocales());
    }

    @Test
    public void isSecure() {
        when(originalRequest.isSecure()).thenReturn(true);

        assertTrue(biAdaptedRequest.isSecure());
    }

    @Test
    public void getRequestDispatcher() throws Exception {
        var retValue = mock(RequestDispatcher.class);
        when(originalRequest.getRequestDispatcher("test")).thenReturn(retValue);

        var mockRequest = mock(ServletRequest.class);
        when(mockRequest.getAttribute("test")).thenReturn("value");
        var mockResponse = mock(ServletResponse.class);
        biAdaptedRequest.getRequestDispatcher("test").forward(mockRequest, mockResponse);

        verify(retValue).forward(argThat(r -> r.getAttribute("test").equals("value")), any());
    }

    @Test
    public void getRealPath() {
        when(originalRequest.getRealPath("test")).thenReturn("path");

        assertEquals("path", biAdaptedRequest.getRealPath("test"));
    }

    @Test
    public void getRemotePort() {
        when(originalRequest.getRemotePort()).thenReturn(8080);

        assertEquals(8080, biAdaptedRequest.getRemotePort());
    }

    @Test
    public void getLocalName() {
        when(originalRequest.getLocalName()).thenReturn("localhost");

        assertEquals("localhost", biAdaptedRequest.getLocalName());
    }

    @Test
    public void getLocalAddr() {
        when(originalRequest.getLocalAddr()).thenReturn("127.0.0.1");

        assertEquals("127.0.0.1", biAdaptedRequest.getLocalAddr());
    }

    @Test
    public void getLocalPort() {
        when(originalRequest.getLocalPort()).thenReturn(8080);

        assertEquals(8080, biAdaptedRequest.getLocalPort());
    }

    @Test
    public void getServletContext() {
        var retValue = mock(ServletContext.class);
        when(retValue.getContextPath()).thenReturn("test");
        when(originalRequest.getServletContext()).thenReturn(retValue);

        assertEquals("test", biAdaptedRequest.getServletContext().getContextPath());
    }

    @Test
    public void startAsync() {
        var retValue = mock(AsyncContext.class);
        when(retValue.getTimeout()).thenReturn(10L);
        when(originalRequest.startAsync()).thenReturn(retValue);

        assertEquals(10, biAdaptedRequest.startAsync().getTimeout());
    }

    @Test
    public void testStartAsync() {
        var retValue = mock(AsyncContext.class);
        when(retValue.getTimeout()).thenReturn(10L);
        when(originalRequest.startAsync(any(), any())).thenReturn(retValue);

        assertEquals(10, biAdaptedRequest.startAsync(mock(ServletRequest.class), mock(ServletResponse.class)).getTimeout());
    }

    @Test
    public void isAsyncStarted() {
        when(originalRequest.isAsyncStarted()).thenReturn(true);

        assertTrue(biAdaptedRequest.isAsyncStarted());
    }

    @Test
    public void isAsyncSupported() {
        when(originalRequest.isAsyncSupported()).thenReturn(true);

        assertTrue(biAdaptedRequest.isAsyncSupported());
    }

    @Test
    public void getAsyncContext() {
        var retValue = mock(AsyncContext.class);
        when(retValue.getTimeout()).thenReturn(10L);
        when(originalRequest.getAsyncContext()).thenReturn(retValue);

        assertEquals(10, biAdaptedRequest.getAsyncContext().getTimeout());
    }

    @Test
    public void getDispatcherType() {
        when(originalRequest.getDispatcherType()).thenReturn(DispatcherType.ASYNC);

        assertEquals(DispatcherType.ASYNC, biAdaptedRequest.getDispatcherType());
    }

    @Test
    public void getHttpServletMapping() {
        var retValue = mock(HttpServletMapping.class);
        when(retValue.getMatchValue()).thenReturn("test");
        when(originalRequest.getHttpServletMapping()).thenReturn(retValue);

        assertEquals("test", biAdaptedRequest.getHttpServletMapping().getMatchValue());
    }

    @Test
    public void newPushBuilder() {
        var retValue = mock(PushBuilder.class);
        when(retValue.getMethod()).thenReturn("GET");
        when(originalRequest.newPushBuilder()).thenReturn(retValue);

        assertEquals("GET", biAdaptedRequest.newPushBuilder().getMethod());
    }

    @Test
    public void getTrailerFields() {
        when(originalRequest.getTrailerFields()).thenReturn(Map.of("test", "value"));

        assertEquals(Map.of("test", "value"), biAdaptedRequest.getTrailerFields());
    }

    @Test
    public void isTrailerFieldsReady() {
        when(originalRequest.isTrailerFieldsReady()).thenReturn(true);

        assertTrue(biAdaptedRequest.isTrailerFieldsReady());
    }

    @Test
    public void getAuthType() {
        when(originalRequest.getAuthType()).thenReturn("BASIC");

        assertEquals("BASIC", biAdaptedRequest.getAuthType());
    }

    @Test
    public void getCookies() {
        var cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("test");
        when(originalRequest.getCookies()).thenReturn(new Cookie[]{cookie});

        var cookies = biAdaptedRequest.getCookies();
        assertEquals(1, cookies.length);
        assertEquals("test", cookies[0].getName());
    }

    @Test
    public void getCookies_null() {
        when(originalRequest.getCookies()).thenReturn(null);

        assertNull(biAdaptedRequest.getCookies());
    }

    @Test
    public void getDateHeader() {
        when(originalRequest.getDateHeader("test")).thenReturn(10L);

        assertEquals(10L, biAdaptedRequest.getDateHeader("test"));
    }

    @Test
    public void getHeader() {
        when(originalRequest.getHeader("test")).thenReturn("value");

        assertEquals("value", biAdaptedRequest.getHeader("test"));
    }

    @Test
    public void getHeaders() {
        var retValue = mock(Enumeration.class);
        when(originalRequest.getHeaders("test")).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getHeaders("test"));
    }

    @Test
    public void getHeaderNames() {
        var retValue = mock(Enumeration.class);
        when(originalRequest.getHeaderNames()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getHeaderNames());
    }

    @Test
    public void getIntHeader() {
        when(originalRequest.getIntHeader("test")).thenReturn(10);

        assertEquals(10, biAdaptedRequest.getIntHeader("test"));
    }

    @Test
    public void getMethod() {
        when(originalRequest.getMethod()).thenReturn("GET");

        assertEquals("GET", biAdaptedRequest.getMethod());
    }

    @Test
    public void getPathInfo() {
        when(originalRequest.getPathInfo()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getPathInfo());
    }

    @Test
    public void getPathTranslated() {
        when(originalRequest.getPathTranslated()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getPathTranslated());
    }

    @Test
    public void getContextPath() {
        when(originalRequest.getContextPath()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getContextPath());
    }

    @Test
    public void getQueryString() {
        when(originalRequest.getQueryString()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getQueryString());
    }

    @Test
    public void getRemoteUser() {
        when(originalRequest.getRemoteUser()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getRemoteUser());
    }

    @Test
    public void isUserInRole() {
        when(originalRequest.isUserInRole("test")).thenReturn(true);

        assertTrue(biAdaptedRequest.isUserInRole("test"));
    }

    @Test
    public void getUserPrincipal() {
        var retValue = mock(java.security.Principal.class);
        when(originalRequest.getUserPrincipal()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getUserPrincipal());
    }

    @Test
    public void getRequestedSessionId() {
        when(originalRequest.getRequestedSessionId()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getRequestedSessionId());
    }

    @Test
    public void getRequestURI() {
        when(originalRequest.getRequestURI()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getRequestURI());
    }

    @Test
    public void getRequestURL() {
        var retValue = new StringBuffer("test");
        when(originalRequest.getRequestURL()).thenReturn(retValue);

        assertEquals(retValue, biAdaptedRequest.getRequestURL());
    }

    @Test
    public void getServletPath() {
        when(originalRequest.getServletPath()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.getServletPath());
    }

    @Test
    public void getSession() {
        var retValue = mock(HttpSession.class);
        when(retValue.getId()).thenReturn("test");
        when(originalRequest.getSession()).thenReturn(retValue);

        assertEquals("test", biAdaptedRequest.getSession().getId());
    }

    @Test
    public void testGetSession() {
        var retValue = mock(HttpSession.class);
        when(retValue.getId()).thenReturn("test");
        when(originalRequest.getSession(true)).thenReturn(retValue);

        assertEquals("test", biAdaptedRequest.getSession(true).getId());
    }

    @Test
    public void changeSessionId() {
        when(originalRequest.changeSessionId()).thenReturn("test");

        assertEquals("test", biAdaptedRequest.changeSessionId());
    }

    @Test
    public void isRequestedSessionIdValid() {
        when(originalRequest.isRequestedSessionIdValid()).thenReturn(true);

        assertTrue(biAdaptedRequest.isRequestedSessionIdValid());
    }

    @Test
    public void isRequestedSessionIdFromCookie() {
        when(originalRequest.isRequestedSessionIdFromCookie()).thenReturn(true);

        assertTrue(biAdaptedRequest.isRequestedSessionIdFromCookie());
    }

    @Test
    public void isRequestedSessionIdFromURL() {
        when(originalRequest.isRequestedSessionIdFromURL()).thenReturn(true);

        assertTrue(biAdaptedRequest.isRequestedSessionIdFromURL());
    }

    @Test
    public void isRequestedSessionIdFromUrl() {
        when(originalRequest.isRequestedSessionIdFromUrl()).thenReturn(true);

        assertTrue(biAdaptedRequest.isRequestedSessionIdFromUrl());
    }

    @Test
    public void authenticate() throws Exception {
        when(originalRequest.authenticate(argThat(r -> r.getStatus() == 2))).thenReturn(true);

        var response = mock(HttpServletResponse.class);
        when(response.getStatus()).thenReturn(2);
        assertTrue(biAdaptedRequest.authenticate(response));
    }

    @Test
    public void login() throws Exception {
        biAdaptedRequest.login("test", "test");

        verify(originalRequest).login("test", "test");
    }

    @Test
    public void logout() throws Exception {
        biAdaptedRequest.logout();

        verify(originalRequest).logout();
    }

    @Test
    public void getParts() throws Exception {
        var part = mock(Part.class);
        when(part.getName()).thenReturn("test");
        when(originalRequest.getParts()).thenReturn(Set.of(part));

        var parts = biAdaptedRequest.getParts();
        assertEquals(1, parts.size());
        assertEquals("test", parts.iterator().next().getName());
    }

    @Test
    public void getPart() throws Exception {
        var part = mock(Part.class);
        when(part.getName()).thenReturn("test");
        when(originalRequest.getPart("test")).thenReturn(part);

        assertEquals("test", biAdaptedRequest.getPart("test").getName());
    }

    @Test
    public void upgrade() throws Exception {
        var retValue = mock(HttpUpgradeHandler.class);
        when(originalRequest.upgrade(HttpUpgradeHandler.class)).thenReturn(retValue);

        biAdaptedRequest.upgrade(HttpUpgradeHandler.class).destroy();
        verify(retValue).destroy();
    }

    @Test
    public void getDelegate() {
        var adaptedRequest = new JavaXHttpServletRequestAdapter(originalRequest);
        assertEquals(originalRequest, adaptedRequest.getDelegate());

        var biAdaptedRequest = new JakartaHttpServletRequestAdapter(adaptedRequest);
        assertEquals(adaptedRequest, biAdaptedRequest.getDelegate());
    }
}
