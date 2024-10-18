package io.atlassian.util.adapter.jakarta.servlet.jsp.tagext;

import io.atlassian.util.adapter.javax.servlet.jsp.tagext.JavaXBodyContentAdapter;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.BodyContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Reader;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaBodyContentAdapterTest {

    @Mock
    private BodyContent original;

    private BodyContent biAdapted;

    @BeforeEach
    void setUp() {
        biAdapted = new JakartaBodyContentAdapter(JavaXBodyContentAdapter.from(original));
    }

    @Test
    void getBufferSize() {
        when(original.getBufferSize()).thenReturn(42);

        assertEquals(42, biAdapted.getBufferSize());
    }

    @Test
    void isAutoFlush() {
        when(original.isAutoFlush()).thenReturn(true);

        assertTrue(biAdapted.isAutoFlush());
    }

    @Test
    void newLine() throws Exception {
        biAdapted.newLine();

        verify(original).newLine();
    }

    @Test
    void print() throws Exception {
        biAdapted.print(true);

        verify(original).print(true);
    }

    @Test
    void testPrint() throws Exception {
        biAdapted.print('c');

        verify(original).print('c');
    }

    @Test
    void testPrint1() throws Exception {
        biAdapted.print(42);

        verify(original).print(42);
    }

    @Test
    void testPrint2() throws Exception {
        biAdapted.print(42L);

        verify(original).print(42L);
    }

    @Test
    void testPrint3() throws Exception {
        biAdapted.print(42.0f);

        verify(original).print(42.0f);
    }

    @Test
    void testPrint4() throws Exception {
        biAdapted.print(42.0d);

        verify(original).print(42.0d);
    }

    @Test
    void testPrint5() throws Exception {
        biAdapted.print(new char[]{'c'});

        verify(original).print(new char[]{'c'});
    }

    @Test
    void testPrint6() throws Exception {
        biAdapted.print("test");

        verify(original).print("test");
    }

    @Test
    void testPrint7() throws Exception {
        var obj = new Object();
        biAdapted.print(obj);

        verify(original).print(obj);
    }

    @Test
    void println() throws Exception {
        biAdapted.println();

        verify(original).println();
    }

    @Test
    void testPrintln() throws Exception {
        biAdapted.println(true);

        verify(original).println(true);
    }

    @Test
    void testPrintln1() throws Exception {
        biAdapted.println('c');

        verify(original).println('c');
    }

    @Test
    void testPrintln2() throws Exception {
        biAdapted.println(42);

        verify(original).println(42);
    }

    @Test
    void testPrintln3() throws Exception {
        biAdapted.println(42L);

        verify(original).println(42L);
    }

    @Test
    void testPrintln4() throws Exception {
        biAdapted.println(42.0f);

        verify(original).println(42.0f);
    }

    @Test
    void testPrintln5() throws Exception {
        biAdapted.println(42.0d);

        verify(original).println(42.0d);
    }

    @Test
    void testPrintln6() throws Exception {
        biAdapted.println(new char[]{'c'});

        verify(original).println(new char[]{'c'});
    }

    @Test
    void testPrintln7() throws Exception {
        biAdapted.println("test");

        verify(original).println("test");
    }

    @Test
    void testPrintln8() throws Exception {
        var obj = new Object();
        biAdapted.println(obj);

        verify(original).println(obj);
    }

    @Test
    void clear() throws Exception {
        biAdapted.clear();

        verify(original).clear();
    }

    @Test
    void clearBuffer() throws Exception {
        biAdapted.clearBuffer();

        verify(original).clearBuffer();
    }

    @Test
    void flush() throws Exception {
        biAdapted.flush();

        verify(original).flush();
    }

    @Test
    void close() throws Exception {
        biAdapted.close();

        verify(original).close();
    }

    @Test
    void getRemaining() throws Exception {
        when(original.getRemaining()).thenReturn(42);

        assertEquals(42, biAdapted.getRemaining());
    }

    @Test
    void write() throws Exception {
        biAdapted.write(42);

        verify(original).write(42);
    }

    @Test
    void testWrite() throws Exception {
        biAdapted.write(new char[]{'c'});

        verify(original).write(new char[]{'c'});
    }

    @Test
    void testWrite1() throws Exception {
        biAdapted.write(new char[]{'c'}, 0, 1);

        verify(original).write(new char[]{'c'}, 0, 1);
    }

    @Test
    void testWrite2() throws Exception {
        biAdapted.write("test");

        verify(original).write("test");
    }

    @Test
    void testWrite3() throws Exception {
        biAdapted.write("test", 0, 1);

        verify(original).write("test", 0, 1);
    }

    @Test
    void append() throws Exception {
        var writer = mock(Writer.class);
        when(original.append('c')).thenReturn(writer);

        assertEquals(writer, biAdapted.append('c'));
        verify(original).append('c');
    }

    @Test
    void testAppend() throws Exception {
        var writer = mock(Writer.class);
        when(original.append("test")).thenReturn(writer);

        assertEquals(writer, biAdapted.append("test"));
        verify(original).append("test");
    }

    @Test
    void testAppend1() throws Exception {
        var writer = mock(Writer.class);
        when(original.append("test", 0, 1)).thenReturn(writer);

        assertEquals(writer, biAdapted.append("test", 0, 1));
        verify(original).append("test", 0, 1);
    }

    @Test
    void clearBody() {
        biAdapted.clearBody();

        verify(original).clearBody();
    }

    @Test
    void getEnclosingWriter() {
        var writer = mock(JspWriter.class);
        when(writer.isAutoFlush()).thenReturn(true);
        when(original.getEnclosingWriter()).thenReturn(writer);

        assertTrue(biAdapted.getEnclosingWriter().isAutoFlush());
    }

    @Test
    void getReader() {
        var reader = mock(Reader.class);
        when(original.getReader()).thenReturn(reader);

        assertEquals(reader, biAdapted.getReader());
    }

    @Test
    void getString() {
        when(original.getString()).thenReturn("test");

        assertEquals("test", biAdapted.getString());
    }

    @Test
    void writeOut() throws Exception {
        var writer = mock(Writer.class);
        biAdapted.writeOut(writer);

        verify(original).writeOut(writer);
    }
}
