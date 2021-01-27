package animatedledstrip.browsercontrol

import kotlinx.browser.document
import react.dom.render
import styled.css
import styled.styledDiv

fun main() {

    render(document.getElementById("root")) {
        styledDiv {
            css {
                +Styles.fillAvailableSpaceStyle
            }
            child(App::class) {}
        }
    }
}