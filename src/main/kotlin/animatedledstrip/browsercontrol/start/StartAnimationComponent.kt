package animatedledstrip.browsercontrol.start

import animatedledstrip.animations.*
import animatedledstrip.animations.parameters.AbsoluteDistance
import animatedledstrip.animations.parameters.DegreesRotation
import animatedledstrip.animations.parameters.PercentDistance
import animatedledstrip.animations.parameters.RadiansRotation
import animatedledstrip.browsercontrol.Styles
import animatedledstrip.browsercontrol.toCSS
import animatedledstrip.colors.ColorContainer
import animatedledstrip.colors.ccpresets.Black
import animatedledstrip.leds.animationmanagement.*
import animatedledstrip.leds.locationmanagement.Location
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.asList
import react.RBuilder
import react.RComponent
import react.dom.*
import react.setState
import styled.*

class StartAnimationComponent : RComponent<StartAnimationProps, StartAnimationState>() {
    override fun StartAnimationState.init() {
        MainScope().launch {
            val supportedAnims = props.alsClient.getSupportedAnimationNames()
            setState {
                supportedAnimations = supportedAnims
            }
        }
    }

    private fun RBuilder.addColorParams(colors: List<ColorContainer>, addColorSupported: Boolean) {
        styledDiv {
            css {
                +Styles.columnFlexStyle
            }
            colors.forEachIndexed { ccIndex, cc ->
                styledDiv {
                    css {
                        +Styles.paramStyle
                    }
                    styledLabel {
                        css {
                            +Styles.paramLabelStyle
                        }
                        +"Color $ccIndex"
                    }
                    for ((colIndex, color) in cc.colors.withIndex())
                        input {
                            attrs {
                                id = "color $ccIndex $colIndex"
                                type = InputType.color
                                defaultValue = color.toCSS()
                                onChangeFunction = {
                                    val newColor =
                                        (document.getElementById("color $ccIndex $colIndex") as HTMLInputElement).value
                                            .removePrefix("#")
                                            .toInt(16)
                                    setState {
                                        colorsPerColor?.get(ccIndex)?.colors?.set(colIndex, newColor)
                                    }
                                }
                            }
                        }
                    button {
                        attrs {
                            onClickFunction = {
                                setState {
                                    colorsPerColor?.get(ccIndex)?.plusAssign(0)
                                }
                            }
                        }
                        +"+"
                    }
                }
            }
            if (addColorSupported) {
                button {
                    attrs {
                        onClickFunction = {
                            setState {
                                colorsPerColor?.add(ColorContainer.Black)
                            }
                        }
                    }
                    +"Add Color"
                }
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
                +"Start New Animation"
            }

            styledDiv {
                css {
                    +Styles.columnFlexStyle
                }
                select {
                    attrs {
                        id = "animSelect"

                        onChangeFunction = {
                            MainScope().launch {
                                val selectAnim = (document.getElementById("animSelect") as HTMLSelectElement).value
                                if (selectAnim != "") {
                                    val animInfo = props.alsClient.getAnimationInfo(selectAnim)
                                    setState {
                                        selectedAnim = animInfo
                                        colorsPerColor = mutableListOf<ColorContainer>().apply {
                                            for(i in 0 until animInfo.minimumColors) add(ColorContainer.Black)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    option {
                        attrs {
                            value = ""
                        }
                        +if (state.supportedAnimations?.isNotEmpty() == true) "Choose an Animation"
                        else "Contacting Server for Supported Animations..."
                    }
                    for (anim in state.supportedAnimations ?: setOf())
                        option {
                            attrs {
                                value = anim
                            }
                            +anim
                        }
                }

                addColorParams(state.colorsPerColor ?: listOf(), state.selectedAnim?.unlimitedColors ?: false)
                addBasicParams(state.selectedAnim?.intParams)
                addBasicParams(state.selectedAnim?.doubleParams)
                addBasicParams(state.selectedAnim?.stringParams)
                addLocationParams(state.selectedAnim?.locationParams)
                addDistanceParams(state.selectedAnim?.distanceParams)
                addRotationParams(state.selectedAnim?.rotationParams)

                styledButton {
                    css {
                        +Styles.topMarginStyle
                    }
                    attrs {
                        onClickFunction = {
                            if ((document.getElementById("animSelect") as HTMLSelectElement).value.isNotBlank()) {
                                val newAnim = AnimationToRunParams().apply {
                                    animation = (document.getElementById("animSelect") as HTMLSelectElement).value
                                    if (animation.isBlank()) return@apply

                                    addColors(state.colorsPerColor ?: listOf<ColorContainer>())

                                    for (param in state.selectedAnim?.intParams ?: listOf())
                                        intParam(param.name,
                                                 (document.getElementById(param.name) as HTMLInputElement).value.toIntOrNull()
                                                 ?: param.default ?: 0)

                                    for (param in state.selectedAnim?.doubleParams ?: listOf())
                                        doubleParam(param.name,
                                                    (document.getElementById(param.name) as HTMLInputElement).value.toDoubleOrNull()
                                                    ?: param.default ?: 0.0)

                                    for (param in state.selectedAnim?.stringParams ?: listOf())
                                        stringParam(param.name,
                                                    (document.getElementById(param.name) as HTMLInputElement).value)

                                    for (param in state.selectedAnim?.locationParams ?: listOf()) {
                                        val x =
                                            (document.getElementById("${param.name} location x") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0
                                        val y =
                                            (document.getElementById("${param.name} location y") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0
                                        val z =
                                            (document.getElementById("${param.name} location z") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0

                                        locationParam(param.name, Location(x, y, z))
                                    }

                                    for (param in state.selectedAnim?.distanceParams ?: listOf()) {
                                        val type =
                                            (document
                                                .getElementsByName("${param.name} distance type")
                                                .asList()
                                                .find { (it as HTMLInputElement).checked } as HTMLInputElement?)
                                                ?.value ?: run {
                                                console.error("Distance type for parameter ${param.name} must be selected")
                                                return@apply
                                            }

                                        val x =
                                            (document.getElementById("${param.name} distance x") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0
                                        val y =
                                            (document.getElementById("${param.name} distance y") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0
                                        val z =
                                            (document.getElementById("${param.name} distance z") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0

                                        when (type) {
                                            "absolute" -> distanceParam(param.name, AbsoluteDistance(x, y, z))
                                            "percent" -> distanceParam(param.name, PercentDistance(x, y, z))
                                        }
                                    }

                                    for (param in state.selectedAnim?.rotationParams ?: listOf()) {
                                        val type =
                                            (document
                                                .getElementsByName("${param.name} rotation type")
                                                .asList()
                                                .find { (it as HTMLInputElement).checked } as HTMLInputElement?)
                                                ?.value ?: run {
                                                console.error("Rotation type for parameter ${param.name} must be selected")
                                                return@apply
                                            }

                                        val x =
                                            (document.getElementById("${param.name} rotation x") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0
                                        val y =
                                            (document.getElementById("${param.name} rotation y") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0
                                        val z =
                                            (document.getElementById("${param.name} rotation z") as HTMLInputElement)
                                                .value
                                                .toDoubleOrNull() ?: 0.0

                                        when (type) {
                                            "radians" -> rotationParam(param.name, RadiansRotation(x, y, z))
                                            "degrees" -> rotationParam(param.name, DegreesRotation(x, y, z))
                                        }
                                    }
                                }
                                console.log(newAnim)
                                MainScope().launch {
                                    props.alsClient.startAnimation(newAnim)
                                }
                            }
                        }
                    }
                    +"Start"
                }
            }

        }
    }
}
