package animatedledstrip.browsercontrol.running

import animatedledstrip.client.ALSHttpClient
import react.RProps

external interface RunningAnimationsListProps : RProps {
    var alsClient: ALSHttpClient
}