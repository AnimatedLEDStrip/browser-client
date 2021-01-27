package animatedledstrip.browsercontrol

import animatedledstrip.browsercontrol.running.RunningAnimationComponent
import animatedledstrip.browsercontrol.running.RunningAnimationProps
import animatedledstrip.browsercontrol.running.RunningAnimationsListComponent
import animatedledstrip.browsercontrol.running.RunningAnimationsListProps
import animatedledstrip.browsercontrol.start.StartAnimationComponent
import animatedledstrip.browsercontrol.start.StartAnimationProps
import animatedledstrip.browsercontrol.stripinfo.StripInfoComponent
import animatedledstrip.browsercontrol.stripinfo.StripInfoProps
import animatedledstrip.colors.b
import animatedledstrip.colors.g
import animatedledstrip.colors.r
import react.RBuilder
import react.ReactElement

fun RBuilder.connect(handler:  ConnectProps.() -> Unit): ReactElement =
    child(ConnectComponent::class) {
        this.attrs(handler)
    }

fun RBuilder.runningAnimationsList(handler: RunningAnimationsListProps.() -> Unit): ReactElement =
    child(RunningAnimationsListComponent::class) {
        this.attrs(handler)
    }

fun RBuilder.runningAnimation(handler: RunningAnimationProps.() -> Unit): ReactElement =
    child(RunningAnimationComponent::class) {
        this.attrs(handler)
    }

fun RBuilder.startAnimation(handler: StartAnimationProps.() -> Unit): ReactElement =
    child(StartAnimationComponent::class) {
        this.attrs(handler)
    }

fun RBuilder.stripInfo(handler: StripInfoProps.() -> Unit): ReactElement =
    child(StripInfoComponent::class) {
        this.attrs(handler)
    }

fun Int.toCSS(): String {
    fun Int.rCSS(): String = r.toString(16).padStart(2, '0')
    fun Int.gCSS(): String = g.toString(16).padStart(2, '0')
    fun Int.bCSS(): String = b.toString(16).padStart(2, '0')
    return "#${rCSS()}${gCSS()}${bCSS()}"
}

fun List<Int>.toCSSList(): String = this.joinToString { it.toCSS() }
