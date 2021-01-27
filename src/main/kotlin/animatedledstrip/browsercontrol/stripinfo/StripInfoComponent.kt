package animatedledstrip.browsercontrol.stripinfo

import animatedledstrip.browsercontrol.Styles
import animatedledstrip.browsercontrol.toCSSList
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.css.background
import react.RBuilder
import react.RComponent
import react.dom.p
import react.setState
import styled.*

class StripInfoComponent : RComponent<StripInfoProps, StripInfoState>() {
    override fun StripInfoState.init() {
        MainScope().launch {
            val newInfo = props.alsClient.getStripInfo()
            setState {
                stripInfo = newInfo
            }
            while (true) {
                val color = props.alsClient.getCurrentStripColor()
                setState {
                    currentColor = color
                }
                delay(100)
            }
        }
    }


    override fun RBuilder.render() {
        styledDiv {
            css {
                +Styles.pageSectionStyle
            }
            styledH2 {
                css {
                    +Styles.centeredTextStyle
                }
                +"Strip Info"
            }
            styledP {

                inlineStyles {
                    background = "linear-gradient(to right, ${state.currentColor?.color?.toCSSList() ?: "#FFFFFF"})"
                }
                +"-"
            }
            p {
                +"Number of LEDs: ${state.stripInfo?.numLEDs ?: "Loading..."}"
            }
            p {
                +"Pin: ${state.stripInfo?.pin ?: "Loading..."}"
            }
            p {
                +"Logging Enabled: ${state.stripInfo?.isRenderLoggingEnabled ?: "Loading..."}"
            }
            if (state.stripInfo?.isRenderLoggingEnabled == true) {
                p {
                    +"Render Log File Name: ${state.stripInfo?.renderLogFile}"
                }
                p {
                    +"Renders Between Log Saves: ${state.stripInfo?.rendersBetweenLogSaves}"
                }
            }
            p {
                +"1D Supported: ${state.stripInfo?.is1DSupported ?: "Loading..."}"
            }
            p {
                +"2D Supported: ${state.stripInfo?.is2DSupported ?: "Loading..."}"
            }
            p {
                +"3D Supported: ${state.stripInfo?.is2DSupported ?: "Loading..."}"
            }
        }
    }

}
