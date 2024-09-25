# Jakarta Adapters

A collection of classes for adapting Servlet API 4.0 objects to Servlet API 5.0 and vice versa.

Simply bundle this library with your application and wrap objects in the appropriate adapter. You must also bundle the
Servlet API dependencies that are not already provided by the container/environment.

Convenient static adapter methods are additionally defined in:

- `io.atlassian.util.adapter.jakarta.JakartaAdapters`
- `io.atlassian.util.adapter.javax.JavaXAdapters`

This library of adapters is useful when you want to integrate a library that relies upon Servlet API 4.0 in a Servlet
API 5.0+ environment, or vice versa. They serve as an alternative to tools such as Eclipse Transformer which modifies
the bytecode.

They are also useful if you have an application or library which exposes Servlet API 4.0 based APIs. Using the adapters
it is possible to provide a transitionary period for consumers by supporting both Servlet API 4.0 and 5.0+ based APIs
simultaneously.
