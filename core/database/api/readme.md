### Module Overview

- You don’t need to implement this module from scratch—simply copy and paste it, then define your
  schema/entity in the domain/contract layer.
- Add database I/O with minimal code and start using it
- No need to define or handle custom exceptions; they are already built into the module.
- Since you’re not developing the module from scratch, it will likely speed up development, even if
  you need to switch between different databases,
- add caching, or switch to remote APIs.
- You can copy the entire `core` module to another project and remove any unnecessary parts to
  tailor it to your needs.

### Additional Features

- **Merge Multiple Data Sources**: You can merge responses from multiple data sources into a single
  response without modifying the feature modules.
  This can be extremely useful in certain situations.

  For example, in one project, a developer was working with a remote API that provided a list of
  employee. However, the image links for the employee
  weren't functioning correctly, and the developer didn't have permission to modify the remote API.
  To address this and convince the client that the app was
  functioning properly, the developer quickly added a local database using the existing module. They
  stored the correct image links in this database and, during the conversion to the domain/contract
  layer, replaced the faulty remote API image links with those from the local database.

  While you might not always work with multiple data sources, this feature can significantly speed
  up development and improve client satisfaction by
  demonstrating that the app works as expected.

- **Handling Local and Remote Data Sources**: This module eliminates the need for feature modules to
  implement `dataSource` classes
  (such as `RemoteDataSource` or `LocalDataSource`) to manage data loading from remote or local
  sources based on network availability. If data fails to load from a remote source,add a cache and 
  fall back to a local cache (e.g., Room or another database)
  and return the cached data to the client.

As a result, each feature module can interact with the repository directly, without the need to
manage separate requests to the local or remote data
sources. This reduces boilerplate code and simplifies the data handling logic in feature modules.

###Motivation

### Improving Module Design for APIs and Data Handling

#### Introduction

The purpose of this module was initially to expose APIs as functions, where the APIs represent an
abstraction. This module depends on a data source,
either a local source such as Room or a remote source like Firebase or MongoDB. The module delivers
data to the client or consumer in a JSON format,
defined within the domain or contract module. The idea behind the `domain/contract` design was to
allow for seamless database
switching (e.g., from MongoDB to Firebase) with minimal code changes, ensuring minimal impact on the
client or consumer.

#### Challenges Encountered

Over time, I realized that the backend is not always under my control. Often, the backend is
developed by someone else, resulting in its own
set of APIs with distinct data formats that do not always align with the structure defined in the
domain/contract module.
To handle this scenario without modifying the existing code, each feature module's data layer needs
to:

- Create a separate version of the entity.
- Provide a new implementation of the repository.
- Add a dependency on the network module (responsible for API calls).

This approach works for certain use cases but creates overhead as each data layer must handle
different entity types and mappers,
which map these entities to the corresponding domain layer model.

#### Key Observation

When switching from a database to a remote API that does’t perfectly adhere to
the `core:database:domain/contract` structure,
each feature module needs to:

1. Depend on the `core:network` module for network requests.
2. Depend on the `core:database:api` module for caching or testing purposes, especially when remote
   APIs are incomplete or still under development.

This results in feature modules depending on both the network and database modules, causing
complexity. For instance, caching may be required
using Room (`core:database`) while testing might rely on the APIs of the remote
system (`core:network`).

#### Proposal: Merging Layers for Simplified Dependency

Since both the `core:database:api` and `core:network` modules are lightweight and are used for
similar purposes (either caching or testing),
merging them into a single module could simplify the overall design. The benefits of this approach
include:

1. **Reduced Dependencies:** Each feature module now depends on a single module rather than two
   separate modules.
2. **Flexible Data Source Switching:** If a switch is required from a remote API to a remote
   database, feature modules won’t need to introduce
   new read/write entities for each API type. Only repository implementations will change, as some
   remote APIs might need parameters like tokens,
   which may not be required by databases.
3. **Automatic Data Fallback:** If data fails to load from a remote API, the merged module can
   automatically fetch data from the local cache
   and provide it to the feature module.
4. **Unified Structure:** If the remote API entity structure differs from
   the `core:database:domain/contract`, the merged module can include
   adapters to convert the remote API structure into the expected domain format. This ensures that
   feature modules only depend on a single,
   consistent structure without needing new entity types.

#### Conclusion

By merging the `core:network` and `core:database:api` modules, we can streamline dependencies,
simplify data source management,
and reduce the need for redundant entity handling. This results in more maintainable and flexible
code, allowing feature modules to easily
adapt to changes in backend APIs or data sources without excessive overhead.

### Naming the Merged Module

