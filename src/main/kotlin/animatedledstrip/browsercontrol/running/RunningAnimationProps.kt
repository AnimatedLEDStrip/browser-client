package animatedledstrip.browsercontrol.running

import animatedledstrip.client.ALSHttpClient
import react.RProps

external interface RunningAnimationProps : RProps {
    var alsClient: ALSHttpClient<*>
    var animId: String
}
