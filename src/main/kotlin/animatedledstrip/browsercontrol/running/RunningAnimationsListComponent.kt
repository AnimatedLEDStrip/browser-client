package animatedledstrip.browsercontrol.running

import animatedledstrip.browsercontrol.Styles
import animatedledstrip.browsercontrol.runningAnimation
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.RBuilder
import react.RComponent
import react.setState
import styled.css
import styled.styledDiv
import styled.styledH2

class RunningAnimationsListComponent : RComponent<RunningAnimationsListProps, RunningAnimationsListState>() {
    override fun RunningAnimationsListState.init() {
        MainScope().launch {
            while (true) {
                val ids = props.alsClient.getRunningAnimationsIds()
                setState {
                    runningAnimationIds = ids
                }
                delay(250)
            }
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +Styles.pageSectionStyle
                +Styles.columnFlexStyle
            }
            styledH2 {
                css {
                    +Styles.centeredTextStyle
                }
                +"Running Animations"
            }
            for (id in state.runningAnimationIds ?: listOf()) {
                runningAnimation {
                    animId = id
                    alsClient = props.alsClient
                }
            }
        }
    }
}
