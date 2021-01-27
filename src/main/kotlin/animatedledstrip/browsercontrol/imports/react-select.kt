@file:JsModule("react-select")
@file:JsNonModule

package animatedledstrip.browsercontrol.imports

import react.RClass
import react.RProps

@JsName("Select")
external val FancySelect: RClass<SelectProps>

@JsName("OptionsType")
external val OptionsType: RClass<OptionsTypeProps>

external interface SelectProps : RProps {
    var options: String
}

external interface OptionsTypeProps : RProps {
    var data: Any?
    var id: Int?
    var index: Int?
    var isDisabled: Boolean?
    var isFocused: Boolean?
    var isSelected: Boolean?
    var label: String?
    var value: Any?
}