# Academic Calender
- Show Loading until data is null
- Proper Error for not fetching(Json parser, network, ...etc)
- Add Calender Feature: Add show initial dialog to taking input year and weekend while 
- 
## Schedule
- Add back button to schedule destination

##
- Add a check box for wee
- Show the  error

- Add divider in class and exam schedule form

##
Try to keep the ui layer interface and adapter as much as framework independent
that is why keeping viewmodel int the `ui` package

- Outer module should not know about the view model
## Why use case are concrete not abstract ??
because all the dependencies of use case are abstract so if anything need
to change then just provide different implementation ,so need to touch the 
use case though it is concrete ,since all dependencies is abstract so it 
act like abstract
