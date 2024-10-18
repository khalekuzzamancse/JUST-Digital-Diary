## Why Data transferring via String instead of Model class
Let we have data sources as Firebase,Room, Mongo and Server
So if we follow the separation of concern then we need 4  model class in the consumer module this overhead.

Recall the concept of Rest api that deal with JSON(String),so since this our target is to test or implement things faster with
less code and less changes that is why it is better to strict with the String(JSON) instead of model class

However if there is n data sources the client should not have n model or entity classes,regradless of number of data sources we
force the client to stick with a single model/entity class then this model will be defined as Kotlix serializable entity class 
in this domain module.

Now it possible that each of data sources follow the different data structure such as Room as concept of primary key,
mongodb has Bson object or document id ,firebase has documents id and there may be some extra internal
fields, we should expose this information to the client/consumer module that is why we will define
an api interface,this api interface will follow the model defined this module and each data source will will
implement this api so that the methods signature and data structure of JSON are the same.
As a result when data will be sended from any data source it it use the services to convert their data
to the common structure where the data souces need to implement the services , the services will be defiend
in this module and all data source just use as functions call,
as a result regradless of data source the client will stick with a single data structure and the same set of 
api that is defined in the module.

Additional benefit:
If we at some point you need to define a  server by kotlin such ktor server or spring boot either for this project or any other project
then since the data is already transferred by Json so we can copy-paste and modifying this code , will help to implement the server
fast or since these are kotlin cod you can use this  database module for as dependey and direcly delegate to as a result you need to 
implement the database part from strack just define the rest end point and those end points will delegate the call to database...

#





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


one of my project I found the mongodb is not working properly with android target though 
it works on desktop platforms so in that case how fast I can change the database faster
to test or show the client that app  is working fine,

in that case this kind of abstraction is better
