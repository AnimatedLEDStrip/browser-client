package animatedledstrip.browsercontrol

import kotlinx.browser.document
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.h2
import react.dom.input
import styled.css
import styled.styledButton
import styled.styledDiv

class ConnectComponent : RComponent<ConnectProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +Styles.pageQuarterStyle
                +Styles.fontStyle
            }
            h2 {
                +"Please enter server IP"
            }
            input {
                attrs {
                    type = InputType.text
                    id = "ipInput"
                }
            }
            styledButton {
                css {
                    +Styles.centeredTextStyle
                }
                attrs {
                    onClickFunction = {
                        console.log((document.getElementById("ipInput") as HTMLInputElement).value)
                        props.onConnectButtonClickEvent((document.getElementById("ipInput") as HTMLInputElement).value)
                    }
                }
                +"Connect"
            }
        }
    }
}