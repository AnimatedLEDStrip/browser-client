package animatedledstrip.browsercontrol
import animatedledstrip.client.ALSHttpClient
import react.RState

external interface AppState : RState {
    var alsClient: ALSHttpClient<*>?
}
