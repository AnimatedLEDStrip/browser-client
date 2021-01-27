package animatedledstrip.browsercontrol.running

import animatedledstrip.leds.animationmanagement.RunningAnimationParams
import react.RState

external interface RunningAnimationState : RState {
    var animParams: RunningAnimationParams?
}