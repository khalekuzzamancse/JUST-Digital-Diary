

### Custom Exception Strategy

- Define the exception as a framework-independent exception
- All `domain `modules should use it as an `api` dependency so that consumers of the domain module have access
to these exceptions
- If any module or feature needs its own exception, those exceptions should inherit from this custom exception

### Motivation

After working on several projects, I found that about 95% of custom exceptions are common across modules.
If we define custom exceptions in the domain layer for each feature module, we end up copying and pasting these
exceptions from one module to another.
While this might be acceptable in some cases, when speed of development time is critical, this duplication
leads to problems.

For instance, if we need to refactor an exception—like changing its debug message or user-friendly message—we'd 
have to modify it across all modules. This slows down development and introduces risks, such as forgetting to 
update certain modules or dealing with imports of outdated exceptions. 
Therefore, I found that it’s better to define exceptions in a single place to avoid these issues.

However, this is just a recommendation. Depending on the project and delivery timeline, 
other approaches might be necessary. From my development experience, I highly recommend centralizing exceptions
in a core module, especially when aiming for faster development. 
It significantly reduces pain points, particularly when deadlines are tight.
