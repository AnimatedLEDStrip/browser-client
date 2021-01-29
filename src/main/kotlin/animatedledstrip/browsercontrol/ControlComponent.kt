package animatedledstrip.browsercontrol

import react.RBuilder
import react.RComponent
import react.RState
import styled.css
import styled.styledDiv

class ControlComponent : RComponent<ControlProps, RState>() {
    override fun RBuilder.render() {
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
                    alsClient = props.alsClient
                }
            }
            styledDiv {
                css {
                    +Styles.pageHalfStyle
                }
                stripInfo {
                    alsClient = props.alsClient
                }
                runningAnimationsList {
                    alsClient = props.alsClient
                }
            }
        }
    }
}
