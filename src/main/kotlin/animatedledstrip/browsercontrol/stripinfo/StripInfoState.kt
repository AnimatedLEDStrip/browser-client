package animatedledstrip.browsercontrol.stripinfo

import animatedledstrip.leds.colormanagement.CurrentStripColor
import animatedledstrip.leds.stripmanagement.StripInfo
import react.RState

external interface StripInfoState : RState {
    var stripInfo: StripInfo?
    var currentColor: CurrentStripColor?
}
