package example.com

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.io.File

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    routing {

        /**
         * Expose the files in /static folder from localhost/static route.
         *
         * If you don't see such folder, run the project, and it will get generated
         * alongside the Tailwind styles.
         */
        staticFiles("/static", File("static"))
        get("/") {
            call.respondHtml {
                head {
                    title {
                        +"Ktor ❤️ Tailwind"
                    }

                    /**
                     * <link> tag to tell the browser where it finds the Tailwind styles.
                     */
                    link(rel = "stylesheet", href = "static/styles.css")
                }
                body {
                    div {
                        classes = setOf("flex", "flex-col", "w-full", "justify-center", "text-center", "p-12")

                        h1 {
                            classes = setOf("text-4xl", "text")
                            +"Kotlin ❤️ Tailwind"
                        }
                        p {
                            classes = setOf("m-12")
                            +"This website is generated with "
                            customLink("https://ktor.io/", "Ktor")
                            +", "
                            customLink("https://github.com/Kotlin/kotlinx.html", "kotlinx.html")
                            +" and "
                            customLink("https://tailwindcss.com/", "Tailwind CSS")
                            +"."
                        }
                    }
                }
            }
        }
    }
}

/**
 * Just like in React you can create your own components and compose your
 * UIs from them.
 */
fun FlowContent.customLink(href: String, text: String) {
    a(href) {
        classes = setOf("font-medium", "text-blue-600", "dark:text-blue-500", "hover:underline")
        +text
    }
}