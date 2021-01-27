package animatedledstrip.browsercontrol.start

import animatedledstrip.animations.*
import animatedledstrip.browsercontrol.Styles
import animatedledstrip.leds.locationmanagement.Location
import kotlinx.html.InputType
import kotlinx.html.id
import react.RBuilder
import react.dom.defaultValue
import react.dom.textArea
import styled.css
import styled.styledDiv
import styled.styledInput
import styled.styledLabel

fun RBuilder.addBasicParams(paramList: List<AnimationParameter<*>>?) {
    if (paramList == null) return
    for (param in paramList) {
        styledDiv {
            css {
                +Styles.paramStyle
            }
            styledLabel {
                css {
                    +Styles.paramLabelStyle
                    hover {
                        textArea {
                            
                        }
                    }
                }
                attrs {

                }
                +param.name
            }
            styledInput {
                css {
                    +Styles.paramInputStyle
                }
                attrs {
                    defaultValue = param.default.toString()
                    id = param.name
                    type = InputType.text
                }
            }
        }
    }
}

fun RBuilder.addLocationParams(paramList: List<AnimationParameter<Location>>?) {
    if (paramList == null) return
    for (param in paramList) {
        styledDiv {
            css {
                +Styles.paramStyle
            }
            styledLabel {
                css {
                    +Styles.paramLabelStyle
                }
                +param.name
            }
            styledDiv {
                css {
                    +Styles.rowFlexStyle
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"x"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.x?.toString() ?: "0.0"
                        id = "${param.name} location x"
                        type = InputType.text
                    }
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"y"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.y?.toString() ?: "0.0"
                        id = "${param.name} location y"
                        type = InputType.text
                    }
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"z"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.z?.toString() ?: "0.0"
                        id = "${param.name} location z"
                        type = InputType.text
                    }
                }
            }
        }
    }
}

fun RBuilder.addDistanceParams(paramList: List<AnimationParameter<Distance>>?) {
    if (paramList == null) return
    for (param in paramList) {
        styledDiv {
            css {
                +Styles.paramStyle
            }
            styledLabel {
                css {
                    +Styles.paramLabelStyle
                }
                +param.name
            }
            styledDiv {
                css {
                    +Styles.rowFlexStyle
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"X"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.x?.toString() ?: "0.0"
                        id = "${param.name} distance x"
                        type = InputType.text
                    }
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"Y"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.y?.toString() ?: "0.0"
                        id = "${param.name} distance y"
                        type = InputType.text
                    }
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"Z"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.z?.toString() ?: "0.0"
                        id = "${param.name} distance z"
                        type = InputType.text
                    }
                }

                styledDiv {
                    css {
                        +Styles.rowFlexStyle
                    }
                    styledLabel {
                        css {
                            +Styles.paramLabelStyle
                        }
                        +"Type:"
                    }
                    styledInput {
                        css {
                            +Styles.paramRadioStyle
                        }
                        attrs {
                            defaultChecked = param.default !is PercentDistance
                            name = "${param.name} distance type"
                            type = InputType.radio
                            value = "absolute"
                        }
                    }
                    styledLabel {
                        css {
                            +Styles.paramLabelStyle
                        }
                        +"Absolute"
                    }
                    styledInput {
                        css {
                            +Styles.paramRadioStyle
                        }
                        attrs {
                            defaultChecked = param.default is PercentDistance
                            name = "${param.name} distance type"
                            type = InputType.radio
                            value = "percent"
                        }
                    }
                    styledLabel {
                        css {
                            +Styles.paramLabelStyle
                        }
                        +"Percent"
                    }
                }
            }
        }
    }
}

// TODO: Add order selection
fun RBuilder.addRotationParams(paramList: List<AnimationParameter<Rotation>>?) {
    if (paramList == null) return
    for (param in paramList) {
        styledDiv {
            css {
                +Styles.paramStyle
            }
            styledLabel {
                css {
                    +Styles.paramLabelStyle
                }
                +param.name
            }
            styledDiv {
                css {
                    +Styles.rowFlexStyle
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"X"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.xRotation?.toString() ?: "0.0"
                        id = "${param.name} rotation x"
                        type = InputType.text
                    }
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"Y"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.yRotation?.toString() ?: "0.0"
                        id = "${param.name} rotation y"
                        type = InputType.text
                    }
                }
                styledLabel {
                    css {
                        +Styles.paramLabelStyle
                    }
                    +"Z"
                }
                styledInput {
                    css {
                        +Styles.paramInputStyle
                    }
                    attrs {
                        defaultValue = param.default?.zRotation?.toString() ?: "0.0"
                        id = "${param.name} rotation z"
                        type = InputType.text
                    }
                }

                styledDiv {
                    css {
                        +Styles.rowFlexStyle
                    }
                    styledLabel {
                        css {
                            +Styles.paramLabelStyle
                        }
                        +"Type:"
                    }
                    styledInput {
                        css {
                            +Styles.paramRadioStyle
                        }
                        attrs {
                            defaultChecked = param.default !is DegreesRotation
                            name = "${param.name} rotation type"
                            type = InputType.radio
                            value = "radians"
                        }
                    }
                    styledLabel {
                        css {
                            +Styles.paramLabelStyle
                        }
                        +"Radians"
                    }
                    styledInput {
                        css {
                            +Styles.paramRadioStyle
                        }
                        attrs {
                            defaultChecked = param.default is DegreesRotation
                            name = "${param.name} rotation type"
                            type = InputType.radio
                            value = "degrees"
                        }
                    }
                    styledLabel {
                        css {
                            +Styles.paramLabelStyle
                        }
                        +"Degrees"
                    }
                }
            }
        }
    }
}

fun RBuilder.addEquationParams(paramList: List<AnimationParameter<Equation>>?) {
    TODO()
}