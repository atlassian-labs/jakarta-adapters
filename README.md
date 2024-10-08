# Jakarta Adapters

An incomplete collection of classes for adapting Servlet API 4.0 objects to Servlet API 5.0 and vice versa.

Simply bundle this library with your application and wrap objects in the appropriate adapter. You must also bundle the
Servlet API dependencies that are not already provided by the container/environment.

Convenient static adapting methods are additionally defined in:

- `io.atlassian.util.adapter.jakarta.JakartaAdapters`
- `io.atlassian.util.adapter.javax.JavaXAdapters`

Note that there are some limitations to using these adapters, as not all methods are adaptable. Additionally, if your
project relies on object class hierarchy specifics through the use of `instanceof` checks or casts, you may not find
this solution suitable.

## Use Cases

This library of adapters is useful when you want to integrate a dependency that relies upon Servlet API 4.0 in a Servlet
API 5.0+ environment, or vice versa. They serve as an alternative to tools such as Eclipse Transformer which transforms
the bytecode of your dependencies at build time.

You may prefer the solution offered by this library if you desire to:

- Avoid additional complexity of build time transformation configuration, including for your project's consumers
- Improved static analysis and developer experience as your IDE/tools do not need to account for transformed
  dependencies
- If your project exposes Servlet API 4.0 based APIs, it is possible to provide a transitionary period for consumers by
  exposing and supporting both Servlet API 4.0 and 5.0+ based APIs simultaneously

### License

Distribution of this library is under the terms of the [Apache License, Version 2.0](LICENSE).

### Disclaimer

Atlassian does not provide any commitment to support or maintain this library. It is provided as-is and
without warranty.
