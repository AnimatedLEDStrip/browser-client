package animatedledstrip.browsercontrol.running

import animatedledstrip.browsercontrol.Styles
import animatedledstrip.browsercontrol.toCSSList
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.background
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.dom.button
import react.dom.p
import react.setState
import styled.css
import styled.inlineStyles
import styled.styledDiv
import styled.styledH3

class RunningAnimationComponent : RComponent<RunningAnimationProps, RunningAnimationState>() {
    override fun RunningAnimationState.init() {
        MainScope().launch {
            val newParams = props.alsClient.getRunningAnimationParams(props.animId)
            setState {
                animParams = newParams
            }
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +Styles.runningAnimationStyle
            }
            styledDiv {
                css {
                    +Styles.rowFlexStyle
                }
                styledH3 {
                    css {
                        +Styles.centeredTextStyle
                        +Styles.fillAvailableWidthStyle
                    }
                    +props.animId
                }
                button {
                    attrs {
                        onClickFunction = {
                            MainScope().launch {
                                props.alsClient.endAnimation(state.animParams?.id ?: return@launch)
                            }
                        }
                    }
                    +"End Animation"
                }
            }
            p {
                +"Animation: ${state.animParams?.animationName ?: "Loading..."}"
            }
            p {
                +"Run Count: ${state.animParams?.runCount ?: "Loading..."}"
            }
            p {
                +"Section: ${state.animParams?.section ?: "Loading..."}"
            }
            for (color in state.animParams?.colors ?: listOf()) {
                styledDiv {
                    inlineStyles {
                        background = "linear-gradient(to right, ${color.colors.toCSSList()})"
                    }
                    p {
                        +"-"
                    }
                }
            }
            for (param in state.animParams?.intParams ?: mapOf())
                p {
                    +"${param.key}: ${param.value}"
                }
            for (param in state.animParams?.doubleParams ?: mapOf())
                p {
                    +"${param.key}: ${param.value}"
                }
            for (param in state.animParams?.stringParams ?: mapOf())
                p {
                    +"${param.key}: ${param.value}"
                }
            for (param in state.animParams?.locationParams ?: mapOf())
                p {
                    +"${param.key}: ${param.value}"
                }
            for (param in state.animParams?.distanceParams ?: mapOf())
                p {
                    +"${param.key}: ${param.value}"
                }
            for (param in state.animParams?.rotationParams ?: mapOf())
                p {
                    +"${param.key}: ${param.value}"
                }
            for (param in state.animParams?.equationParams ?: mapOf())
                p {
                    +"${param.key}: ${param.value}"
                }
        }
    }
}
