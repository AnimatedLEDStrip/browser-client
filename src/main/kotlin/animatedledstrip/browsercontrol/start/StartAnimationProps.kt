package animatedledstrip.browsercontrol.start

import animatedledstrip.client.ALSHttpClient
import react.RProps

external interface StartAnimationProps : RProps {
    var alsClient: ALSHttpClient<*>
}
