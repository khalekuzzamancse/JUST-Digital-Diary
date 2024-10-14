Any data source, such as Room, MongoDB, Firebase, or a server, will follow this contract.
It will determine the primary key and handle data in JSON format, both for requests and responses,
to avoid unnecessary models and entities across all data sources. This module defines a schema that
works for all data sources,
whether for reading or writing, so no data source needs to define its own entities or schemas—they
can directly use JSON for reading and writing.
Each data source will decide its primary key, which allows easy switching between different
databases with minimal code changes.
The module also defines custom exceptions, which all data sources will follow, eliminating the need
for each data source to define
its own exceptions. This reduces code duplication and makes it easier to switch data sources, such
as from Firebase to MongoDB.
Additionally, the module defines the APIs that each data source should implement, ensuring
consistency in supported operations

.Importantly, this module does not depend on any specific data source (e.g., server,
Firebase, MongoDB, or Room), but rather, all data sources must depend on it in terms 
of data response and entry structure, APIs, and custom exceptions. 
The module also defines custom exceptions, which all data sources will follow, 
eliminating the need for each data source to define its own exceptions.
This reduces code duplication and makes it easier to switch data sources,
such as from Firebase to MongoDB. Additionally, the module defines the APIs that
each data source should implement, ensuring consistency in supported operations.