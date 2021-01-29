package animatedledstrip.browsercontrol

import animatedledstrip.client.ALSHttpClient
import react.RProps

external interface ControlProps : RProps {
    var alsClient: ALSHttpClient<*>
}
