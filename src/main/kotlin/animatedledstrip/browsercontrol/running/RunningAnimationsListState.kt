package animatedledstrip.browsercontrol.running

import react.RState

external interface RunningAnimationsListState : RState {
    var runningAnimationIds: List<String>?
}