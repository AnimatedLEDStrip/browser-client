package animatedledstrip.browsercontrol

import kotlinx.css.*
import styled.StyleSheet

object Styles : StyleSheet("ALSStyles", isStatic = true) {
    val centeredTextStyle by css {
        textAlign = TextAlign.center
    }

    val columnFlexStyle by css {
        display = Display.flex
        flexDirection = FlexDirection.column
        scrollBehavior = ScrollBehavior.smooth
    }

    val fillAvailableHeightStyle by css {
        height = LinearDimension.fillAvailable
    }

    val fillAvailableSpaceStyle by css {
        +fillAvailableHeightStyle
        +fillAvailableWidthStyle
    }

    val fillAvailableWidthStyle by css {
        width = LinearDimension.fillAvailable
    }

    val fontStyle by css {
        fontFamily = "Arial"
    }

    val pageHalfStyle by css {
        +columnFlexStyle
        width = LinearDimension("50%")
    }

    val pageQuarterStyle by css {
        +columnFlexStyle
        width = LinearDimension("25%")
    }

    val pageSectionStyle by css {
        +roundedSectionStyle
        backgroundColor = Color("#F0F0F0")
    }

    val paramInputStyle by css {
        +rightMarginStyle
        width = LinearDimension("15%")
    }

    val paramLabelStyle by css {
        marginRight = LinearDimension("1%")
    }

    val paramRadioStyle by css {
        +rightMarginStyle
    }

    val paramStyle by css {
        +rowFlexStyle
        +topMarginStyle
        alignItems = Align.center
    }

    val rightMarginStyle by css {
        marginRight = LinearDimension("1%")
    }

    val roundedSectionStyle by css {
        borderRadius = LinearDimension("15px")
        marginBottom = LinearDimension("1%")
        padding(LinearDimension("1%"))
    }

    val rowFlexStyle by css {
        display = Display.flex
        flexDirection = FlexDirection.row
        scrollBehavior = ScrollBehavior.smooth
    }

    val runningAnimationStyle by css {
        +roundedSectionStyle
        backgroundColor = Color.white
    }

    val topMarginStyle by css {
        marginTop = LinearDimension("1%")
    }
}