package animatedledstrip.browsercontrol

import animatedledstrip.client.ALSHttpClient
import io.ktor.client.engine.js.Js
import react.RBuilder
import react.RComponent
import react.RProps
import react.setState
import styled.css
import styled.styledDiv

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
            styledDiv {
                css {
                    +Styles.rowFlexStyle
                    +Styles.fontStyle
                }
                styledDiv {
                    css {
                        +Styles.pageHalfStyle
                    }
                    startAnimation {
                        alsClient = state.alsClient!!
                    }
                }
                styledDiv {
                    css {
                        +Styles.pageHalfStyle
                    }
                    stripInfo {
                        alsClient = state.alsClient!!
                    }
                    runningAnimationsList {
                        alsClient = state.alsClient!!
                    }
                }
            }
        }

    }

}
