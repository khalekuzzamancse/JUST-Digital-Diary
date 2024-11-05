## Motivation
- If define the `entity` to `domain/contract` module only then since these are accessible
from the datasource modules and  api modules as result each feature module not access to these
entity because the feature module are not allowed to depends on  `domain/contract`, only the data source 
module and api module allowed to depends on `domain/contract` and data source modules,
the feature module only allowed to depends on `api` module.
but are not allowed to declare the entities in the `api` module because all data source model need to 
access this entity and `api` module need to depends on all data source,
that means api depends on data sources(api-->dataSources)
and all data sources must implement the `domain/contract` module contract that means
`dataSouces--->domain`
but if we define the entity in the `api` module then data sources need to depends on `api` in order 
to implement the contract defined in `domain/contract`, this will cause circular dependency which is not allowed.

## Benefit of moving entities from `domain/contract` to a separate module(entity)
- Since `:feature` module are depends on `:api` now each feature module can direcly use these entity 
as to transfer data as a result each `feature` module 
  - Need not define their own entity 
  - Need to parse the Json 



## Affect to existing code
that means now instead of `:network` (for parsing json) now each module need to depends on `entity`,
but `:api` module depends on `:entity` module via `api()` gradle dependency instead of `implemention` that 
each feature module dependency decrease by 1 because they need to depends on `:network` any more.

However if a feature module wants to define their own entity and parser they can easily do that,

so the number of dependency to feature module is same as previous but the benefit is larger because if a
feature module has n entities previous then m module will have x=n*m entities and x is larger for 
scalable project and also  need to parse x entities 
However if a feature module wants that he will define it own 

- `:domain/:contract`  can depends on `:entity` module via `api()` gradle dependency instead of `implemention` 
as a result all the consumer module of `:domain/:contract` now will have access to entity that means all the data sources
code need to change means they can still access entities via the  `:domain/:contract`

- If since the number of entity,parsing and module dependency decrease so any new feature can be added quick,because
now the feature module data layer is very thin,because it has no entity,json passion,network IO and custom execution conversion
- so clean arch with less code and less code==less bug=faster development
- However if a feature module want/need to json response instead of enity this can be easily added along side with exising code
with minimum effort , minimum cost

- Since the `:featuer:data` layer is now thin as a result if there is a feature that is `thin` then within single module this feature
can be implemented because the `:feature:domain` module is always a thin module, and `:feature:di` is ultra thin so as a result
the whole all feature sub module can be converted to package because they are thin as result in each feature we will save
4 extra module so for n thing feature we will save 4n module
- When a project is early stage it will feature module are thin so all feature layer(`data,domain,di,presentation`) can be 
implemented as `package` instead of `:module` as a result in the early stage or that app that has less featrue or has features
- that are thing
# How thin the `feature:data` module become?
- Now `feature:data` module need to define data sources class,interfaces ,and factory methods 
  -  So need to define RemoteDataSource,LocalDataSource,RemoteDataSourceImpl,LocalDataSourceImpl
    - Factory method for createRemoteSource(),createLocalSource
  - Need not to parse response as Expected entity and on failure to feedback message entity so it will 
  save lots of codes for each method/operation/use case
  - Now the repository just pull the data from `:api` module and map to `feature:domain` model and return that it
  - So now the RepositoryImpl class itself become very thin
  - No entity need to define the in the `feature:data:`
  - So for most feature even it is large there are main two classes in the data layer(RepositoryImpl,EntityMapper)
  - So the `feature:data:` very thin so now it make sense to implement the layers (`data,domain,di,presentation`) using package
   instead of `:module` as result we have less module, less code,less bug, faster development
  