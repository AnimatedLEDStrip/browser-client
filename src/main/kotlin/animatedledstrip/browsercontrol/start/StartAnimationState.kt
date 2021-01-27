package animatedledstrip.browsercontrol.start

import animatedledstrip.animations.Animation
import animatedledstrip.colors.ColorContainer
import react.RState

external interface StartAnimationState : RState {
    var supportedAnimations: List<String>?
    var selectedAnim: Animation.AnimationInfo?
    var colorsPerColor: List<ColorContainer>?
}