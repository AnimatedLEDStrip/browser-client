package animatedledstrip.browsercontrol

import animatedledstrip.client.ALSHttpClient
import io.ktor.client.engine.js.Js
import react.RBuilder
import react.RComponent
import react.RProps
import react.setState

class App : RComponent<RProps, AppState>() {
    override fun RBuilder.render() {
        if (state.alsClient == null) {
            connect {
                onConnectButtonClickEvent = { ip ->
                    setState {
                        alsClient = ALSHttpClient(Js, ip)
                    }
                }
            }
        } else {
            control {
                alsClient = state.alsClient!!
            }
        }
    }
}
