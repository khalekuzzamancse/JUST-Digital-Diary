package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.controls

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.events.RegisterDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.events.RegisterModuleEvent

interface RegisterControlEvents :RegisterModuleEvent{
    data object RegisterRequest: RegisterControlEvents
    data object LoginRequest: RegisterControlEvents
}