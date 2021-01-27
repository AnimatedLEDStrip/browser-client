package animatedledstrip.browsercontrol.stripinfo
import animatedledstrip.client.ALSHttpClient
import react.RProps

external interface StripInfoProps : RProps {
    var alsClient: ALSHttpClient
}