Since we are merging the network layer with the existing data layer, we can assign a name to this
module that
reflects its functionality. Some possible names could be:

- **`api`**: This name emphasizes the module's role in exposing APIs, making it clear that the
  module deals with API interactions.
- **`network`**: This is also a valid name since the merged layer primarily handles network requests
  and API calls.
  The name "network" accurately reflects the module's responsibility in managing external data
  sources.

By merging the network layer with this data handling layer, the need for frequent changes in feature
modules due to
shifts in data sources or API modifications is minimized. This layer will manage such variations
internally,
providing a stable interface to the feature modules, ensuring that they remain unaffected by changes
in underlying data sources.

Note that instead of directly merging you can add the implementation() dependency of network module
to this layer, as a result your network
layer can be used independently(if needed)

## Closing note

I found this helpful for this project because in this project the remote api is designed by
some one else and the project is large enough(specially for single developer), and I was
not able to sync working process of app with the api development, for some reason I want to
deliver the app faster where the all remote api is not read yet,but I have to deliver it so
for quick testing I introduce my own database such as mongodb and exposes the needed api for
both public and admin user can then develop the app and test it.
Also at the beginning I was confuse that which database is used mongo or firebase or anything else
because the client may want different database with different features that where I realized that
the app should be developed by targeting a specific database that is why I introduced
the abstraction and facade such as the `core:database:domain/contract` , `core:database:api`
so that database can be switched so quick
with minimal code and without touching the features modules.

Now even if the app may use it own backend service I can still the use
the `core:database:domain/contract` , `core:database:api`
for caching because `room/sqllite ` is some kind of database.

So I found that is if you in future you need to caching or you may want to
change the database or backend or remote apis then better start with this structure as a result

- if remote apis is not ready yet still you can develop whole app with your own remote database
- With minimal code and and without touching features module you can switch between different
  database(remote/local) or remote apis
    - Even for testing purposes you can use a file system instead of database or remote api or cache
      in this layer to your feature module or app
      will not affected , then once you move from file system to database or remote api or caching
      your
      feature module code need not to change

However depending on your project you may need follow different strategies,but for most of the
project you can at least start with this template then when the remote api are mature you can
gradually move the code the feature modules(though I think it not needed)

This is not overhead for a project that is small but if your project grow smaller to larger
then it will give extra benefits

### Closing Note: Insights from the Project

In this project, the remote API was designed by someone else, and due to the project's
scale—especially for a single developer—I
found it challenging to sync the app development with the API development process. The need for
quick delivery further complicated
the situation as not all remote APIs were ready. To overcome this, I introduced my own database (
e.g., MongoDB) and exposed the
necessary APIs for both public and admin users, allowing me to develop and test the app efficiently.

#### Flexible Database Choice

Initially, I was unsure which database to use—MongoDB, Firebase, or something else—because the
client might request different
databases with varying features. This led me to realize that the app should be developed with
flexibility in mind.
To address this, I introduced abstraction and facade layers, such as:

- `core:database:domain/contract`
- `core:database:api`

These layers allow for quick switching between databases with minimal code changes, without
impacting the feature modules.

#### Caching and Future Flexibility

Even if the app uses its own backend service, I can still use the `core:database:domain/contract`
and `core:database:api`
for caching purposes, as Room or SQLite function similarly to a database. This structure offers
significant
advantages for future needs, such as:

- **Development with Incomplete APIs**: If remote APIs aren't ready, you can still develop the
  entire app using your own database,
  whether remote or local.
- **Seamless Switching**: You can switch between databases (remote or local) or remote APIs with
  minimal code changes, and without modifying the feature modules.
- **Testing Flexibility**: For testing, you can use a file system, database, or remote API in this
  layer without affecting the app's core functionality.
  When transitioning to a full database or remote API, the feature module code remains unchanged.

#### Strategy Consideration

While different projects may require different strategies, this approach provides a solid starting
point for most projects.
You can adopt this structure initially, and as the remote APIs mature, you can gradually shift more
code to the feature modules,
though this might not always be necessary.

#### Benefits for Long-Term Growth

This approach may not seem like overhead for smaller projects, but as the project scales, this
structure provides additional benefits
by ensuring flexibility and reducing the complexity of switching between data sources or APIs.

### Disclaimer

This is merely a recommendation, and you do not need to follow it exactly. Depending on your
project’s specific requirements,
you may need to adopt different strategies. However, it is certain that this layer structure will
reduce your code complexity
and will not introduce any extra overhead, even for small projects. It offers flexibility and ease
of switching between
data sources or APIs, making it a robust approach for both small and large-scale projects.